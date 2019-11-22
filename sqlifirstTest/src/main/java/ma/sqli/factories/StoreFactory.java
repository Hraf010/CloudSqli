package ma.sqli.factories;

import ma.sqli.entities.Store;

public class StoreFactory {
    public Store getStoreInstance(String name){
        return new Store(name);
    }
}
