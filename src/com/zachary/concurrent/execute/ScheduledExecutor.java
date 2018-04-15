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
 * @desc 定时任务线程
 */
public class ScheduledExecutor {

	private static final AtomicLong l = new AtomicLong(0);
	ScheduledExecutorService scheduledThreadPool = Executors
			.newScheduledThreadPool(5);

	// 延迟3秒执行
	public void setp1() {

		scheduledThreadPool.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("delay 3 seconds");
			}
		}, 3, TimeUnit.SECONDS);
	}

	/*
	 * 使用scheduleAtFixedRate ， 该方法第三个参数表示在上一个个任务开始执行之后延迟 多少秒之后再执行，
	 * 是从上一个任务开始时开始计算 但是还是会等上一个任务执行完之后，下一个任务才开始执行，最后的结果，就是感觉延迟失去 了作用
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
	 * 该方法第三个参数表示在上一个个任务结束执行之后延迟 多少秒之后再执行， 是从上一个任务结束时开始计算
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
