/**
 * 
 */
package com.zachary.concurrent.po;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhang
 *
 */
public class Teacher implements Runnable{
	
	private CountDownLatch countDownLatch;
	
	public Teacher(CountDownLatch countDownLatch){
		this.countDownLatch=countDownLatch;
	}

	/* （非 Javadoc�?
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("教师�?��叫同学们看书.....");
		try {
			this.countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("同学们都看完书本，结束！！！");
	}

}
