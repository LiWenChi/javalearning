package concurrent.c_025;

import java.util.concurrent.LinkedTransferQueue;

public class T08_TransferQueue {
	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
		
		// 先启动消费者
		/*new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/

		// transfer()方法解释
		// 先启动消费者，在启动生产者，如果在put的时候有等待空闲的消费者，则直接将元素给消费者，不用put进队列
		// 如果没有等待这的消费者线程，则会阻塞
		strs.transfer("aaa"); // 适用于更高的并发情况下，体改并发的性能
		
		strs.put("aaa"); // 不会阻塞
		
		// 后启动消费者的话则无法执行，因为已经阻塞了
		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
