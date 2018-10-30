
package concurrent.c_026;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
/**
 * 认识future：是任务结束后返回的对象
 * Future的简单用法
 */
public class T06_Future {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		//FutureTask是和Runnable这样的任务组区分的，FutrueTask在运行结束后会有返回值	
		// 通过包装Callable类型的任务为一个FutureTask任务
		// 泛型只的是返回值的类型
		FutureTask<Integer> task = new FutureTask<>(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1000;
		}); //new Callable () { Integer call();}
		
		new Thread(task).start();
		
		System.out.println(task.get()); //阻塞；等到任务执行完拿到返回值
		
		//*******************************
		ExecutorService service = Executors.newFixedThreadPool(5);
		// 通过submit()方法想线程池汇总执行任务
		Future<Integer> f = service.submit(()->{
			TimeUnit.MILLISECONDS.sleep(500);
			return 1;
		});
		System.out.println(f.get()); // 得到返回值
		System.out.println(f.isDone()); // 判断任务是否执行完
		
	}
}
