package concurrent.c_021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法， 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * 
 * 使用wait和notify/notifyAll来实现
 * 
 * 使用Lock和Condition来实现 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 * 
 * @author mashibing
 */
/**
 * Condition的特性:
 * 1.Condition中的await()方法相当于Object的wait()方法，
 * 		Condition中的signal()方法相当于Object的notify()方法，
 * 		Condition中的signalAll()相当于Object的notifyAll()方法。
 * 		不同的是，Object中的这些方法是和同步锁捆绑使用的；而Condition是需要与互斥锁/共享锁捆绑使用的。
 * 2.Condition它更强大的地方在于：能够更加精细的控制多线程的休眠与唤醒。
 *  	对于同一个锁，我们可以创建多个Condition，在不同的情况下使用不同的Condition。
 * 例如，假如多线程读/写同一个缓冲区：当向缓冲区中写入数据之后，唤醒"读线程"；
 *   当从缓冲区读出数据之后，唤醒"写线程"；并且当缓冲区满的时候，"写线程"需要等待；当缓冲区为空时，"读线程"需要等待。
 *   如果采用Object类中的wait(), notify(),
 *   notifyAll()实现该缓冲区，当向缓冲区写入数据之后需要唤醒"读线程"时，不可能通过notify()或notifyAll()明确的指定唤醒"读线程"，
 *   而只能通过notifyAll唤醒所有线程(但是notifyAll无法区分唤醒的线程是读线程，还是写线程)。
 *   但是，通过Condition，就能明确的指定唤醒读线程。
 */
public class MyContainer2<T> {
	final private LinkedList<T> lists = new LinkedList<>();
	final private int MAX = 10; // 最多10个元素
	private int count = 0;

	private Lock lock = new ReentrantLock(); // 锁对象
	// 得到控制锁的两个对象
	private Condition producer = lock.newCondition(); // 条件
	private Condition consumer = lock.newCondition();

	public void put(T t) {
		try {
			lock.lock();
			while (lists.size() == MAX) { // 想想为什么用while而不是用if？
				producer.await(); // producer线程等待
			}

			lists.add(t);
			++count;
			consumer.signalAll(); // consumer线程全部被叫醒
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public T get() {
		T t = null;
		try {
			lock.lock();
			while (lists.size() == 0) {
				consumer.await();
			}
			t = lists.removeFirst();
			count--;
			producer.signalAll(); // 通知生产者进行生产
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return t;
	}

	public static void main(String[] args) {
		MyContainer2<String> c = new MyContainer2<>();
		// 启动消费者线程
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				for (int j = 0; j < 5; j++)
					System.out.println(c.get());
			}, "c" + i).start();
		}

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 启动生产者线程
		for (int i = 0; i < 2; i++) {
			new Thread(() -> {
				for (int j = 0; j < 25; j++)
					c.put(Thread.currentThread().getName() + " " + j);
			}, "p" + i).start();
		}
	}
}
