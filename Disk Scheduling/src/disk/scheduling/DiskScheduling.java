package disk.scheduling;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author Ibrahim Ali
 */
public class DiskScheduling {
    Scanner in = new Scanner(System.in);
    int start , numberOfCylinders , algoithmType , direction;
    
    public ArrayList<Integer> getcylindersRequests() throws FileNotFoundException, IOException{
        ArrayList<Integer> list = new ArrayList<>();
        
        Path filePath = Paths.get("input.txt");
        Scanner scanner = new Scanner(filePath);
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            } else {
                scanner.next();
            }
        }
        return list;
    }
    public void chooseAlgorithm(){
        System.out.println("choose [1] to Implement FCFS Algorithm : ");
        System.out.println("choose [2] to Implement SSTF Algorithm : ");
        System.out.println("choose [3] to Implement SCAN Algorithm : ");
        System.out.println("choose [4] to Implement C-SCAN Algorithm : ");
        System.out.println("choose [5] to Implement LOOK Algorithm : ");
        System.out.println("choose [6] to Implement C-LOOK Algorithm : ");
        System.out.println("choose [7] to EXIT : ");
    }
    public void ShowOutput(ArrayList<Integer> result , int totalSeek){
        System.out.println("-------------- The Sequence is --------------------");
        for (Iterator<Integer> iterator = result.iterator(); iterator.hasNext();) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        System.out.println("The total seek distance is : " + totalSeek);
        System.out.println("---------------------------------------------------");
    }
    public void init() throws IOException{
        System.out.println("Enter number of cylinders that disk drive has : ");
        numberOfCylinders = in.nextInt();
        System.out.println("Enter the current head position : ");        
        start = in.nextInt();
        
        ArrayList<Integer> list = getcylindersRequests();
        
        chooseAlgorithm();
        algoithmType = in.nextInt();
        while(algoithmType != 7){
            if (algoithmType == 1) {
                DiskScheduling_Algorithms obj = new FCFS(list, start, -1 ,numberOfCylinders - 1);
                ShowOutput(obj.sequenceOFHeadMovement(), obj.totalHeadMovement());
                ShowWindow(obj.sequenceOFHeadMovement(),obj.totalHeadMovement());
            }
            else if (algoithmType == 2) {
                DiskScheduling_Algorithms obj = new SSTF(list, start, -1 ,numberOfCylinders - 1);
                ShowOutput(obj.sequenceOFHeadMovement(), obj.totalHeadMovement());
                ShowWindow(obj.sequenceOFHeadMovement(),obj.totalHeadMovement());
            }
            else if (algoithmType == 3) {
                System.out.println("Enter the Direction [1] left [2] right : ");
                direction = in.nextInt();
                DiskScheduling_Algorithms obj = new SCAN(list, start, direction , numberOfCylinders - 1);
                ShowOutput(obj.sequenceOFHeadMovement(), obj.totalHeadMovement());
                ShowWindow(obj.sequenceOFHeadMovement(),obj.totalHeadMovement());
            }
            else if (algoithmType == 4) {
                System.out.println("Enter the Direction [1] left [2] right : ");
                direction = in.nextInt();
                DiskScheduling_Algorithms obj = new CSCAN(list, start, direction , numberOfCylinders - 1);
                ShowOutput(obj.sequenceOFHeadMovement(), obj.totalHeadMovement());
                ShowWindow(obj.sequenceOFHeadMovement(),obj.totalHeadMovement());
            }
            else if (algoithmType == 5) {
                System.out.println("Enter the Direction [1] left [2] right : ");
                direction = in.nextInt();
                DiskScheduling_Algorithms obj = new LOOK(list, start, direction , numberOfCylinders - 1);
                ShowOutput(obj.sequenceOFHeadMovement(), obj.totalHeadMovement());
                ShowWindow(obj.sequenceOFHeadMovement(),obj.totalHeadMovement());
            }
            else if (algoithmType == 6) {
                System.out.println("Enter the Direction [1] left [2] right : ");
                direction = in.nextInt();
                DiskScheduling_Algorithms obj = new CLOOK(list, start, direction , numberOfCylinders - 1);
                ShowOutput(obj.sequenceOFHeadMovement(), obj.totalHeadMovement());
                ShowWindow(obj.sequenceOFHeadMovement(),obj.totalHeadMovement());
            }
            chooseAlgorithm();
            algoithmType = in.nextInt();
        }
            
    }
    
    public void ShowWindow(ArrayList<Integer> list , int total){
        JFrame frame = new JFrame("Simulation Of The Head Movements");
        // Add a window listner for close button
        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        // This is an empty content area in the frame
        JLabel jlbempty = new JLabel();
        frame.setPreferredSize(new Dimension(1000, 400));
        frame.getContentPane().add(jlbempty, BorderLayout.CENTER);
        //jlbempty.setText("The total seek distance is " + total);
        //jlbempty.setLocation(2,2);
        //JPanel main = new JPanel();
        //main.add(jlbempty);
        //frame.add(jlbempty);  
        Points p = new Points();
        p.setList(list);
        frame.add(p);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        DiskScheduling main = new DiskScheduling();
        //main.ShowWindow();
        try {
            main.init();
        } catch (IOException ex) {
            Logger.getLogger(DiskScheduling.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}