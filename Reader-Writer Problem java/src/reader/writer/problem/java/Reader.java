package reader.writer.problem.java;

/**
 *
 * @author Ibrahim Ali
 */
import java.util.ArrayList;


public class Reader extends Thread {
 
    ReadWriteBuffer buf;
    int nReader;
    ArrayList<String> elements = new ArrayList<String> ();
    
    public Reader(ReadWriteBuffer buf,int redN) {
	 this.buf = buf;
	 this.nReader=redN;
    }
     
    @Override
    public void run() {
	for (int i = 0; i < nReader; i++){
	     buf.reader(elements.get(i));
         }
    }
			
}