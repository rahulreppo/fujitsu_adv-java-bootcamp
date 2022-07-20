package adv_java_labs.thread;

public class ThreadJoinEx implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
		}

	}

	public static void main(String args[]) throws InterruptedException {

		System.out.println(Thread.currentThread().getName());
		System.out.println("Inside Main thread");

		Thread t1 = new Thread(new CreateThreadwithExtendingThread());

		Thread t2 = new Thread(new CreateThreadwithExtendingThread());

		t1.start();
		t1.join();
		t2.start();
		t2.join();
		

	}

}