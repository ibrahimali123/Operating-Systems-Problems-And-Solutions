/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reader.writer.problem.java;

import java.util.ArrayList;

/**
 *
 * @author Ibrahim Ali
 */

public class Writer extends Thread {

    ReadWriteBuffer buf;
    int nWriter;
    ArrayList<String [][]> elements = new ArrayList<String [][]> ();

	public Writer(ReadWriteBuffer buf,int wrtN) {
	this.buf = buf;
	this.nWriter=wrtN;
	}
	
    @Override
     public void run() {
	for (int i = 0; i < nWriter; i++){
	   buf.writer(elements.get(i)); 
	}
     }
	
	
}

