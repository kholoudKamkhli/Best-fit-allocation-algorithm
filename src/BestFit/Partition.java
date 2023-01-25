package BestFit;

public class Partition {
    public int name;
    public String n = "partition";
    public Integer size;
    Process process;

    public boolean occupied = false;
    public boolean externalFragment = false;


    public Partition(int name, int size) {
        this.name = name;
        this.size = size;
        this.n+=Integer.toString(name);
    }
}
