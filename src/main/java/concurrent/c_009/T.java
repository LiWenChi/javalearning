package concurrent.c_009;

import java.util.concurrent.TimeUnit;
/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁.
 * 也就是说synchronized获得的锁是可重入的
 * 就是说可以在同一个线程中，可以申请同一个锁
 *
 * 这个时候如果又另外一个线程需要先锁定m2然后再锁定m1，那么两个线程同时运行的时候就容易产生死锁
 * 因为第一个线程先锁定了m1后锁定了m2
 * @author mashibing
 */
public class T {
	synchronized void m1() {
		System.out.println("m1 start");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m2();
	}
	
	synchronized void m2() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m2");
	}
}
