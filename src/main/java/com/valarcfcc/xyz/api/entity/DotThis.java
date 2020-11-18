package com.valarcfcc.xyz.api.entity;
import io.swagger.models.auth.In;

import static com.valarcfcc.xyz.utils.Common.println;
public class DotThis {
    void f(){
        println("DotThis.f()");
    }
    public class Inner{
        public DotThis outer(){
            return DotThis.this;
        }
    }
    public Inner inner (){
        return new Inner();
    }
    public static void main(String[] args){
        DotThis dotThis = new DotThis();
        DotThis.Inner dti = dotThis.inner();
        dti.outer().f();
        println(dti.outer().toString());
        println(dotThis.toString());
    }
}
