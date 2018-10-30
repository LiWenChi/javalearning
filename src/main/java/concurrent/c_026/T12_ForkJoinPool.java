package concurrent.c_026;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
/**
 * ForkJoinPool()将大的任务切分成小的的任务，运行完之后在将结果聚合
 * 线程池中的线程是精灵线程，会在后台运行
 * 用于大任务的切分运行
 */
public class T12_ForkJoinPool {
	static int[] nums = new int[1000000];
	static final int MAX_NUM = 50000;
	static Random r = new Random();
	
	static {
		for(int i=0; i<nums.length; i++) {
			nums[i] = r.nextInt(100);
		}
		
		System.out.println(Arrays.stream(nums).sum()); //stream api 
	}
	
	// 使用RecursiveAction定义ForkJoinPool的线程池任务，任务没有返回值
	/*static class AddTask extends RecursiveAction { 
		
		int start, end;
		
		AddTask(int s, int e) {
			start = s;
			end = e;
		}
		
		// 执行每个分片任务的计算逻辑
		@Override
		protected void compute() {
			
			if(end-start <= MAX_NUM) {
				long sum = 0L;
				for(int i=start; i<end; i++) sum += nums[i];
				System.out.println("from:" + start + " to:" + end + " = " + sum);
			} else {
			
				int middle = start + (end-start)/2;
				
				AddTask subTask1 = new AddTask(start, middle);
				AddTask subTask2 = new AddTask(middle, end);
				subTask1.fork();
				subTask2.fork();
			}
			
			
		}
		
	}*/

	// 使用RecursiveTask<T>定义ForkJoinPool的线程池任务，任务有返回值
	static class AddTask extends RecursiveTask<Long> { 
		
		int start, end;
		
		AddTask(int s, int e) {
			start = s;
			end = e;
		}

		@Override
		protected Long compute() {
			
			if(end-start <= MAX_NUM) {
				long sum = 0L;
				for(int i=start; i<end; i++) sum += nums[i];
				return sum;
			} 
			
			int middle = start + (end-start)/2;
			
			AddTask subTask1 = new AddTask(start, middle);
			AddTask subTask2 = new AddTask(middle, end);
			subTask1.fork(); // 调用compute()方法，相当于递归调用
			subTask2.fork();
			
			return subTask1.join() + subTask2.join(); // join阻塞并获取结果
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		ForkJoinPool fjp = new ForkJoinPool();
		AddTask task = new AddTask(0, nums.length);
		fjp.execute(task);
		long result = task.join();
		System.out.println(result);
		
		//System.in.read();
		
	}
}
