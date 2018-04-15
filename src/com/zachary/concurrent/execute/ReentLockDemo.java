/**
 * 
 */
package com.zachary.concurrent.execute;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.zachary.concurrent.po.StudentA;
import com.zachary.concurrent.po.StudentB;
import com.zachary.concurrent.po.Teacher;

/**
 * @author zhangcheng
 * @desc 同步锁
 * 
 */
public class ReentLockDemo {

	private static ReentrantLock lock = new ReentrantLock();
	private static int threadCount = 1000;
	
	public static void main(String[] args) throws InterruptedException {

		Thread[] threads = new Thread[threadCount];
		final CountDownLatch countDownLatch=new CountDownLatch(threadCount);

		long start=System.currentTimeMillis();
		for (int i = 0; i < threadCount; i++) {
			threads[i] = new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println(lockMethod());
					System.out.println(trylockMethod());
					countDownLatch.countDown();
				}
			});
			threads[i].start();
			//threads[i].join();
		}
		countDownLatch.await();
		System.out.println((System.currentTimeMillis()-start)+"/ms");
	}

	public static String lockMethod() {

		if (lock.tryLock()) {
			System.out.println("thead name" + Thread.currentThread().getName()
					+ "获取锁..");
			try {
				return UUID.randomUUID().toString();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				lock.unlock();
			}
		}
		return null;
	}
	
	
	public static String trylockMethod() {
		try {
			if (lock.tryLock(5000,TimeUnit.MILLISECONDS)) {
				try {
					System.out.println("thead name" + Thread.currentThread().getName()
							+ "获取锁..");
						return UUID.randomUUID().toString();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {
					lock.unlock();
				}
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
