package concurrent.c_003;
/**
 * synchronized关键字
 * 对某个对象加锁
 * @author mashibing
 */

public class T {

	private int count = 10;
	// 执行代码的时候，锁定的是当前的对象，不是代码块
	public synchronized void m() { //等同于在方法的代码执行时要synchronized(this)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}

}
