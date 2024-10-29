package com.jcv.fx_movietable;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

public class HorrorCharacter
{
    private SimpleStringProperty name;
    private SimpleStringProperty subtype;
    private int age;
    private LocalDate rebirth;

    public HorrorCharacter(String name, String subtype, int age, LocalDate rebirth) {
        this.name = new SimpleStringProperty(name);
        this.subtype = new SimpleStringProperty(subtype);
        this.age = age;
        this.rebirth = rebirth;
    }
    public HorrorCharacter() {

    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getSubtype() {
        return subtype.get();
    }

    public void setSubtype(String subtype) {
        this.subtype = new SimpleStringProperty(subtype);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRebirth() {return rebirth;}
    public void setRebirth(LocalDate rebirth) {this.rebirth = rebirth;}

    public String toName(){
        String rep = "";
        rep += this.getName() ;
        return rep;
    }

    public String toAge(){
        String rep = "";
        rep += this.getAge() ;
        return rep;
    }

    public String toSubtype(){
        String rep = "";
        rep += this.getSubtype() ;
        return rep;
    }

    public String toRebirth(){
        String rep = "";
        rep += this.getRebirth() ;
        return rep;
    }


    
}
