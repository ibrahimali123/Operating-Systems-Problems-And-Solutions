 
package reader.writer.problem.java;

import java.util.Scanner;

 
public class ReaderWriterProblemJava {  
    
    public static void main(String[] args) { 
        Scanner read=new Scanner(System.in); 
        Scanner read2=new Scanner(System.in);
        
        System.out.println("Enter Initial Buffer Content");
        ReadWriteBuffer buf = new ReadWriteBuffer(read.nextLine());
			
	System.out.println("Enter Number of reader threads");
	int nReader = read2.nextInt();

	System.out.println("Enter Number of writer threads");
	int nWriter = read2.nextInt();
			
	Reader R = new Reader(buf,nReader);
	System.out.println("reader threads : ");
	for(int i=0;i<nReader;i++){
		R.elements.add(read.nextLine());
	}
	
        Writer W = new Writer(buf,nWriter);
	String [][] arr;
			
        System.out.println("writer threads & texts : ");
	for(int i=0;i<nWriter;i++){
		arr = new String [1][2];
		arr[0][0]=read.nextLine();
		arr[0][1]=read.nextLine();

		W.elements.add(arr);
	}
			
	R.start();
	W.start();        
    }
}
