package BestFit;

public class Process {
    public String name;
    public int size;
    public boolean gotPlace = false;
    Partition p;

    public Process(String name , int size) {
        this.name = name;
        this.size = size;
    }
}
