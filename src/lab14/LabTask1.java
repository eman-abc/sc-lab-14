package lab14;

class NumberThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Number: " + i);
        }
    }
}

class SquareThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Square of " + i + ": " + (i * i));
        }
    }
}

public class LabTask1 {
    public static void main(String[] args) {
        NumberThread numThread = new NumberThread();
        SquareThread squareThread = new SquareThread();

        numThread.start();
        squareThread.start();
    }
}
