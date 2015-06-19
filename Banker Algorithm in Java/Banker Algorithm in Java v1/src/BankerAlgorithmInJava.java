
import java.awt.Choice;
import java.util.Scanner;

public class BankerAlgorithmInJava {
    private int need[][] , allocate[][] , max[][] , available[] , numberOFProcess , numberOFRescourcces;
    public void SetData(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter The Number of Process ? ");
        numberOFProcess = in.nextInt();
        System.out.println("Enter The Number of Resources ? ");
        numberOFRescourcces = in.nextInt();
        
        need = new int[numberOFProcess][numberOFRescourcces];
        allocate = new int[numberOFProcess][numberOFRescourcces];
        max = new int[numberOFProcess][numberOFRescourcces];
        available = new int[numberOFRescourcces];
        
        int instances[] = new int[numberOFProcess];
        int alloc[] = new int[numberOFProcess];
        System.out.println("Enter instances of resources ? ");
        for (int i = 0; i < numberOFRescourcces; i++) {
            instances[i] = in.nextInt();
        }
        
        System.out.println("Enter allocated resource table ? ");
        for (int i = 0; i < numberOFProcess; i++) {
            for (int j = 0; j < numberOFRescourcces; j++) {
                allocate[i][j] = in.nextInt();
                alloc[j] += allocate[i][j];
            }
        }
               
        System.out.println("Enter maximum resource table ? ");
        for (int i = 0; i < numberOFProcess; i++) {
            for (int j = 0; j < numberOFRescourcces; j++) {
                max[i][j] = in.nextInt();
            }
        }
        
        // Calculate Available = sum (Allocation) - initial
        for (int i = 0; i < numberOFRescourcces; i++) {
            available[i] = instances[i] - alloc[i];
        }
        
        // Calculate Need = Max - Allocation
        for (int i = 0; i < numberOFProcess; i++) {
            for (int j = 0; j < numberOFRescourcces; j++) {
                need[i][j] = max[i][j] - allocate[i][j];
            }
        }                
    }
    public void releaseResorces(int pNumber , int r1 , int r2 , int r3){
        allocate[pNumber][0] -= r1;
        allocate[pNumber][1] -= r2;
        allocate[pNumber][2] -= r3;
        
        available[0] += r1;
        available[1] += r2;
        available[2] += r3;
        System.out.println("Mission Done Successfully !");
    }
    public void requestResorces(int pNumber , int r1 , int r2 , int r3){
        allocate[pNumber][0] += r1;
        allocate[pNumber][1] += r2;
        allocate[pNumber][2] += r3;
        
        available[0] -= r1;
        available[1] -= r2;
        available[2] -= r3;
    }
    public boolean checkRequest(int p){
        for (int i = 0; i < numberOFRescourcces; i++) {
            if(available[i] < need[p][i]) return false;
        }
        return true;
    }    
    public boolean isSafe(){
        boolean finish[] = new boolean[numberOFProcess];
        int i = 0;
        while (i<numberOFProcess) {
            boolean allocated = false;
            for (int j = 0; j < numberOFProcess ; j++) {
               if(!finish[j] && checkRequest(j)){
                   for (int k = 0; k < numberOFRescourcces ; k++) {
                      available[k] += allocate[j][k];    
                   }
                   System.out.println("Allocated Process p" + (j) + " Successfully !");
                   allocated = finish[j] = true;
                   i++;
               }   
            } 
            if(!allocated) break;
        }
        System.out.println("Avaiable resources");
        for (int j = 0; j < numberOFRescourcces; j++) {
            System.out.print(available[j] + " ");
        }
        System.out.println();
        if(i==numberOFProcess){
            System.out.println("Safety Allocated");
            return true;
        }
        System.out.println("Not Safety Allocated");
        return false;
    }
    public void showData(){
        for (int i = 0; i < numberOFProcess ; i++) {
            for (int j = 0; j < numberOFRescourcces ; j++) {
                System.out.print(allocate[i][j] + " ");
            }
            System.out.print( "  ");
            for (int j = 0; j < numberOFRescourcces ; j++) {
                System.out.print(max[i][j] + " ");
            }
            System.out.print( "  ");
            for (int j = 0; j < numberOFRescourcces ; j++) {
                System.out.print(need[i][j] + " ");
            }
            System.out.print( "  ");
            if(i==0){
                for (int j = 0; j < numberOFRescourcces ; j++) {
                System.out.print(available[j] + " ");
                }
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        BankerAlgorithmInJava obj = new BankerAlgorithmInJava();
        Scanner in = new Scanner(System.in);
        String choice = "";        
        while (!"Quit".equals(choice)) { 
            System.out.println("-----------------------------");
            System.out.println("[*] to Check safe or not");
            System.out.println("[RL] to release resources");
            System.out.println("[RQ] to request resources");
            System.out.println("[Quit] exit");
            System.out.println("-----------------------------");
            
            choice = in.nextLine();
            if(choice.contains("*")){
                obj.SetData();
                System.out.println("The Entered Data : ");
                obj.showData();
                System.out.println(obj.isSafe());
            }
            else if(choice.contains("RL") || choice.contains("rl")){
                String[] splitArray = choice.split("\\s+");
                obj.releaseResorces(Integer.valueOf(splitArray[1]), Integer.valueOf(splitArray[2]),Integer.valueOf(splitArray[3]),Integer.valueOf(splitArray[4]));
                obj.showData();   
            }
            else if(choice.contains("RQ") || choice.contains("rq")){
                String[] splitArray = choice.split("\\s+");
                obj.requestResorces(Integer.valueOf(splitArray[1]), Integer.valueOf(splitArray[2]),Integer.valueOf(splitArray[3]),Integer.valueOf(splitArray[4]));
                obj.showData(); 
                System.out.println(obj.isSafe());
            }
            else if(choice.contains("Quit") || choice.contains("quit") || choice.contains("exit")){
                break;   
            }
            else{
                System.err.println("Command not found !");
            }
        }
        
        
        
    }
    
}
