package concurrent.c_026;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * 
 * Executors.newScheduledThreadPool(4)
 * 用于执行按照时间顺序的任务
 * 执行定时任务
 * 线程池中的线程是可以复用的
 *
 */
public class T10_ScheduledPool {
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
		// 以固定的频率执行任务，0表示起始执行时间；500间隔时间数值；TimeUnit.MILLISECONDS时间单位
		service.scheduleAtFixedRate(()->{
			try {
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		}, 0, 500, TimeUnit.MILLISECONDS);
		
	}
}
