package edu.escuelaing;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class Reflexion {
    private static void printMembers(Member[] mbrs, String s) {
        System.out.format("%s:%n", s);
        for (Member mbr : mbrs) {
            if (mbr instanceof Field)
                System.out.format(" %s%n", ((Field) mbr).toGenericString());
            else if (mbr instanceof Constructor)
                System.out.format(" %s%n", ((Constructor) mbr).toGenericString());
            else if (mbr instanceof Method)
                System.out.format(" %s%n", ((Method) mbr).toGenericString());
        }
        if (mbrs.length == 0) {
            System.out.format(" -- No %s --%n", s);
            System.out.format("%n");
        }
    }

    public static void main(String[] args) {
        printMembers(String.class.getConstructors(), "constructores");
        printMembers("Hola".getClass().getDeclaredFields(), "constructores");
        printMembers("Hola".getClass().getDeclaredMethods(), "constructores");
    }

}
