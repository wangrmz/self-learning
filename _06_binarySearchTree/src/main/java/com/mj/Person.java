package com.mj;
/**
@author wrmeng
@create 2023-29-13:46
**/

public class Person implements java.lang.Comparable<Person>{
    private  int age;

    public Person(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person person) {
        return age-person.age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
