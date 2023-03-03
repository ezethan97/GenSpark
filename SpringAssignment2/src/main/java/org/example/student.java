package org.example;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class student {
    private int id;
    private String name;
    private List<Phone> ph;
    private Address add;

    public student(int id, String name, List<Phone> ph, Address add){
        this.id = id;
        this.name = name;
        this.ph = ph;
        this.add = add;
    }

    @Override
    public String toString(){
        return String.format("student[id:%d name:%s phones:%s address:%s]", id, name, ph, add);
    }
}
