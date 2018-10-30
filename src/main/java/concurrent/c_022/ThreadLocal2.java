package concurrent.c_022;

import java.util.concurrent.TimeUnit;
/**
 * ThreadLocal线程局部变量
 *
 * ThreadLocal是使用空间换时间，synchronized是使用时间换空间
 * 比如在hibernate中session就存在与ThreadLocal中，避免synchronized的使用
 * 
 * 运行下面的程序，理解ThreadLocal
 * 
 * @author 马士兵
 */
public class ThreadLocal2 {
	//volatile static Person p = new Person();
	//线程只会使用自己工作内存中的变量，多个不会公共该变量，这样就提高效率
	static ThreadLocal<Person> tl = new ThreadLocal<>();
	
	public static void main(String[] args) {
				
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(tl.get());
		}).start();
		
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tl.set(new Person());
		}).start(); 
	}
	
	static class Person {
		String name = "zhangsan";
	}
}


