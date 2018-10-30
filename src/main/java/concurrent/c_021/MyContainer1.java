package concurrent.c_021;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * 如果容器已经满了，那么调用put的方法就得阻塞
 * 如果容器已经空了，那么调用get的方法就得阻塞
 * 
 * 使用wait和notify/notifyAll来实现
 * wait往往都是和while一直使用的
 * 
 * @author mashibing
 */
public class MyContainer1<T> {
	final private LinkedList<T> lists = new LinkedList<>();
	final private int MAX = 10; //最多10个元素
	private int count = 0;
	
	
	public synchronized void put(T t) {
		// 使用while不使用if进行判断的原因是：
		// 如果使用if则当条件不满足则直接往下执行，但是在还没有add的时候，另外一个线程addl
		// 则这个时候当前线程再add就超过容器的大小了，程序出错
		// 但是如果使用while的时候，当前等待线程被唤醒后，往下执行的时候还会再次检查是否==MAX
		// 所以如果出现有其他的线程add了则条件仍然成立
		while(lists.size() == MAX) { //想想为什么用while而不是用if？(wait一直判断，if只判断一次)
			try {
				this.wait(); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		lists.add(t);
		++count;
		// 使用notifyAll不使用notify的原因是：
		// notify只能叫醒一个线程，这时候就可能还是叫醒的是一个生产者等待线程，不一定叫醒的是消费者线程
		this.notifyAll(); //通知消费者线程进行消费
	}
	
	public synchronized T get() {
		T t = null;
		while(lists.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = lists.removeFirst();
		count --;
		this.notifyAll(); //通知生产者进行生产
		return t;
	}
	
	public static void main(String[] args) {
		MyContainer1<String> c = new MyContainer1<>();
		//启动消费者线程
		for(int i=0; i<10; i++) {
			new Thread(()->{
				for(int j=0; j<5; j++) System.out.println(c.get());
			}, "c" + i).start();
		}
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//启动生产者线程
		for(int i=0; i<2; i++) {
			new Thread(()->{
				for(int j=0; j<25; j++) c.put(Thread.currentThread().getName() + " " + j);
			}, "p" + i).start();
		}
	}
}
