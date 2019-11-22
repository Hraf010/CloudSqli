package ma.sqli.entities;

public class Machine {
    private String name;
    private String opSystem;
    private double diskSize;
    private double memory;
    private double usedMemory;
    private String state;

    public Machine(String name, String opSystem, double diskSize, double memory) {
        this.name = name;
        this.opSystem = opSystem;
        this.diskSize = diskSize;
        this.memory = memory;
        this.state = "inactive";
    }

    public double getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(double usedMemory) {
        this.usedMemory = usedMemory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpSystem() {
        return opSystem;
    }

    public void setOpSystem(String opSystem) {
        this.opSystem = opSystem;
    }

    public double getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(double diskSize) {
        this.diskSize = diskSize;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
