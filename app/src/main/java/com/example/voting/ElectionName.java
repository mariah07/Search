package com.example.voting;

public class ElectionName {

    public String name;
    public String date;

    public ElectionName(){}

    public ElectionName(String name, String date){
        this.name = name;
        this.date = date;
    }

    public String getElectionName(){
        return name;
    }

    public void setElectionName(String name){
        this.name = name;
    }
}
