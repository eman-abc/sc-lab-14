package lab14;

import java.util.concurrent.CopyOnWriteArrayList;

class ListTask implements Runnable {
    private final CopyOnWriteArrayList<Integer> list;

    public ListTask(CopyOnWriteArrayList<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            list.add((int) (Math.random() * 100));
            System.out.println(Thread.currentThread().getName() + " added a number.");
        }
    }
}

public class LabTask3 {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        Thread t1 = new Thread(new ListTask(list));
        Thread t2 = new Thread(new ListTask(list));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final List: " + list);
    }
}
