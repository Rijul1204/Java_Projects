package com.company;

/**
 * Created with IntelliJ IDEA.
 * User: rashedul.hasan
 * Date: 4/13/14
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {

    @AnnotatedName()
    private String name;
    @AnnotatedAge(minAge = 21)
    private int age;
    @AnnotatedEmail()
    private String Email;
    private int age1;

    User() {
    }

    User(String s) {
        name = s;
    }

    User(String name, String email, int age) {
        this.name = name;
        this.Email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }


}
