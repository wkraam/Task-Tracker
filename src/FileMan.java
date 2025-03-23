import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class FileMan {

    String fileName = "src/tasks.json";
    public FileMan() throws IOException, ParseException {
        jsonTaskList = (JSONObject) new JSONParser().parse(new FileReader(fileName));
        allTasks = readAllTasks();
    }
    //init an empty arraylist<Task>
    static ArrayList<Task> allTasks;
    static ArrayList<Integer> usedIDs;
    static JSONObject jsonTaskList;

    // ---- external ----
    int newTask(String description) throws FileNotFoundException {
        //needs to get the list of used ID's, and go from there
        int newID = getUsedIDs().get(getUsedIDs().size()-1)+1;
        usedIDs.add(newID);
        Task newTask = new Task(newID, description, Status.TODO, LocalDateTime.now(), LocalDateTime.now());
        allTasks.add(newTask);
        updateTaskJson();
        return newID;
    }
    public void deleteTask(int id) throws FileNotFoundException {
        allTasks.removeIf(task -> task.getId() == id);
        usedIDs.remove((Integer) id);
        updateTaskJson();
    }
    public void updateTask(int id, String description) throws FileNotFoundException {
        for (Task task:allTasks){
            if (task.getId()==id){
                task.setDescription(description);
            }
        }
        updateTaskJson();
    }
    public void markTaskDone(int id) throws FileNotFoundException {
        for (Task task :
                allTasks) {
            if (task.getId() == id) {
                task.setStatus("done");
            }
        }
        updateTaskJson();
    }
    public void markTaskInprogress(int id) throws FileNotFoundException {
        for (Task task :
                allTasks) {
            if (task.getId() == id) {
                task.setStatus("in-progress");
            }
        }
        updateTaskJson();
    }
    public void printAllTasks(){
        for (Task el:allTasks){
            System.out.println(el.toString());
        }
    }
    public void printAllDone() {
        for (Task task:allTasks) {
            if (task.getStatus() == Status.DONE) {
                System.out.println(task);
            }
        }
    }
    public void printAllTodo() {
        for (Task task:allTasks) {
            if (task.getStatus() == Status.TODO) {
                System.out.println(task);
            }
        }
    }
    public void printAllInprogress() {
        for (Task task:allTasks) {
            if (task.getStatus() == Status.IN_PROGRESS) {
                System.out.println(task);
            }
        }
    }

    // ---- internal ----
    static ArrayList<Task> readAllTasks(){
        usedIDs = new ArrayList<>();
        //first try to open the JSON file, then try to read it
        //need to get the list of used ID's

        ArrayList<Task> returnList = new ArrayList<>();

        try{
            //JSONObject jsonTaskList = (JSONObject) new JSONParser().parse(new FileReader("src/tasks.json"));
            if (jsonTaskList.isEmpty()) System.out.println("the list is empty");
            else{
                JSONArray inputArray = (JSONArray) jsonTaskList.get("tasks");
                for (Object obj: inputArray) {
                    JSONObject jo = (JSONObject) obj;
                    //System.out.println(jo); //for debug

                    usedIDs.add(Integer.parseInt(jo.get("id").toString()));
                    returnList.add(new Task(Integer.parseInt(jo.get("id").toString()),
                            jo.get("description").toString(),
                            switch (jo.get("status").toString().toLowerCase()) {
                                case "in-progress" -> Status.IN_PROGRESS;
                                case "done" -> Status.DONE;
                                default -> Status.TODO;
                            },
                            LocalDateTime.parse(jo.get("createdAt").toString()),
                            LocalDateTime.parse(jo.get("updatedAt").toString())));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        usedIDs.sort(null);
        return returnList;
    }
    public ArrayList<Integer> getUsedIDs() {
        return usedIDs;
    }
    public void updateTaskJson() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(fileName);
        pw.write("{\n\"tasks\":[\n");
        if (allTasks.size() == 1) pw.append(allTasks.get(0).toJSON());
        else{
            for (int i = 0; i < allTasks.size(); i++) {
                pw.append(allTasks.get(i).toJSON());
                if(i+1 != allTasks.size()){
                    pw.append(",\n");
                }else pw.append("\n");
            }
        }
        pw.append("]\n}");
        pw.flush();
        pw.close();
    }

}
