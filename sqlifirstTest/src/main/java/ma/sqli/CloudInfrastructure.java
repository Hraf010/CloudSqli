package ma.sqli;

import ma.sqli.entities.File;
import ma.sqli.entities.Machine;
import ma.sqli.entities.Store;
import ma.sqli.exceptions.CreateStoreException;
import ma.sqli.exceptions.MachineStateException;
import ma.sqli.factories.FileFactory;
import ma.sqli.factories.MachineFactory;
import ma.sqli.factories.StoreFactory;

import javax.crypto.Mac;
import java.util.*;

public class CloudInfrastructure {
    private FileFactory fileFactory;
    private StoreFactory storeFactory ;
    private MachineFactory machineFactory;
    private Map<Store, List<File>> listMap;
    private List<Machine> machines;
    private double globalUsedDisk;
    private double globalUsedMemory;

    public CloudInfrastructure() {
        fileFactory = new FileFactory();
        storeFactory = new StoreFactory();
        machineFactory = new MachineFactory();
        listMap = new HashMap<Store, List<File>>();
        machines = new ArrayList<>();
    }

    public void createStore(String name) {
        if(searchStore(name)!=null)
           throw new CreateStoreException("This Store already exist");
        else
            listMap.put(storeFactory.getStoreInstance(name),new ArrayList<File>());
    }

    public Store searchStore(String newStore){
        for(Store store : listMap.keySet())
            if(store.getName().equals(newStore))
                return store;

            return null;
    }



    public void uploadDocument(String name, String...  files) {
        final Store store = searchStore(name);
        if(store!=null)
            Arrays.stream(files).
                    forEach(file->{
                        listMap.get(store).add(fileFactory.getFileInstance(file));
                        store.setSize(store.getSize()+0.100);
                        globalUsedDisk+=0.100;
                    });
    }

    public String listStores() {
        StringBuilder resultats = new StringBuilder();
        for(Store store : listMap.keySet()){
            resultats.append(store.getName()+":");//myFiles:empty||myImages:picture.jpeg, profile.png
            List<File> files = listMap.get(store);
            if(files.isEmpty())
                resultats.append("empty");
            else {
                for(File file :files){
                    resultats.append(file.getName()+", ");
                }
                resultats.delete(resultats.length()-2,resultats.length());
            }
            resultats.append("||");
        }
        resultats.delete(resultats.length()-2,resultats.length());
        return String.valueOf(resultats);
    }

    public void deleteStore(String name) {
        Store store = searchStore(name);
        listMap.remove(store);
    }

    public void emptyStore(String name) {
        Store store = searchStore(name);
        if(listMap.containsKey(store)){
            listMap.get(store).clear();
            globalUsedDisk-=store.getSize();
        }

    }

    public void createMachine(String name, String opSystem, String disk, String memory) {
        double newDisk = Double.parseDouble(disk.substring(0,disk.length()-2));
        double newMemory = Double.parseDouble(memory.substring(0,memory.length()-2));
        Machine machine = machineFactory.getMachineInstance(name,opSystem,newDisk,newMemory);
        machine.setUsedMemory(0);
        globalUsedDisk+=machine.getDiskSize();
        machines.add(machine);
    }

    public String listMachines() {
        StringBuilder resultats = new StringBuilder();
        for (Machine machine :machines){
            resultats.append(machine.getName()+":"+machine.getState()+"||");
        }
        resultats.delete(resultats.length()-2,resultats.length());
        return String.valueOf(resultats);
    }

    public void startMachine(String machine2) {
        Machine machine = searchMachine(machine2);
        if(machine!=null){
            if(machine.getState()=="running")
                throw new MachineStateException();

            machine.setState("running");
            machine.setUsedMemory(machine.getMemory());
            globalUsedMemory+=machine.getMemory();
        }

    }

    public Machine searchMachine(String name){
        for(Machine machine : machines){
            if(machine.getName().equals(name))
                return machine;
        }
        return null;
    }
    public void stopMachine(String machine1) {
        Machine machine = searchMachine(machine1);
        if(machine!=null){
            machine.setState("stopped");
            machine.setUsedMemory(0);
            globalUsedMemory-=machine.getMemory();
        }

    }

    public Double usedMemory(String machine1) {
        Machine machine = searchMachine(machine1);
       return machine.getUsedMemory();
    }

    public Double usedDisk(String machine1) {
        Machine machine = searchMachine(machine1);
        if(machine!=null)
        return machine.getDiskSize();
        else {
            Store store = searchStore(machine1);
            return store.getSize();
        }
    }

    public Double globalUsedDisk() {
        return globalUsedDisk;
    }

    public Double globalUsedMemory() {
        return globalUsedMemory;
    }
}
