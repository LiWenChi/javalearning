
package concurrent.c_026;

import java.util.concurrent.Callable;
/**
 * 认识Callable，对Runnable进行了扩展,
 * Runnable的run方法没有返回值
 * Callbale的call方法是有返回值的
 * 对Callable的调用，可以有返回值，如果在线程中调用方法需要返回值则用Callable不然用Runnalbe
 */
public class T03_Callable implements Callable{

	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
