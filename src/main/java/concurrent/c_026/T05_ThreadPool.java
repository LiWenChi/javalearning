
package concurrent.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 线程池的概念
 * 维护着两个队列，分别存放未执行和运行完的任务
 */
public class T05_ThreadPool {
	public static void main(String[] args) throws InterruptedException {
		// 定义一个线程池，包含5个线程
		ExecutorService service = Executors.newFixedThreadPool(5); //execute submit
		for (int i = 0; i < 6; i++) {
			// 使用execute方法，向线程池汇中提交任务
			service.execute(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		System.out.println(service);
		
		service.shutdown(); // 关闭线程池
		System.out.println(service.isTerminated()); // 任务是否执行完
		System.out.println(service.isShutdown());// 线程池是否关闭
		System.out.println(service);
		
		TimeUnit.SECONDS.sleep(5);
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
	}
}
