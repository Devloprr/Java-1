import java.util.concurrent.*;
import static java.lang.System.*;

class Executor_Example{
	public static void main(String ar[]){
		CountDownLatch cdl1 = new CountDownLatch(5);
		CountDownLatch cdl2 = new CountDownLatch(5);
		CountDownLatch cdl3 = new CountDownLatch(5);
		CountDownLatch cdl4 = new CountDownLatch(5);
		ExecutorService es = Executors.newFixedThreadPool(2);
	//	ExecutorService es = Executors.newCachedThreadPool();
	//	ScheduledExecutorService es = Executors.newScheduledThreadPool(2);

		out.println("Starting");
		es.execute(new MyThread3(cdl1, "A"));
		es.execute(new MyThread3(cdl2, "B"));
		es.execute(new MyThread3(cdl3, "C"));
		es.execute(new MyThread3(cdl4, "D"));

		try{
			cdl1.await();
			cdl2.await();
			cdl3.await();
			cdl4.await();
		}catch(Exception e){
			out.println(e);
		}
		es.shutdown();
		out.println("Done");
	}
}
class MyThread3 implements Runnable{
	String name;
	CountDownLatch cdl;
	MyThread3(CountDownLatch cdl, String name){
		this.cdl = cdl;
		this.name = name;
		new Thread(this);
	}
	public void run(){
		try{
			for(int i=0; i<5; i++){
				out.println(name + " : " + i);
				Thread.sleep(100);
				cdl.countDown();
			}
		}catch(Exception e){
			out.println(e);
		}
	}
}