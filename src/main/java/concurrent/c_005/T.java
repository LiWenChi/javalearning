package concurrent.c_005;
/**
 * 分析一下这个程序的输出
 * @author mashibing
 */
public class T implements Runnable {

	private int count = 10;
	// synchronized代码快是原子操作，不可分的
	public /*synchronized*/ void run() { 
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	/** 5个线程同时访问堆内存中的同一个对象 */
	public static void main(String[] args) {
		T t = new T();
		for(int i=0; i<5; i++) {
			new Thread(t, "THREAD" + i).start();
		}
	}
	
}
