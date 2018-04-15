/**
 * 
 */
package com.zachary.concurrent.execute;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhang
 * @desc ��ʱ�����߳�
 */
public class ScheduledExecutor {

	private static final AtomicLong l = new AtomicLong(0);
	ScheduledExecutorService scheduledThreadPool = Executors
			.newScheduledThreadPool(5);

	// �ӳ�3��ִ��
	public void setp1() {

		scheduledThreadPool.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("delay 3 seconds");
			}
		}, 3, TimeUnit.SECONDS);
	}

	/*
	 * ʹ��scheduleAtFixedRate �� �÷���������������ʾ����һ��������ʼִ��֮���ӳ� ������֮����ִ�У�
	 * �Ǵ���һ������ʼʱ��ʼ���� ���ǻ��ǻ����һ������ִ����֮����һ������ſ�ʼִ�У����Ľ�������Ǹо��ӳ�ʧȥ ������
	 */
	public void setp2() {
		ScheduledFuture<?> sf1 = scheduledThreadPool.scheduleAtFixedRate(
				new Runnable() {
					public void run() {
						try {
							long i = l.getAndAdd(1);
							System.out.println("start " + i);
							TimeUnit.SECONDS.sleep(5);
							System.out.println("end " + i);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}, 0, 2, TimeUnit.SECONDS);
	}

	/*
	 * �÷���������������ʾ����һ�����������ִ��֮���ӳ� ������֮����ִ�У� �Ǵ���һ���������ʱ��ʼ����
	 */
	public void setp3() {
		ScheduledFuture<?> sf2 = scheduledThreadPool.scheduleWithFixedDelay(
				new Runnable() {
					public void run() {
						try {
							long i = l.getAndAdd(1);
							System.out.println("start " + i);
							TimeUnit.SECONDS.sleep(3);
							System.out.println("end " + i);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}, 0, 2, TimeUnit.SECONDS);
	}

	public static void main(String[] args) {

	}
}
