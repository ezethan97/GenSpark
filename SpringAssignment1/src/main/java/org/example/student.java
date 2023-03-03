package org.example;

import java.util.List;

public class student {
    private int id;
    private String name;
    private List<Phone> ph;
    private Address add;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPh(List<Phone> ph) {
        this.ph = ph;
    }

    public void setAdd(Address add) {
        this.add = add;
    }
    @Override
    public String toString(){
        return String.format("student[id:%d name:%s phones:%s address:%s]", id, name, ph, add);
    }
}
