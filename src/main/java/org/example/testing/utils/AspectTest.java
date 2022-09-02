package org.example.testing.utils;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AspectTest {

    @Before("execution(* *(..))")
    public void before(){

    }
}
