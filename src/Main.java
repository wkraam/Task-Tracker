public class Main {
    public static void main(String[] args) {
        FileMan fm = new FileMan();
        if (args.length >= 1){
            switch (args[0]){
                case "add":
                    fm.newTask(args[1]);
                    System.out.println("Task added successfully (ID: "+1+")");
                    break;
                case "?":
                    System.out.println("add \t- add a task \t- requires a task description");
                    System.out.println("update \t- update a task description \t- requires a task id and description");
                    System.out.println("delete \t- delete a task \t- requires a task id");

                default:
                    System.out.println("unrecognized command, ? for info");

                    break;
            }
        }
        else{
            System.out.println("No task to run, please use keywords add, update or  delete");
            System.out.println("add \t-\t add a task - requires a task description");
            System.out.println("update \t-\t update a task description - requires a task id and description");
            System.out.println("mark-in-progress \t-\t mark a task as in progress - requires a task id");
            System.out.println("mark-done \t-\t completes a task - requires a task id");
            System.out.println("list \t-\t lists all tasks");
            System.out.println("list done \t-\t lists all done tasks");
            System.out.println("list todo \t-\t lists all todo tasks");
            System.out.println("list in-progress \t-\t lists all in-progress tasks");

        }

    }

    public static void add(String description){

    }

    public static void update(int id, String description){

    }
    public static void delete(int id){

    }
    public static void markInProgress (int id){

    }
    public static void markDone(int id){

    }
    public static void list(String command){

    }
}