package com.company;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: rashedul.hasan
 * Date: 4/13/14
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Validator<T> {

    T obj;

    Validator() {
        obj = null;
    }

    Validator(T obj) {
        this.obj = obj;
    }


    public boolean validateAll() {
        Field[] fields = obj.getClass().getDeclaredFields();
        boolean returnFlag = true;
        for (Field f : fields) {
            if (validateField(f) == false) {
                System.out.format("Field %S failed\n", f.getName());
                returnFlag = false;
            }
        }
        return returnFlag;
    }

    public boolean validateAllEmail() {
        Field[] fields = obj.getClass().getDeclaredFields();
        boolean returnFlag = true;
        for (Field f : fields) {
            if (f.isAnnotationPresent(AnnotatedEmail.class) && !validateEmail(f)) {
                returnFlag = false;
            }
        }
        return returnFlag;
    }

    public boolean validateField(Field f) {

        f.setAccessible(true);
        if (f.isAnnotationPresent(AnnotatedName.class)) {
            return validateName(f);
        }
        if (f.isAnnotationPresent(AnnotatedAge.class)) {
            return validateAge(f);
        }
        if (f.isAnnotationPresent(AnnotatedEmail.class)) {
            return validateEmail(f);
        }

        return true;
    }

    public boolean validateName(Field f) {

        String name = null;
        try {
            name = (String) f.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        String namePattern = f.getAnnotation(AnnotatedName.class).namePattern();
        if (!name.matches(namePattern)) {
            System.out.println("Sorry Name Pattern doesn't match");
            return false;
        }

        return true;
    }

    public boolean validateAge(Field f) {

        int age = 0;
        try {
            f.setAccessible(true);
            age = (Integer) f.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        int maxAge = f.getAnnotation(AnnotatedAge.class).maxAge();
        int minAge = f.getAnnotation(AnnotatedAge.class).minAge();
        if (age > maxAge || age < minAge) {
            System.out.println("Sorry age limit crossed");
        }

        return true;
    }

    public boolean validateEmail(Field f) {

        String email = null;
        try {
            f.setAccessible(true);
            email = (String) f.get(obj);
        } catch (IllegalAccessException e) {
            return true;
        }
        String namePattern = f.getAnnotation(AnnotatedEmail.class).namePattern();
        if (!email.matches(namePattern)) {
            System.out.println("Sorry E-mail Pattern doesn't match");
            return false;
        }
        return true;
    }
}
