package jvm;

class CodeZY {

    public CodeZY() {
        System.out.println("Code的构造方法1111");
    }
    {
        System.out.println("Code的构造块2222");
    }
    static {
        System.out.println("Code的静态代码块3333");
    }
}

public class CodeBlock03 {
    {
        System.out.println("CodeBlock03的构造块444");
    }
    static {
        System.out.println("CodeBlock03的静态代码块555");
    }

    public CodeBlock03() {
        System.out.println("CodeBlock03的构造方法666");
    }

    public static void main(String[] args) {
        System.out.println("==我是美丽的分割线========CodeBlock03的main方法777");
        new CodeZY();
        System.out.println("-------------------");
        new CodeZY();
        System.out.println("-------------------");
        new CodeBlock03();
    }
}



















