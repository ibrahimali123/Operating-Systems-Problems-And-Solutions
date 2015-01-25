package reader.writer.problem.java;

/**
 *
 * @author ibrahim ali
 */
public class semaphore {

     static int value = 0;
     
     ReadWriteBuffer buff = new ReadWriteBuffer();
	 
     public synchronized void P(String name,int Read_or_Write) {
	if(((Read_or_Write==1)&&((buff.Read_Buffer.size()!=0)||(buff.Write_Buffer.size()!=0)))||((Read_or_Write==0)&&(buff.Write_Buffer.size()!=0))){
	    
            value--;
	    if (value < 0)
		try {
		    System.out.println( name + " Blocked");
		    wait();
		} 
                catch (InterruptedException e) {
		}
	}
    }

    public synchronized void V() {
		
	if (value <0){
	    value++;
	    notify();
	}
    }
}
