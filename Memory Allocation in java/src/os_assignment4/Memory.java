/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os_assignment4;

import java.util.*;

/**
 *
 * @author lenovo
 */
class node {

    public int memory_address;
    public int partition_size;
    public int freeSize;
    public String partition_Status;
}

public class Memory {

    public ArrayList<node> nodeList = new ArrayList<node>();
    boolean flag = true;

    public int memoryAllocate() {
        Scanner read = new Scanner(System.in);

        int counter = 0;
        if (flag == true) {
            int sum = 1;
            System.out.println("Enter number of partitions");
            int noOfPartitions = read.nextInt();
            for (int i = 0; i < noOfPartitions; i++) {
                node nOne = new node();
                nOne.memory_address = sum;
                System.out.println("the size of memory");
                nOne.partition_size = read.nextInt();
                sum += nOne.partition_size;
                nOne.partition_Status = "free";
                nodeList.add(nOne);
            }
            flag = false;
        }

        System.out.println("the size of process");
        int sizeProcess = read.nextInt();
        System.out.println("choose polices");
        System.out.println("1-First Fit");
        System.out.println("2-Best Fit");
        System.out.println("3-Worst Fit");
        int check = read.nextInt();

        if (check == 1) {
            for (int i = 0; i < nodeList.size(); i++) {
                node item = nodeList.get(i);
                if (item.partition_Status.equals("free")) {
                    if (item.partition_size >= sizeProcess) {
                        item.partition_Status = "occupied";
                        item.freeSize = item.partition_size - sizeProcess;
                    }
                    System.out.println("occupied in partion no. " + i + 1);
                    return nodeList.get(i).memory_address;
                }
            }
        } else if (check == 2) {
            int curr = -1;
            node bestNode = new node();
            for (int i = 0; i < nodeList.size(); i++) {
                node item = nodeList.get(i);
                if (item.partition_Status.equals("free")) {
                    if (item.partition_size >= sizeProcess) {
                        if (i == 0) {
                            curr = i;
                            bestNode = item;
                        }
                        if (item.partition_size < bestNode.partition_size) {
                            curr = i;
                            bestNode = item;
                        }
                    }

                }
            }
            if (curr > 0) {
                nodeList.get(curr).freeSize = nodeList.get(curr).partition_size - sizeProcess;
                nodeList.get(curr).partition_Status = "occupied";
                return nodeList.get(curr).memory_address;
            }
        } else if (check == 3) {
            int curr = -1;
            node bestNode = new node();
            for (int i = 0; i < nodeList.size(); i++) {
                node item = nodeList.get(i);
                if (item.partition_Status.equals("free")) {
                    if (item.partition_size >= sizeProcess) {
                        if (i == 0) {
                            curr = i;
                            bestNode = item;
                        }
                        if (item.partition_size > bestNode.partition_size) {
                            curr = i;
                            bestNode = item;
                        }
                    }

                }
            }
            if (curr > 0) {
                nodeList.get(curr).freeSize = nodeList.get(curr).partition_size - sizeProcess;
                nodeList.get(curr).partition_Status = "occupied";
                return nodeList.get(curr).memory_address;
            }
        }
        System.out.println("something went wrong!\n\n");
        return -1;
    }

    public boolean Deallocate() {
        Scanner read = new Scanner(System.in);

        System.out.println("enter the wanted adress");
        int check = read.nextInt();

        for (int i = 0; i < nodeList.size(); i++) {
            node item = nodeList.get(i);

            if (item.memory_address == check) {

                if (item.partition_Status.equals("free")) {
                    System.out.println("the partition is already free\n\n");
                } else {
                    item.partition_Status = "free";
                }
                return true;
            }

        }
        System.out.println("something went wrong .\n\n");
        return false;
    }

    //1- 50 free
    //2- 70 occup 20 free
    //3- 70 free
    //4- 30 occup 10 free
    //5- 40 occup 20 free
    //6- 30 free
    //7- 20 free
    public void Defragmentation() {
        int free = 0;
        for (int i = 0; i < nodeList.size(); i++) {
            node item = nodeList.get(i);
            if (item.freeSize > 0) {
                free += item.freeSize;
                item.partition_size -= item.freeSize;
                item.freeSize = 0;
            } else if (item.partition_Status.equals("free")) {
                free += item.partition_size;
                nodeList.remove(i);
                i--;
            }
            if ((i + 1) != nodeList.size()) {
                nodeList.get(i + 1).memory_address -= free;
            }
            if (item.partition_Status.equals("free")) {

            }

        }
        node nitem = new node();
        nitem.partition_size = free;
        nitem.freeSize = free;
        nitem.partition_Status = "free";
        nitem.memory_address = (nodeList.get(nodeList.size() - 1).memory_address) + (nodeList.get(nodeList.size() - 1).partition_size);
        nodeList.add(nitem);
        System.out.println("Defragmentation done. ");
    }

    public void print() {
        for (int i = 0; i < nodeList.size(); i++) {
            node item = nodeList.get(i);
            System.out.println((i + 1) + "- adress : " + item.memory_address);
            System.out.println(" - size : " + item.partition_size);
            System.out.println(" - partition_Status : " + item.partition_Status + "\n");
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Memory obj = new Memory();
        Scanner read = new Scanner(System.in);
        int check = -1;
        for (; check != 0;) {
            System.out.println("choose the next operation");
            System.out.println("1- memory allocate.");
            System.out.println("2- memory deallocate.");
            System.out.println("3- defragmentation.");
            System.out.println("4- Print.");
            check = read.nextInt();
            if (check == 1) {
                System.out.println(obj.memoryAllocate());
            } else if (check == 2) {
                System.out.println(obj.Deallocate());
            } else if (check == 3) {
                obj.Defragmentation();
            } else if (check == 4) {
                obj.print();
            }

        }
    }

}
