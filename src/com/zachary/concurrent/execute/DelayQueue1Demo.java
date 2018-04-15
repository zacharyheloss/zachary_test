/**
 * 
 */
package com.zachary.concurrent.execute;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import com.zachary.concurrent.execute.DelayQueue1Demo.Producer.Consumer;
import com.zachary.concurrent.execute.DelayQueue1Demo.Producer.Message;

public class DelayQueue1Demo {

	static class Producer implements Runnable {
		private DelayQueue<Message> queue;

		public Producer(DelayQueue<Message> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			// 5�������Ϣ
			Message m2 = new Message("2", "Tom", 5000);
			queue.offer(m2);
			System.out.println("��Ϣ����������Ϣ���з�����Ϣ��" + m2.getId() + ":"
					+ m2.getName());
			// 3�������Ϣ
			Message m1 = new Message("1", "Tom", 3000);
			queue.offer(m1);
			System.out.println("��Ϣ����������Ϣ���з�����Ϣ��" + m1.getId() + ":"
					+ m1.getName());

		}

		static class Consumer implements Runnable {
			private DelayQueue<Message> queue;

			public Consumer(DelayQueue<Message> queue) {
				this.queue = queue;
			}

			@Override
			public void run() {
				while (true) {
					try {
						Message take = queue.take();
						System.out.println("��Ϣ�����߻�ȡ��Ϣ��" + take.getId() + ":"
								+ take.getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		static class Message implements Delayed {
			private String id;
			private String name;
			private long activeTime;// ִ��ʱ��

			public Message() {

			}

			public Message(String id, String name, long activeTime) {
				super();
				this.id = id;
				this.name = name;
				this.activeTime = TimeUnit.NANOSECONDS.convert(activeTime,
						TimeUnit.MILLISECONDS) + System.nanoTime();
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			@Override
			public int compareTo(Delayed delayed) {
				Message msg = (Message) delayed;
				return Integer.valueOf(this.id) > Integer.valueOf(msg.id) ? 1
						: (Integer.valueOf(this.id) < Integer.valueOf(msg.id) ? -1
								: 0);
			}

			@Override
			public long getDelay(TimeUnit unit) {
				return unit.convert(this.activeTime - System.nanoTime(),
						TimeUnit.NANOSECONDS);
			}
		}
	}

	public static void main(String[] args) {
		DelayQueue<Message> queue = new DelayQueue<Message>();
		new Thread(new Producer(queue)).start();
		new Thread(new Consumer(queue)).start();
	}
}
