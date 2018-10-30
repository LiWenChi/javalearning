
package concurrent.c_026;

import java.util.concurrent.Executor;
/**
 * 认识Executor
 * 一个接口，其定义了一个接收Runnable对象的方法executor，其方法签名为executor(Runnable command),
 * 其内部使用了线程池机制，它在java.util.cocurrent 包下，通过该框架来控制线程的启动、执行和关闭，可以简化并发编程的操作
 */
public class T01_MyExecutor implements Executor {

	public static void main(String[] args) {
		new T01_MyExecutor().execute(()->System.out.println("hello executor"));
	}

	public void execute(Runnable command) {
		//new Thread(command).run();
		command.run();
	}

}

