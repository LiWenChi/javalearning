
package concurrent.c_025;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;
/**
 * http://blog.csdn.net/sunxianghuang/article/details/52221913 
 * http://www.educity.cn/java/498061.html
 * 阅读concurrentskiplistmap
 * skiplistmap是插入效率也很高，且有序
 *
 * ConcurrentHashMap将原本整体的锁(Hashtable的锁)进行了细化（分成16断），只锁定其中的一段被操作的数据
 * 这样执行的效率会变高
 */
public class T01_ConcurrentMap {
	public static void main(String[] args) {
		//Map<String, String> map = new ConcurrentHashMap<>();
		Map<String, String> map = new ConcurrentSkipListMap<>(); //高并发且排序
		
		//Map<String, String> map = new Hashtable<>();// 默认加锁
		//Map<String, String> map = new HashMap<>(); //手动加锁：Collections.synchronizedXXX
		//TreeMap
		Random r = new Random();
		Thread[] ths = new Thread[100]; // 线程数组
		CountDownLatch latch = new CountDownLatch(ths.length);
		long start = System.currentTimeMillis();
		for(int i=0; i<ths.length; i++) {
			ths[i] = new Thread(()->{
				for(int j=0; j<10000; j++) map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
				latch.countDown();
			});
		}
		
		Arrays.asList(ths).forEach(t->t.start()); // 启动线程数组的线程
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
