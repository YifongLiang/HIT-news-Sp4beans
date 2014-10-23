package com.Sp4beans.spider;

import java.util.LinkedList;

/**
 * 队列
 *
 */
public class Queue {
	private LinkedList<String> queue = new LinkedList<String>();

	// 入队
	public void enQueue(String t) {
		queue.addLast(t);
	}

	// 出队
	public String deQueue() {
		return queue.removeFirst();
	}

	// 判断队列是否为空
	public boolean isQueueEmpty() {
		return queue.isEmpty();
	}

	// 队列中是否包含t
	public boolean contains(Object t) {
		return queue.contains(t);
	}
}
