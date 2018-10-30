package concurrent.c_024;

import java.util.Vector;
import java.util.concurrent.TimeUnit;
/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * 
 * 分析下面的程序可能会产生哪些问题？
 *  
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 * 
 * @author 马士兵
 */
public class TicketSeller2 {
	static Vector<String> tickets = new Vector<>(); // Vector是线程安全的
	
	
	static {
		for(int i=0; i<1000; i++) tickets.add("票 编号：" + i);
	}
	
	public static void main(String[] args) {
		
		for(int i=0; i<10; i++) {
			new Thread(()->{
				// remove和size方法是原子的，但是判断和操作两者分离，
				// 如果在这两者之间出现问题则还是会出现问题
				while(tickets.size() > 0) {
					// 在执行while里面的代码时候，可能会被打断
					try {
						TimeUnit.MILLISECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					
					System.out.println("销售了--" + tickets.remove(0));
				}
			}).start();
		}
	}
}
