package ru.alexander_kramarenko.ping_pong_and_counter.ping_pong;

public class PingPong {

    private final Object monitor = new Object();
    private final String PING = "Ping";
    private final String PONG = "Pong";
    private String currentMove = PING;


    public static void main(String[] args) {

        PingPong pingPong = new PingPong();
        new Thread(() -> {
            pingPong.printPing();
        }).start();
        new Thread(() -> {
            pingPong.printPong();
        }).start();
    }

    public void printPing() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 20; i++) {
                    while (!currentMove.equals(PING)) {
                        monitor.wait();
                    }
                    System.out.println(PING);
                    currentMove = PONG;
                    monitor.notifyAll();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printPong() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 20; i++) {
                    while (!currentMove.equals(PONG)) {
                        monitor.wait();
                    }
                    System.out.println(PONG);
                    currentMove = PING;
                    monitor.notifyAll();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
