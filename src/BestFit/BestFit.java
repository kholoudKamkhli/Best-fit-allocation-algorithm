package BestFit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class BestFit {
    ArrayList<Partition> blocks = new ArrayList<Partition>();
    ArrayList<Process> processes = new ArrayList<Process>();
    ArrayList<Process> notTakenprocesses = new ArrayList<Process>();
    int numOfPartitions;
    int indexOfProcessNotSet = -1;
    int numOfProcesses;
    int lastPart;
    public BestFit(ArrayList<Partition> blocks,ArrayList<Process> processes,int numOfPartitions,int numOfProcesses){
        this.blocks = blocks;
        this.numOfPartitions = numOfPartitions;
        this.processes = processes;
        this.numOfProcesses = numOfProcesses;
        lastPart = blocks.size();
    }
    Comparator<Partition> cmp = new Comparator<Partition>() {
        public int compare(Partition p1, Partition p2) {
            return p1.size.compareTo(p2.size);
        }
    };
    public void setPartitions() {
        for (Process p : processes) {
            blocks.sort(cmp);
            boolean flag = false;
            for (var par : blocks) {
                if (par.size >= p.size  && par.occupied==false) {
                    par.occupied = true;
                    //par.externalFragment = true;
                    flag = true;
                    par.process = p;
                    int rem = par.size - p.size;
                    par.size = p.size;
                    if (rem > 0) {
                        Partition newPart = new Partition(lastPart,rem);
                        newPart.externalFragment = true;
                        blocks.add(newPart);
                        lastPart++;

                    }
                    break;
                }
            }
            if (!flag)
                notTakenprocesses.add(p);
        }
    }
    public void compact() {
        int size = 0;
        ArrayList<Partition> toBeRemoved = new ArrayList<>();
        for (var x : blocks) {
            if (x.occupied == false) {
                size += x.size;
                toBeRemoved.add(x);
            }
        }
        blocks.add(new Partition(lastPart, size));
        lastPart++;
        blocks.removeAll(toBeRemoved);
        processes= (ArrayList) notTakenprocesses.clone();
        notTakenprocesses.clear();
        setPartitions();
    }
    public void print(){
        // add rest of external fragmentations
        for (var x : blocks) {
            if(x.occupied == true)
                System.out.println(x.n+" "+"("+x.size+") => "+ x.process.name);
            else {
                System.out.println(x.n+" "+"("+x.size+") => External Fragement");
            }
        }

        // printing not allocated processes
        if(notTakenprocesses.size()>0){
            System.out.println("--------------------------");
            System.out.println("List of Process not Allocated : ");
            int count=1;
            for (var x : notTakenprocesses) {
                System.out.println(count +"."+x.name + " (" + x.size + ")" + " Not Allocated");
            }
        }
    }
}


