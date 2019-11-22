package ma.sqli.entities;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Store {
    private String name;
    private double size;

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Store(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
       String storeName = ((Store)o).getName();
       return this.name.equals(storeName);
    }

    @Override
    public String toString() {
        return name;
    }


}
