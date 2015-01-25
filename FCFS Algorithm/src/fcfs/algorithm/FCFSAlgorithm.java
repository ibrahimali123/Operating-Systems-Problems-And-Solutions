/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcfs.algorithm;

import java.util.Scanner;

/**
 *
 * @author Ibrahim Ali
 */
public class FCFSAlgorithm {

    
    public static void FCFS(String []processName , int []processArrivalTime , int []processBurstTime )
    {   
        int resposeTime = 0 ;
        double avg = 0;
        int [] response = new int[processName.length+1];
        
        System.out.println("order | process | response time");
        response[0] = 0;
        for (int i = 1; i <= processName.length ; i++) {
              resposeTime += processBurstTime[i-1];
              response[i] = resposeTime;
        }
        
        for (int i = 0; i < response.length-1 ; i++) {
            resposeTime = response[i] - processArrivalTime[i];
            System.out.println(i + "     | " + processName[i] + "      | " + resposeTime);
            avg += resposeTime;
        }
        
        System.out.println("average response time : " + avg/processName.length);
               
    }
    public static void main(String[] args) {
        // TODO code application logic here 
        String p;
        int t;
        
        System.out.println("Enter no of processes : ");
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();
        
        String [] processName = new String[n];
        int [] processArrivalTime = new int[n];
        int [] processBurstTime = new int[n];
        
        for (int i = 0; i < n ; i++) {
             System.out.println("process # " + i );
             reader.nextLine();
             System.out.println("Enter process Name : ");
             p = reader.nextLine();
              
             processName[i] = p;
             
             
             System.out.println("Enter process Arrival Time : ");
             t = reader.nextInt();
             processArrivalTime[i] = t;
             
             System.out.println("Enter process Burst Time : ");
             t = reader.nextInt();
             processBurstTime[i] = t;
            
             System.out.println("----------------------------------");
        }
        FCFS(processName,processArrivalTime,processBurstTime);
        
    }
    
}
