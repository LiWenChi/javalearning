package concurrent.c_004;
/**
 * synchronized关键字
 * 对某个对象加锁
 * @author mashibing
 */

public class T {

	private static int count = 10;
	
	public synchronized static void m() { //这里等同于synchronized(yxxy.c_004.T.class),当前的class对象
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void mm() {
		//考虑一下这里写synchronized(this)是否可以？；
		//不可以，因为静态的属性方法是不需要new出来对象就可以访问的，所以不锁定this
		synchronized(T.class) { 
			count --;
		}
	}

}
