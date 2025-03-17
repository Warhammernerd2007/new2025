class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(getName() + " - Count: " + i);
            try {
                Thread.sleep(100); // Simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadPriorityTest {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread("Low Priority Thread");
        MyThread thread2 = new MyThread("Normal Priority Thread");
        MyThread thread3 = new MyThread("High Priority Thread");

        // Assign priorities
        thread1.setPriority(Thread.MIN_PRIORITY);  // Priority 1
        thread2.setPriority(Thread.NORM_PRIORITY); // Priority 5
        thread3.setPriority(Thread.MAX_PRIORITY);  // Priority 10

        // Start threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

