
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BankerAlgorithmInJava {
    public int need[][] , allocate[][] , max[][] , available[] ,instances[] ,alloc[], numberOFProcess , numberOFRescourcces;
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
        
        for (int i = 0; i < numberOFProcess; i++) {
            for (int j = 0; j < numberOFRescourcces; j++) {
                allocate[i][j] = 0;
            }
        } 
        
        BufferedReader reader = new BufferedReader(new FileReader("max.txt"));        
        String line = "";
        int i = 0;
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
        for (int i = 0; i < numberOFProcess; i++) {
            for (int j = 0; j < numberOFRescourcces; j++) {
                alloc[j] += allocate[i][j];
            }
        } 
        
        // Calculate Available = sum (Allocation) - initial
        for (int i = 0; i < numberOFRescourcces; i++) {
            available[i] = instances[i] - alloc[i];
        }
    }
    public boolean releaseResorces(int pNumber , int r1 , int r2 , int r3){
        if(r1 > allocate[pNumber][0] || r2 > allocate[pNumber][1] || r3 > allocate[pNumber][2]) return false;
        allocate[pNumber][0] -= r1;
        allocate[pNumber][1] -= r2;
        allocate[pNumber][2] -= r3;
        
        available[0] += r1;
        available[1] += r2;
        available[2] += r3;
        
        calculateNeed(); 
        return true;
    }
    public boolean requestResorces(int pNumber , int r1 , int r2 , int r3){
        if(r1 > available[0] || r2 > available[1] || r3 > available[2]) return false;
        if(r1 > need[pNumber][0] || r2 > need[pNumber][1] || r3 > need[pNumber][2]) return false;
        
        allocate[pNumber][0] += r1;
        allocate[pNumber][1] += r2;
        allocate[pNumber][2] += r3;
        
        int a = available[0] , b = available[1] , c = available[2];
        
        available[0] -= r1;
        available[1] -= r2;
        available[2] -= r3;
        
        calculateNeed();
        if(isSafe(false)) return true;
        
        allocate[pNumber][0] -= r1;
        allocate[pNumber][1] -= r2;
        allocate[pNumber][2] -= r3;

        available[0] = a;
        available[1] = b;
        available[2] = c;
        calculateNeed();
        return false;       
    }
    public boolean checkRequest(int p){
        for (int i = 0; i < numberOFRescourcces; i++) {
            if(available[i] < need[p][i]) return false;
        }
        return true;
    }    
    public boolean isSafe(boolean flag){       
        boolean finish[] = new boolean[numberOFProcess];
        int i = 0;
        while (i<numberOFProcess) {
            boolean allocated = false;
            for (int j = 0; j < numberOFProcess ; j++) {
               if(!finish[j] && checkRequest(j)){
                   for (int k = 0; k < numberOFRescourcces ; k++) {
                      available[k] += allocate[j][k];    
                   }
                   if(flag){
                       System.out.println("Allocated Process p" + (j) + " Successfully !");
                   }
                   allocated = finish[j] = true;
                   i++;
               }   
            } 
            if(!allocated) break;
        }
//        System.out.println("Avaiable resources");
//        for (int j = 0; j < numberOFRescourcces; j++) {
//            System.out.print(available[j] + " ");
//        }
//        System.out.println();
        if(i==numberOFProcess){
            System.out.println("is Safe");
            return true;
        }
        System.out.println("is Not Safe");
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
        obj.SetData();
        while (!"Quit".equals(choice)) { 
            System.out.println("-----------------------------");
            System.out.println("[*] to Check safe or not");
            System.out.println("[RL] to release resources");
            System.out.println("[RQ] to request resources");
            System.out.println("[Quit] exit");
            System.out.println("-----------------------------");
            
            choice = in.nextLine();
            if(choice.contains("*")){ 
                obj.calculateAVailable();
                obj.isSafe(true);
                obj.showData();
            }
            else if(choice.contains("RL") || choice.contains("rl")){
                String[] splitArray = choice.split("\\s+");
                if(obj.releaseResorces(Integer.valueOf(splitArray[1]), Integer.valueOf(splitArray[2]),Integer.valueOf(splitArray[3]),Integer.valueOf(splitArray[4])))
                {
                    System.out.println("Mission Done");
                }
                else
                {
                    System.out.println("Error , you want to release amout larger than allocation ");
                }
                obj.showData();   
            }
            else if(choice.contains("RQ") || choice.contains("rq")){
                String[] splitArray = choice.split("\\s+");
                if(obj.requestResorces(Integer.valueOf(splitArray[1]), Integer.valueOf(splitArray[2]),Integer.valueOf(splitArray[3]),Integer.valueOf(splitArray[4]))){
                    System.out.println("Accept The Request");
                }
                else{
                    System.out.println("Regect The Request");
                }
                obj.showData();
            }
            else if(choice.contains("Quit") || choice.contains("quit") || choice.contains("exit")){
                break;   
            }
            else if(choice.contains(".")){
                obj.showData();
            }
            else{
                System.err.println("Command not found !");
            }
        }
        
        
        
    }
    
}
