package juc;

@FunctionalInterface
interface Foo {
    //public void sayHello();

    public int add(int a, int b);
}

public class LambdaExpressDemo02 {
    public static void main(String[] args) {
        /*Foo foo = () -> {System.out.println("Lambda Express");};
        foo.sayHello();*/

        Foo foo = (int a, int b) -> {return a + b;};
        System.out.println(foo.add(3, 5));
    }
}
