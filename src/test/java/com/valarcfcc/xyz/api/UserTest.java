package com.valarcfcc.xyz.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserTest {



    @Test
    public void typeTest() {
        Type type3 = new GenericArrayType() {
            @Override
            public Type getGenericComponentType() {
                return null;
            }
        };
        Type genericArrayType3 = ((GenericArrayType) type3).getGenericComponentType();
        ParameterizedType parameterizedType3 = (ParameterizedType) genericArrayType3;
        Type[] parameterizedType3Arr = parameterizedType3.getActualTypeArguments();
        Class class3 = (Class) parameterizedType3Arr[0];
        System.out.println("class3:" + class3.getName());

    }

}
