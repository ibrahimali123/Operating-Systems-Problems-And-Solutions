/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cli_program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Ibrahim Ali
 */
public class CLI_Program {

    /**
     * @param args the command line arguments
     */
    
    public static void date()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date)); 
    }
    public static void help()
    {
        System.out.println("args  : List all command arguments");
        System.out.println("date  : Current date/time");
        System.out.println("exit  : Stop all");
        System.out.println("mkdir : create a directory");
        System.out.println("rmdir : remove a directory");
        System.out.println("clear : clear the screen");
        System.out.println("cd    : This command changes the current directory to another one.");
        System.out.println("ls    : These programs list each given file or directory name.");
        System.out.println("cat   : Concatenate files and print on the standard output.");
        System.out.println("more  : Let us display and scroll down the output in one direction only.");
        System.out.println("less  : Like more but more enhanced. It support scroll forward and backward.");
        System.out.println("cp    : copies each other given file into a file with the same name in that directory.");
        System.out.println("mv    : moves each other given file into a file with the same name in that directory.");
        System.out.println("rm    : rename file in that directory.");
        System.out.println("pwd   : Display current user directory.");
    }
    public static void args()
    {
        System.out.println("mkdir : take 1 param the name of the dir");
        System.out.println("rmdir : take 1 param the name of the dir");
        System.out.println("cd    : take 1 param the name of the dir to be changed");
        System.out.println("cat   : take 1 param the name of the file to view");
        System.out.println("more  : take 1 param the name of the file");
        System.out.println("less  : take 1 param the name of the file");
        System.out.println("cp    : take 2 param the name of the first and second dir");
        System.out.println("mv    : take 2 param the name of the first and second dir");
        System.out.println("rm    : take 1 param the name of the file");
    }
    private static void cp(String SourcePath , String destPath) throws IOException
    {
        int x = 0;
        String fileName = SourcePath;
        char slash = '\\';
        for (int i = 0; i < SourcePath.length(); i++) {
            if(SourcePath.charAt(i) == '\\' || SourcePath.charAt(i) == '/'){
                  x = i;
                  slash = SourcePath.charAt(i);
            }
        }
        SourcePath = SourcePath.substring(x+1, SourcePath.length());
        File src = new File(fileName); 
        File target = new File(destPath+slash+SourcePath);
        if(target.exists()){
            System.err.println("file already exits");
        }
        if(!target.exists() || !src.exists()){
            System.err.println("error path");
        }
        else{
            Files.copy(src.toPath(), target.toPath());
        }

    }
    private static void mv(String SourcePath , String destPath) {

        // File (or Directory) to be moved
        File file = new File(SourcePath);

        // Destination directory
        File dir = new File(destPath);

        // Move file to a new directory
        boolean success = file.renameTo(new File(dir, file.getName()));

        if (success) {
            System.out.println("File was successfully moved.\n");
        } else {
            System.out.println("File was not successfully moved.\n");
        }


    }
    public static void rm(String dir) 
    {
      try{ 
    		File file = new File(dir);
 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
 
    	}catch(Exception e){
 
    		e.printStackTrace();
 
    	}
    }
    public static void ls(String currentDir)
    {
        // Directory path here
        String path = currentDir;
        
        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles(); 
 
        for (int i = 0; i < listOfFiles.length; i++) 
        {
 
        if (listOfFiles[i].isFile() || listOfFiles[i].isDirectory()) 
        {
            files = listOfFiles[i].getName();
            System.out.println(files);
        }
        }
    }
    
    public static void mkdir(String dir ,String currentDir)
    {
        boolean success = false;
        
        // Creating new directory in Java, if it doesn't exists
        
        String OS = System.getProperty("os.name").toLowerCase();
        
        if(OS.contains("win")){
            currentDir += "\\" + dir;
        }
        else{
            currentDir += "/" + dir;
        }
        
        File directory = new File(currentDir);
        
        if (directory.exists()) 
        {
            System.out.println("Directory already exists ...");
        } 
        else 
        {
           
            success = directory.mkdir();
            if (success) {
                System.out.printf("Successfully created new directory : %s%n", dir);
            } else {
                System.out.printf("Failed to create new directory: %s%n", dir);
            }
        }
        

    }
    public static void rmdir(String dir , String  currentDir)
    {
        boolean success = false;
        
        String OS = System.getProperty("os.name").toLowerCase();
        
        if(OS.contains("win")){
            currentDir += "\\" + dir;
        }
        else{
            currentDir += "/" + dir;
        }
        
        
        
        File directory = new File(currentDir);
        directory.delete();
        
        if (!directory.exists()) 
        {
            System.out.printf("Successfully deleted directory : %s%n", dir);
        } 
        else 
        {
            System.out.printf("Failed to delete directory: %s%n", dir);
        }
        

    }
    public static void cat(String filename , String dir) throws FileNotFoundException, IOException
    {
        String OS = System.getProperty("os.name").toLowerCase();
        String fileDir ;
        if(OS.contains("win")){
            fileDir = dir +"\\"+ filename;
        }
        else{
            fileDir = dir +"/"+ filename;
        }
        
        
        BufferedReader reader = new BufferedReader(new FileReader(fileDir));
        String line = "";
        while ((line = reader.readLine()) != null) {
             System.out.println(line);
        }
        reader.close();
    }
    public static void more(String filename , String dir) throws FileNotFoundException, IOException
    {
        String OS = System.getProperty("os.name").toLowerCase();
        String fileDir ;
        if(OS.contains("win")){
            fileDir = dir +"\\"+ filename;
        }
        else{
            fileDir = dir +"/"+ filename;
        }
        
        ArrayList list = new ArrayList();
        
        BufferedReader reader = new BufferedReader(new FileReader(fileDir));
        String line = "";
        while ((line = reader.readLine()) != null) {
             list.add(line);
        }
        reader.close();
        String c;
        Scanner read = new Scanner(System.in);
        
        for (int i = 0; i < list.size() ; i++) {
            if(i<4){
                System.out.println(list.get(i));
            }
            if ( i == 4) {
                System.err.print("enter to show more");
            }
            if(i>=4){
                c = read.nextLine();
                if(c.length() == 0){
                    System.out.print(list.get(i));
                }
            }
        }
    }
    public static void find(String fileName , String path)
    {
        String OS = System.getProperty("os.name").toLowerCase();
        String filePath;
        if(OS.contains("win")){
            filePath = path + "\\" + fileName;
        }
        else{
            filePath = path + "/" + fileName;
        }
        
        File fileObj  = new File(filePath);
        if(fileObj.exists()){
            System.out.println("--> file exits in " + path);
            for (int i = 0; i < path.length(); i++) {
                if(path.charAt(i)== '\\' || path.charAt(i)== '/'){
                    filePath = path.substring(0, i) + path.charAt(i) +fileName;
                    fileObj  = new File(filePath);
                    if(fileObj.exists()){
                        System.out.println("--> file exits in " + path.substring(0, i));
                    }
                }
            }
        }
        else{
            System.out.println("--> file not exits");
        }
    }
    public static void grep(String text , String fileName , String path) throws FileNotFoundException, IOException
    {
        String OS = System.getProperty("os.name").toLowerCase();
        String filePath;
        if(OS.contains("win")){
            filePath = path + "\\" + fileName;
        }
        else{
            filePath = path + "/" + fileName;
        }
             
        File fileObj  = new File(filePath);
        boolean falg = false;
        if(!fileObj.exists()){
            System.err.println("there is no file named " + fileName + "in this directory");
        }
        else{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = "";
            while ((line = reader.readLine()) != null) {
                   if(line.contains(text)){
                       falg = true;
                       System.out.println(line);
                   }
            }
            if(falg == false){
                System.err.println("the word " + text + "not found in the file");
            }
            reader.close();
        }
    }
    public static void funList2(String fileName,String currentDir) throws IOException {
        String OS = System.getProperty("os.name").toLowerCase();
        String path; 
        if(OS.contains("win")){
            path = currentDir + "\\" + fileName;
        }
        else{
            path = currentDir + "/" + fileName;
        }
       
        String files;
        File folder = new File(currentDir); 
        if (!folder.exists() ){
                     System.err.println("there is no file with " + fileName);
        } 
                    
        File[] listOfFiles = folder.listFiles(); 
       
        BufferedWriter fileWriter = new BufferedWriter(new  FileWriter(path,true));
        fileWriter.newLine();
        fileWriter.newLine();
        fileWriter.newLine(); 
            
        for (int i = 0; i < listOfFiles.length; i++) {
 
            
            if (listOfFiles[i].isFile() || listOfFiles[i].isDirectory()) 
            {
              files = listOfFiles[i].getName();
              fileWriter.write(files); 
              fileWriter.newLine();
            }
        }
        fileWriter.close();
    }
    public static void funList1(String fileName,String currentDir) throws IOException {
        String OS = System.getProperty("os.name").toLowerCase();
        String path; 
        if(OS.contains("win")){
            path = currentDir + "\\" + fileName;
        }
        else{
            path = currentDir + "/" + fileName;
        }
       
        String files;
        File folder = new File(currentDir); 
        if (!folder.exists() ){
                     System.err.println("there is no file with " + fileName);
        } 
                    
        File[] listOfFiles = folder.listFiles(); 
       
        BufferedWriter fileWriter = new BufferedWriter(new  FileWriter(path,false));
        fileWriter.newLine();
        fileWriter.newLine();
        fileWriter.newLine(); 
            
        for (int i = 0; i < listOfFiles.length; i++) {
 
            
            if (listOfFiles[i].isFile() || listOfFiles[i].isDirectory()) 
            {
              files = listOfFiles[i].getName();
              fileWriter.write(files); 
              fileWriter.newLine();
            }
        }
        fileWriter.close();
    }
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
        String currentDir = System.getProperty("user.dir");
        String homeDir = System.getProperty("user.home");
        
        Scanner reader = new Scanner(System.in);
        String command = null , com1 = null, com2;
        boolean FirstCommand = false , SecondCommand = false;
        
        currentDir = homeDir;
        do
        {
        
        
        if(SecondCommand == false){
            System.out.print(currentDir + " $ ");
            command = reader.nextLine();
        }
        if(SecondCommand == true){
            SecondCommand = false;
        }
        if(command.contains(";")){
            com1 = command;
            int i = command.indexOf(";");
            command = command.substring(0,i-1);
            FirstCommand = true;
        }
        
        if(command.contains("mkdir"))
        {
            String dirName = command.substring(6, command.length());
           
            mkdir(dirName , currentDir);
            
        }
        else if(command.contains("rmdir"))
        {
            String dirName = command.substring(6, command.length());
            rmdir(dirName , currentDir);
        }
        else if(command.contains("args"))
        {
            args();
        }
        else if(command.contains("cat"))
        {
            String filename = command.substring(4, command.length());
            File filePath = new File(filename);
            if(!filePath.exists()){
                System.err.println("error path");
            }
            cat(filename , currentDir);
        }
        else if(command.contains("more"))
        {
            String filename = command.substring(5, command.length());
            File filePath = new File(filename);
            if(!filePath.exists()){
                System.err.println("error path");
            }
            more(filename , currentDir);
        }
        else if(command.contains("ls") && !command.contains(">>"))
        {
            ls(currentDir);
        }
        else if(command.contains("date"))
        {
            date();
        }
        else if(command.contains("exit"))
        {
            break;
        }
        else if(command.contains("rm"))
        {
            String dir = command.substring(3, command.length());
            rm(dir);
        }
        else if(command.length() == 0){
            continue;
        }
        else if(command.contains("find"))
        {
            int x = 0;
            boolean flag = false;
            String filename = "" ,Path = ""; 
            for(int i=0;i<command.length();i++)
            {
                if(command.charAt(i) == '.'){
                    flag = true;
                }
                if(command.charAt(i) == ' ' && flag == true){
                    x = i;
                    break;
                }
            }
            filename = command.substring(5, x);
            Path = command.substring(x+1,command.length());
            File theDir = new File(Path);
            if (!theDir.exists() ){
                     System.err.println("Error in directory");
             } 
            find(filename, Path);
        }
        else if(command.contains("grep"))
        {
            int x = 0;
            boolean flag = false;
            String filename = "" ,Path = "" , text; 
            
            for(int i=0;i<command.length();i++)
            {
                if(command.charAt(i) == ' '){
                    x = i; 
                }
                
            }
            text = command.substring(5, x);
            
            String OS = System.getProperty("os.name").toLowerCase(); 
            if(OS.contains("win")){
                  Path = currentDir + '\\'+command.substring(x+1,command.length());
             }
            else{
                  Path = currentDir + '/'+command.substring(x+1,command.length());
            }
        
            
            File theDir = new File(Path);
            if (!theDir.exists() ){
                     System.err.println("Error in directory");
             } 
            grep(text,filename, Path);
        }
        else if(command.contains("mv"))
        {
            int x = 0;
            boolean flag = false;
            String sourcePath = "" ,destPath = ""; 
            for(int i=0;i<command.length();i++)
            {
                if(command.charAt(i) == '.'){
                    flag = true;
                }
                if(command.charAt(i) == ' ' && flag == true){
                    x = i;
                    break;
                }
            }
            sourcePath = command.substring(3, x);
            destPath = command.substring(x+1,command.length());
            File theDir1 = new File(sourcePath);
            File theDir2 = new File(destPath);
            if (!theDir1.exists() || !theDir2.exists() ){
                     System.err.println("Error in directory");
                } 
            mv(sourcePath, destPath);
        } 
        else if (command.contains(">>")) {
                String fileName = command.substring(6, command.length());
                funList2(fileName, currentDir);
        }
        else if (command.contains(">")) {
                String fileName = command.substring(5, command.length());
                funList1(fileName, currentDir);
        }
        else if(command.contains("cp"))
        {
            int x = 0;
            boolean flag = false;
            String sourcePath = "" ,destPath = ""; 
            for(int i=0;i<command.length();i++)
            {
                if(command.charAt(i) == '.'){
                    flag = true;
                }
                if(command.charAt(i) == ' ' && flag == true){
                    x = i;
                    break;
                }
            }
            sourcePath = command.substring(3, x);
            destPath = command.substring(x+1,command.length());
            File theDir1 = new File(sourcePath);
            File theDir2 = new File(destPath);
           
            cp(sourcePath, destPath);
        } 
        else if(command.contains("clear"))
        {
            for (int i = 0; i < 10; i++) {
                System.out.println(homeDir + " $ ");
            }
        }
        else if(command.contains("help"))
        {
           help();
        }
        else if(command.contains("pwd"))
        {
            System.out.println(currentDir);
        }
        
        else if(command.contains("cd") && command.length()<4 )
        {
            currentDir = homeDir;
            System.out.println(currentDir);
        }
        else if(command.contains("cd") && command.length()>4 )
        {
            String dir = command.substring(3,command.length());
            File theDir = new File(dir); 
            if (!theDir.exists()){
                System.out.println("Error directory");
                
            }
            else{
                currentDir = dir;
            }
            
        }
        else
        {
            System.out.println( "'" + command + "' is not recognized as an internal or external command ");
            System.out.println("operating program or batch file.");
        }
        
        if(FirstCommand == true){
           int i = com1.indexOf(";");
           command = com1.substring(i+2,com1.length());
           SecondCommand = true;
           FirstCommand = false;
        }
        
        }
        while(!"exit".equals(command));
        
        
    }
}
