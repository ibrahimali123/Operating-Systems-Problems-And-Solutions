package reader.writer.problem.java;

import java.util.LinkedList;
import java.util.Queue;
 
class ReadWriteBuffer {
        
    
    String Text = "";
    ReadWriteBuffer(String buff){
		Text = buff;
    }
    
    ReadWriteBuffer(){}
		
    public static Queue<String> Read_Buffer = new LinkedList<String>();
    public static Queue<String [][]> Write_Buffer = new LinkedList<String [][]>();

    public static semaphore sema = new semaphore();

    public void reader(String Process) {
            System.out.println( Process + " Start Reading");
            sema.P(Process,0);
            Read_Buffer.add(Process);
                    
            System.out.println( Process + " Read: " + Text);
                    
            System.out.println(Process + " Finished Reading");
            Read_Buffer.poll();
                    
            sema.V();	
     }
		
    public void writer(String [][] Process) {
	    System.out.println(Process[0][0]+" Start Writing");
	    sema.P(Process[0][0],1);
	    Write_Buffer.add(Process);
			
	    Text += Process[0][1];
	    System.out.println(Process[0][0]+" Finished Writing");			
	    Write_Buffer.poll();

	    sema.V();
    }
}

 