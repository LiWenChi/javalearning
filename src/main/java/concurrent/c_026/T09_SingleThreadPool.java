package concurrent.c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 单线程池
 * Executors.newSingleThreadExecutor()线程池中只有一个线程
 * 用于保证任务是顺序执行的
 * @author Administrator
 *
 */
public class T09_SingleThreadPool {
	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		for(int i=0; i<5; i++) {
			final int j = i;
			service.execute(()->{
				
				System.out.println(j + " " + Thread.currentThread().getName());
			});
		}
			
	}
}
