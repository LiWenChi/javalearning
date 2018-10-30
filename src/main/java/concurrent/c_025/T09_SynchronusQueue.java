package concurrent.c_025;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/** 同步队列SynchronousQueue */
public class T09_SynchronusQueue { //容量为0
	public static void main(String[] args) throws InterruptedException {
		// 没有容量的队列，来的任何元素，都必须被理解被消费掉
		BlockingQueue<String> strs = new SynchronousQueue<>();
		
		new Thread(()->{
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		strs.put("aaa"); //阻塞等待消费者消费，不会讲元素放置到容器中
		//strs.add("aaa");  // 会报错，应为容器的容量为零
		System.out.println(strs.size());
	}
}
