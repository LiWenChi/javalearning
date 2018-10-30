总结：
1：对于map/set的选择使用（set和map本质是一回事失只是一个装的是key另一个装的是key，value）
不需要考虑同步
HashMap
TreeMap
LinkedHashMap

加锁，考虑同步（并发不是很高的情况下使用）
Hashtable
Collections.sychronizedXXX

高并发的容器选用：
ConcurrentHashMap：适用于高并发
ConcurrentSkipListMap：适用于高并发且需要排序的场景

2：队列
非同步的队列
ArrayList
LinkedList
Collections.synchronizedXXX 手动加锁

CopyOnWriteList 写时复制，适用于读多写少的场景

Queue，高并发下使用的队列：
	CocurrentLinkedQueue 加锁队列
	
	BlockingQueue 阻塞式队列，包括如下：
		LinkedBQ 无界队列
		ArrayBQ 有界队列
		并发性能更高的阻塞队列
		TransferQueue 添加元素时优先将其交给消费者
		SynchronusQueue 无容量的TransferQueue

	DelayQueue 执行定时任务
		
	