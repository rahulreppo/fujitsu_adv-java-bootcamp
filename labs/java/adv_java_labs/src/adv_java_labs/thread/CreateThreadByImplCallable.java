package adv_java_labs.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CreateThreadByImplCallable implements Callable<String> {

	

	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName()+"INSIDE CALL METHOD");
		return "Hello World";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executor=Executors.newFixedThreadPool(1);
		
		Callable t1=new CreateThreadByImplCallable();
		
		Future<String> f1=executor.submit(t1);
		
//		while(!f1.isDone())
//		{
//			System.out.println("Processing....");
//		}

		try {
			System.out.println(f1.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		executor.shutdown();
	}
	


	
}
