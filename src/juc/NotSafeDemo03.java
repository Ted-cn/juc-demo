package juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

class MyList {

}

public class NotSafeDemo03 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        //List<String> list = new CopyOnWriteArrayList();
        Set<String> set = new HashSet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
               list.add(UUID.randomUUID().toString().substring(0, 8));
               System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
