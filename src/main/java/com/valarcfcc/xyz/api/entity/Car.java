package com.valarcfcc.xyz.api.entity;
import static com.valarcfcc.xyz.utils.Common.println;
public class Car {
    public  void run(){
        println("Car run!");
    }
    public void print(String... names){
        for (String name:names) {
            println(name);
        }
    }
}
