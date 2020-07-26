package com.valarcfcc.xyz.api;

import com.valarcfcc.xyz.DTO.DogDTO;
import com.valarcfcc.xyz.DTO.TestDTO;
import com.valarcfcc.xyz.api.entity.Dog;
import com.valarcfcc.xyz.api.entity.User;
import com.valarcfcc.xyz.utils.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.*;
import java.util.*;

/**
 * @Auther: valarcfcc
 * @Date: 2020/7/23 19:09
 * @Description:
 */
@SpringBootTest
@Slf4j
public class TestReflect {

    @Test
    public void objToXMLTest() {
        DogDTO dogDTO = new DogDTO();

        Dog dog = new Dog();
        dog.setName("来福");
        dog.setId("1");
        dog.setNum(1);

        ArrayList<Dog> list = new ArrayList<>();
        list.add(dog);
        list.add(dog);
        list.forEach(System.out::println);
        ArrayList<ArrayList> listList = new ArrayList<>();
        listList.add(list);
        listList.add(list);
        TestDTO testDTO = new TestDTO();
        User user = new User();
        List<User> userList = new ArrayList<>();
        userList.add(user);
        testDTO.setUserList(userList);
        dogDTO.setListList(Collections.singletonList(testDTO));
        dogDTO.setDog(dog);
        dogDTO.setDogList(list);
        dogDTO.setName("小强");
        Map map = new HashMap();
        map.put("dog1", dog);
        map.put("dogList1", listList);
        map.put("str", "ser");

        dogDTO.setDogMap(map);

        try {
            String str = XmlUtils.objectToXML(dogDTO, "s");
            System.out.println(str);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void test(TestReflect p0,
                            List<List> p1,
                            Map<String, List> p2,
                            List<String>[] p3,
                            Map<String, TestReflect>[] p4,
                            List<? extends TestReflect> p5,
                            Map<? extends TestReflect, ? super TestReflect> p6
                            //T p7
    ) {

    }

    @Test
    public void testTest() {

        Method[] methods = TestReflect.class.getMethods();

        for (int i = 0; i < methods.length; i++) {
            Method oneMethod = methods[i];
            System.out.println("--------" + methods[i].getName() + "--------");
            if (oneMethod.getName().equals("test")) {
                Type[] types = oneMethod.getGenericParameterTypes();

                //第一个参数，TestReflect p0
                Class type0 = (Class) types[0];
                System.out.println("type0:" + type0.getName());

                //第二个参数，List<TestReflect> p1
                Type type1 = types[1];
                Type[] parameterizedType1 = ((ParameterizedType) type1).getActualTypeArguments();
                Class parameterizedType1_0 = (Class) parameterizedType1[0];
                System.out.println("parameterizedType1_0:" + parameterizedType1_0.getName());

                //第三个参数，Map<String,TestReflect> p2
                Type type2 = types[2];
                Type[] parameterizedType2 = ((ParameterizedType) type2).getActualTypeArguments();
                Class parameterizedType2_0 = (Class) parameterizedType2[0];
                System.out.println("parameterizedType2_0:" + parameterizedType2_0.getName());
                Class parameterizedType2_1 = (Class) parameterizedType2[1];
                System.out.println("parameterizedType2_1:" + parameterizedType2_1.getName());


                //第四个参数，List<String>[] p3
                Type type3 = types[3];
                Type genericArrayType3 = ((GenericArrayType) type3).getGenericComponentType();
                ParameterizedType parameterizedType3 = (ParameterizedType) genericArrayType3;
                Type[] parameterizedType3Arr = parameterizedType3.getActualTypeArguments();
                Class class3 = (Class) parameterizedType3Arr[0];
                System.out.println("class3:" + class3.getName());

                //第五个参数，Map<String,TestReflect>[] p4
                Type type4 = types[4];
                Type genericArrayType4 = ((GenericArrayType) type4).getGenericComponentType();
                ParameterizedType parameterizedType4 = (ParameterizedType) genericArrayType4;
                Type[] parameterizedType4Arr = parameterizedType4.getActualTypeArguments();
                Class class4_0 = (Class) parameterizedType4Arr[0];
                System.out.println("class4_0:" + class4_0.getName());
                Class class4_1 = (Class) parameterizedType4Arr[1];
                System.out.println("class4_1:" + class4_1.getName());


                //第六个参数，List<? extends TestReflect> p5
                Type type5 = types[5];
                Type[] parameterizedType5 = ((ParameterizedType) type5).getActualTypeArguments();
                Type[] parameterizedType5_0_upper = ((WildcardType) parameterizedType5[0]).getUpperBounds();
                Type[] parameterizedType5_0_lower = ((WildcardType) parameterizedType5[0]).getLowerBounds();

                //第七个参数，Map<? extends TestReflect,? super TestReflect> p6
                Type type6 = types[6];
                Type[] parameterizedType6 = ((ParameterizedType) type6).getActualTypeArguments();
                Type[] parameterizedType6_0_upper = ((WildcardType) parameterizedType6[0]).getUpperBounds();
                Type[] parameterizedType6_0_lower = ((WildcardType) parameterizedType6[0]).getLowerBounds();
                Type[] parameterizedType6_1_upper = ((WildcardType) parameterizedType6[1]).getUpperBounds();
                Type[] parameterizedType6_1_lower = ((WildcardType) parameterizedType6[1]).getLowerBounds();

            }


        }

    }

}