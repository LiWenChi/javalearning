package concurrent.c_025;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * 队列在并发中使用的比较多
 */
public class T04_ConcurrentQueue {
	public static void main(String[] args) {
		Queue<String> strs = new ConcurrentLinkedQueue<>();
		
		for(int i=0; i<10; i++) {
			strs.offer("a" + i);  //添加元素
		}
		
		System.out.println(strs);
		
		System.out.println(strs.size());
		
		System.out.println(strs.poll()); //从队列的头部获拿取数据
		System.out.println(strs.size());
		
		System.out.println(strs.peek());
		System.out.println(strs.size()); //对队列的头部获取数据，不删除数据
		
		//双端队列Deque
	}
}
