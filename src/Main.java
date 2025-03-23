import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Scanner;

public class Main {
    /*Requirements
    The application should run from the command line, accept user actions and inputs as arguments, and store the tasks in a JSON file. The user should be able to:

    Add, Update, and Delete tasks
    Mark a task as in progress or done
    List all tasks
    List all tasks that are done
    List all tasks that are not done
    List all tasks that are in progress
    Here are some constraints to guide the implementation:

    You can use any programming language to build this project.
    Use positional arguments in command line to accept user inputs.
    Use a JSON file to store the tasks in the current directory.
    The JSON file should be created if it does not exist.
    Use the native file system module of your programming language to interact with the JSON file.
    Do not use any external libraries or frameworks to build this project.
    Ensure to handle errors and edge cases gracefully.*/



    public static void main(String[] args) throws IOException, ParseException {

        FileMan fm = new FileMan();

        if (args.length >= 1){
            switch (args[0]){
                case "add":
                    add(fm, args[1]);
                    break;
                case "?":
                    printHelp();
                    break;
                case "list":
                    if (args.length == 1) list(fm);
                    else list(fm, args[1]);
                    break;
                case "delete":
                    if (args.length == 2){
                        fm.deleteTask(Integer.parseInt(args[1]));
                    } else {
                        System.out.println("please enter only one ID");
                    }
                    break;
                case "update":
                    if (args.length == 3){
                        fm.updateTask(Integer.parseInt(args[1]), args[2].toString());
                    } else {
                        System.out.println("please enter task ID and description between \" after update keyword");
                    }
                    break;
                case "mark-in-progress":{
                    if (args.length == 2){
                        fm.markTaskInprogress(Integer.parseInt(args[1]));
                    } else {
                        System.out.println("please enter only one ID");
                    }
                    break;
                }
                case "mark-done":{
                    if (args.length == 2){
                        fm.markTaskDone(Integer.parseInt(args[1]));
                    } else {
                        System.out.println("please enter only one ID");
                    }
                    break;
                }
                default:
                    System.out.println("unrecognized command, ? for info");
                    break;
            }
        }
        else{
            printHelp();

        }

        //loop to test functions
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        while (cont){
            System.out.println("testing env.");
            System.out.println("1 - test reading all tasks");
            System.out.println("2 - test used ID's");
            System.out.println("3 - add new task");
            System.out.println("4 - remove task with ID");
            System.out.println("5 - update task with ID");
            System.out.println("6 - mark task done with ID");
            System.out.println("7 - mark task in progress with ID");
            System.out.println("8 - print all done tasks");
            System.out.println("9 - print all in progress tasks");
            System.out.println("0 - print all todo tasks");
            System.out.println("default - exit");
            System.out.print("enter input: ");
            int id;
            String desc;
            switch (sc.nextLine()) {
                case "1" -> fm.printAllTasks();
                case "2" -> System.out.println(fm.getUsedIDs());
                case "3" -> {
                    System.out.print("enter new task description: ");
                    desc = sc.nextLine();
                    add(fm, desc);
                }
                case "4" -> {
                    System.out.print("enter the task ID: ");
                    id = Integer.parseInt(sc.nextLine());
                    delete(fm, id);
                }
                case "5" -> {
                    System.out.print("enter the task ID: ");
                    id = Integer.parseInt(sc.nextLine());
                    System.out.println();
                    System.out.print("enter the new description: ");
                    desc = sc.nextLine();
                    update(fm, id, desc);
                }
                case "6" ->{
                    System.out.print("enter the task ID: ");
                    id = Integer.parseInt(sc.nextLine());
                    System.out.println();
                    markDone(fm, id);
                }
                case "7" ->{
                    System.out.print("enter the task ID: ");
                    id = Integer.parseInt(sc.nextLine());
                    System.out.println();
                    markInProgress(fm, id);
                }
                case "8" -> list(fm, "done");
                case "9" -> list(fm, "in-progress");
                case "0" -> list(fm, "todo");
                default -> cont = false;
            }
        }
    }

    public static void add(FileMan fm, String description) throws FileNotFoundException {
        int returnInt = fm.newTask(description);
        System.out.println("Task added successfully (ID: "+returnInt+")");
    }

    public static void update(FileMan fm, int id, String description) throws FileNotFoundException{
        fm.updateTask(id, description);
    }
    public static void delete(FileMan fm, int id) throws FileNotFoundException {
        fm.deleteTask(id);
    }
    public static void markInProgress (FileMan fm, int id) throws FileNotFoundException{
        fm.markTaskInprogress(id);
    }
    public static void markDone(FileMan fm, int id) throws FileNotFoundException{
        fm.markTaskDone(id);
    }
    public static void list(FileMan fm ,String command) throws FileNotFoundException{
        if(command.equalsIgnoreCase("done")){
            System.out.println("listing all done tasks");
            fm.printAllDone();
        }else if(command.equalsIgnoreCase("todo")){
            System.out.println("listing all todo tasks");
            fm.printAllTodo();
        } else if (command.equalsIgnoreCase("in-progress")) {
            System.out.println("listing all in-progress tasks");
            fm.printAllInprogress();
        }
    }
    public static void list(FileMan fm){
        System.out.println("listing all available tasks");
        fm.printAllTasks();
    }

    public static void printHelp(){
        System.out.println("--- HELP ---");
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