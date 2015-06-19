/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package disk.scheduling;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Ibrahim Ali
 */
public class Points extends JPanel {

  private ArrayList<Integer> list = new ArrayList<>();
  
  public void setList(ArrayList<Integer> list ){
      this.list = list;
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.red);
    
    for (int i = 0; i < list.size(); i++) {
        int x2 = list.get(i);
        g2d.setColor(Color.blue);
        g2d.drawString(String.valueOf(list.get(i)),x2,15);
    }
    
    g2d.setColor(Color.red);
    
    int y = 20;
    for (int i = 1; i < list.size(); i++) {
      Dimension size = getSize();
      int x1 = list.get(i-1);
      int x2 = list.get(i);
      g2d.drawLine(x1,y,x2,y+20);
      y += 20;
    }
 }
}
