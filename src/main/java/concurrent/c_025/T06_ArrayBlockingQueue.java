package concurrent.c_025;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockingQueue {

	// 这是一个有界队列的阻塞式容器
	static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10); // 装10元素

	static Random r = new Random();

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			strs.put("a" + i);
		}
		
		strs.put("aaa"); //满了就会等待，程序阻塞
		//strs.add("aaa"); // 满了会报异常
		//strs.offer("aaa"); // 满了不会报异常，也不阻塞
		//strs.offer("aaa", 1, TimeUnit.SECONDS); // 一秒加不进去就不再添加
		
		System.out.println(strs);
	}
}
