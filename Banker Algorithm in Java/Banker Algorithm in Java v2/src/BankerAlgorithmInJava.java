
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BankerAlgorithmInJava {
    private int need[][] , allocate[][] , max[][] , available[] ,instances[] ,alloc[], numberOFProcess , numberOFRescourcces;
    public void SetData() throws FileNotFoundException, IOException{
        Scanner in = new Scanner(System.in);
        System.out.println("Enter The Number of Process ? ");
        numberOFProcess = in.nextInt();
        System.out.println("Enter The Number of Resources ? ");
        numberOFRescourcces = in.nextInt();
        
        need = new int[numberOFProcess][numberOFRescourcces];
        allocate = new int[numberOFProcess][numberOFRescourcces];
        max = new int[numberOFProcess][numberOFRescourcces];
        available = new int[numberOFRescourcces];
        
        instances = new int[numberOFProcess];
        alloc = new int[numberOFProcess];
        System.out.println("Enter instances of resources ? ");
        for (int i = 0; i < numberOFRescourcces; i++) {
            instances[i] = in.nextInt();
        }
        System.out.println("-----------------------------------");
        BufferedReader reader = new BufferedReader(new FileReader("alloc.txt"));        
        String line = "";
        int i = 0;
        System.out.println("The allocated resource table : ");
        while ((line = reader.readLine()) != null) {
            String[] splitArray = line.split("\\s+");
              for (int j = 0; j < numberOFRescourcces; j++) {
                allocate[i][j] = Integer.valueOf(splitArray[j]);
                  System.out.print(allocate[i][j] + " ");
                alloc[j] += allocate[i][j];
               }
               System.out.println("");
              i++;
        }
        reader.close(); 
        System.out.println("-----------------------------------");
               
        reader = new BufferedReader(new FileReader("max.txt"));        
        line = "";
        i = 0;
        System.out.println("The max resource table : ");
        while ((line = reader.readLine()) != null) {
            String[] splitArray = line.split("\\s+");
              for (int j = 0; j < numberOFRescourcces; j++) {
                max[i][j] = Integer.valueOf(splitArray[j]);
                  System.out.print(max[i][j] + " ");
               }
               System.out.println("");
              i++;
        }
        reader.close(); 
        System.out.println("-----------------------------------");
       
        calculateAVailable();
        calculateNeed();
    }
    public void calculateNeed()
    {
        // Calculate Need = Max - Allocation
        for (int i = 0; i < numberOFProcess; i++) {
            for (int j = 0; j < numberOFRescourcces; j++) {
                need[i][j] = max[i][j] - allocate[i][j];
            }
        } 
    }
    public void calculateAVailable()
    {
        // Calculate Available = sum (Allocation) - initial
        for (int i = 0; i < numberOFRescourcces; i++) {
            available[i] = instances[i] - alloc[i];
        }
    }
    public void releaseResorces(int pNumber , int r1 , int r2 , int r3){
        allocate[pNumber][0] -= r1;
        allocate[pNumber][1] -= r2;
        allocate[pNumber][2] -= r3;
        
        available[0] += r1;
        available[1] += r2;
        available[2] += r3;
        
        calculateAVailable();
        calculateNeed(); 
        System.out.println("Mission Done Successfully !");
    }
    public boolean requestResorces(int pNumber , int r1 , int r2 , int r3){
        if(r1 > available[0] || r2 > available[1] || r3 > available[2]) return false;
        
        allocate[pNumber][0] += r1;
        allocate[pNumber][1] += r2;
        allocate[pNumber][2] += r3;
        
        available[0] -= r1;
        available[1] -= r2;
        available[2] -= r3;
        
        calculateAVailable();
        calculateNeed(); 
        
        return true;
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
    public static void main(String[] args) throws IOException {
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
                obj.isSafe();
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
                if(obj.requestResorces(Integer.valueOf(splitArray[1]), Integer.valueOf(splitArray[2]),Integer.valueOf(splitArray[3]),Integer.valueOf(splitArray[4]))){
                    if(obj.isSafe()) System.out.println("Accept The Request");
                    else  System.out.println("Regect The Request");
                }
                else{
                    System.out.println("Regect The Request");
                }
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
