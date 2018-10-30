package concurrent.c_024;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * 
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 * 
 * 使用Vector或者Collections.synchronizedXXX
 * 分析一下，这样能解决问题吗？
 * 
 * 就算操作A和B都是同步的，但A和B组成的复合操作也未必是同步的，仍然需要自己进行同步
 * 就像这个程序，判断size和进行remove必须是一整个的原子操作
 * 
 * 使用ConcurrentQueue提高并发性（并发容器）
 * 
 * @author 马士兵
 */
public class TicketSeller4 {
	// 使用支持并发的队列提高并发性能
	static Queue<String> tickets = new ConcurrentLinkedQueue<>();
	
	
	static {
		for(int i=0; i<1000; i++) tickets.add("票 编号：" + i);
	}
	
	public static void main(String[] args) {
		
		for(int i=0; i<10; i++) {
			new Thread(()->{
				while(true) {
					// 这个里面不需要加锁，因为即使在操作和判断中被打断，也不会有问题
					// 这样执行的效率就会高很多
					String s = tickets.poll(); // 从队列中拿出一个元素
					if(s == null) break; // 判断是否为空
					else System.out.println("销售了--" + s);
				}
			}).start();
		}
	}
}
