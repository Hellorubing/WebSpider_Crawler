package com.bin.BFS;

import java.util.LinkedList;

public class Queue {
	/**
	 * 使用链表实现队列
	 */
	private LinkedList<Object> queue = new LinkedList<Object>();
	//入队列
	public void enQueue(Object t){
		queue.addLast(t);
	}
	//出队列
	public Object deQueue(){
		return queue.removeFirst();
	}
	//判断队列是否为空
	public boolean isQueueEmpty(){
		return queue.isEmpty();
	}
	//判断队列是否为空
	public boolean contians(Object t){
		return queue.contains(t);
	}
	
	public boolean empty(){
		return queue.isEmpty();
	}
}