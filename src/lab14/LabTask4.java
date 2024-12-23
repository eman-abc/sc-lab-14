package lab14;

import java.util.concurrent.atomic.AtomicInteger;

class BankAccount {
    private AtomicInteger balance = new AtomicInteger(0);

    public void deposit(int amount) {
        balance.addAndGet(amount);
        System.out.println(Thread.currentThread().getName() + " deposited: " + amount);
    }

    public void withdraw(int amount) {
        if (balance.get() >= amount) {
            balance.addAndGet(-amount);
            System.out.println(Thread.currentThread().getName() + " withdrew: " + amount);
        } else {
            System.out.println(Thread.currentThread().getName() + " tried to withdraw: " + amount + " but insufficient balance.");
        }
    }

    public int getBalance() {
        return balance.get();
    }
}

class Client implements Runnable {
    private final BankAccount account;

    public Client(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            int amount = (int) (Math.random() * 100);
            if (Math.random() > 0.5) {
                account.deposit(amount);
            } else {
                account.withdraw(amount);
            }
        }
    }
}

public class LabTask4 {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Thread client1 = new Thread(new Client(account), "Client 1");
        Thread client2 = new Thread(new Client(account), "Client 2");

        client1.start();
        client2.start();

        try {
            client1.join();
            client2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Account Balance: " + account.getBalance());
    }
}
