/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcv.fx_movietable;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author shin_
 */
public class Movie 
{
    private SimpleStringProperty title;
    private SimpleStringProperty genre;
    private int year;
    private LocalDate rebirth;

    public Movie(String title, String genre, int year, LocalDate rebirth) {
        this.title = new SimpleStringProperty(title);
        this.genre = new SimpleStringProperty(genre);
        this.year = year;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title = new SimpleStringProperty(title);
    }

    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String genre) {
        this.genre = new SimpleStringProperty(genre);
    }

    public int getYear() {
        return year;
    }
    
    public int getYearsOld()
    {
        return LocalDate.now().getYear() - year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDate getRebirth() {return rebirth;}
    public void setRebirth(LocalDate rebirth) {this.rebirth = rebirth;}
    
    @Override
    public String toString()
    {
        String rep = "";
        rep += this.getTitle() + " (" + this.getYear() + ")";
        return rep;
    }
    
    
    
}
