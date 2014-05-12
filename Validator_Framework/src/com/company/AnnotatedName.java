package com.company;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created with IntelliJ IDEA.
 * User: rashedul.hasan
 * Date: 4/13/14
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotatedName{
    String namePattern() default "[a-zA-Z\\s]+";
}
