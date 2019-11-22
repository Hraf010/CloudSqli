package ma.sqli.factories;

import ma.sqli.entities.Machine;

public class MachineFactory {
    public Machine getMachineInstance(String name , String opSystem , double disk , double memory){
        return new Machine(name,opSystem,disk,memory);
    }
}
