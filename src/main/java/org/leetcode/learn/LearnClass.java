package org.leetcode.learn;

import java.io.IOException;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;


interface GardenerProtocol {
    void doSomething1();

    default void doSomething2() {
        System.out.println("do something 2");
    }

    static void doSomething3() {
        System.out.println("do something 3");
    }

    private void doSomething() {
        System.out.println("do something 4");
    }
}

class ClassicGardener implements GardenerProtocol {

    @Override
    public void doSomething1() {

    }

    @Override
    public void doSomething2() {
        GardenerProtocol.super.doSomething2();
    }
}

abstract class Trader {
    Trader() {
        Object obj = new Object();
        System.out.println("init trader");
    }

    abstract void doSomething1();

    void doSomething2() {
        System.out.println("asdf");
    }
}

class SeaTurtle extends Trader {
    SeaTurtle() {
        super();
        System.out.println("init sea turtle");
    }

    @Override
    void doSomething1() {
        System.out.println("sea turtle");
    }

    int wtf1(int a) {
        return 0;
    }

    void wtf1() {

    }
}

class A {
    private int i = 12;

    class B {
        static int sbi = 13;

        private int pbi = 114514;

        void print() throws IOException {
            System.out.println(i);
            System.out.println(sbi);
            throw new IOException("dude");
        }
    }

    void run() {
        try {
            B b = new B();
            b.print();
            System.out.println(b.pbi);
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("do something!");
        }
    }
}

class Node<T> {
    public T data;

    public Node(T data) {
        this.data = data;
    }

    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}

class MyNode extends Node<Integer> {
    MyNode(Integer data) {
        super(data);
    }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}

class Animal {
    Animal() {
        System.out.println("animal init");
    }

    void watch() {
        System.out.println("watch");
    }
}

class Cat extends Animal {
    Cat() {
        System.out.println("cat init");
    }

    @Override
    public void watch() {
        super.watch();
    }
}

class Dog extends Animal {

}

//class SgT<T> {
//    static T = null;
//
//    static T doSomething() {
//
//    }
//}

//class DebugInvocationHandler implements InvocationHandler {
//    private final Object target;
//
//    DebugInvocationHandler(Object target) {
//        this.target = target;
//    }
//
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//    }
//}

class TargetObject {
    private String value;

    TargetObject() {
        value = "JavaGuide";
    }

    void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    void privateMethod() {
        System.out.println("value is " + value);
    }
}

class TestOverload {
    int a;

//    int print1() {
//        System.out.println("hello 1");
//        return 1;
//    }

    int print1() {
        System.out.println("dude");
        return 0;
    }

//    int print1(int b) {
//        System.out.println("hello 1");
//        return b;
//    }
}

//class SubTestOverload extends TestOverload {
//    @Override
//    int print1(int b) {
//        System.out.println("just 1 " + b);
//        return 12;
//    }
//
//    @Override
//    void print1() {
//        super.print1();
//    }
//}

class Student {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

interface JustWe {

}

abstract class ABS {

}

public class LearnClass {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        ABS abs = new ABS() {
        };
        System.out.println(abs);

        JustWe we = new JustWe() {
        };
        System.out.println(we);
//        ArrayList<Student> arr = new ArrayList<>();
//        arr.add(new Student("王老五", 22));
//        arr.add(new Student("老赵", 24));
//        arr.add(new Student("老白", 19));
//        arr.add(new Student("老李", 25));
//        System.out.println(arr.stream().filter(student -> student.age > 20).map(student -> student.name).limit(2).toList());
//
//        List<List<String>> list2D = Arrays.asList(
//                Arrays.asList("Reflection", "Collection", "Stream"),
//                Arrays.asList("Structure", "State", "Flow"),
//                Arrays.asList("Sorting", "Mapping", "Reduction", "Stream")
//        );
//
//        List<String> tmp = new ArrayList<>();
//        List<String> res = list2D.stream()
//                .flatMap(List::stream)
//                .distinct()
//                .sorted()
//                .peek(tmp::add)
//                .toList();
//        System.out.println(res);
//        System.out.println(tmp);
//
//        String s = s1 + s2;
//        System.out.println(s);
//
//        ClassLoader classLoader = LearnClass.class.getClassLoader();
//        StringBuilder split = new StringBuilder("|--");
//        boolean needContinue = true;
//        while (needContinue) {
//            System.out.println(split.toString() + classLoader);
//            if (classLoader == null) {
//                needContinue = false;
//            } else {
//                classLoader = classLoader.getParent();
//                split.insert(0, '\t');
//            }
//        }
    }

    static String s1;
    static String s2;
}

@FunctionalInterface
interface MyFuncInterface {
    void doSomething(int a, int b);
}
