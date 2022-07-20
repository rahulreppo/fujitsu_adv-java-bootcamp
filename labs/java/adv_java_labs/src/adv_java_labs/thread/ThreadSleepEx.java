package adv_java_labs.thread;

public class ThreadSleepEx implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
		}

	}

	public static void main(String args[]) {

		System.out.println(Thread.currentThread().getName());
		System.out.println("Inside Main thread");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("ERROR:"+e.getMessage());
			e.printStackTrace();
		}

		Thread t1 = new Thread(new CreateThreadwithExtendingThread());

		Thread t2 = new Thread(new CreateThreadwithExtendingThread());

		t1.start();
		
		try {
			t1.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("ERROR:"+e.getMessage());
			e.printStackTrace();
		}
		t2.start();
	

	}

}