package com.crudapp.learn.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    private int rollNo;
    private String name;
    private int age;
    private String email;

    private String address;
    private String subject;
    private boolean isDeleted;

    public Student(int rollNo, String name, int age, String email, String subject, String address, int isDeleted) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.email = email;
        this.subject = subject;
        this.address = address;
        this.isDeleted=false;
    }

    public Student() {

    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getSubject() {
        return subject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
