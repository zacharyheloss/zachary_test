/**
 * 
 */
package com.zachary.concurrent.po;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zhang
 *
 */
public class StudentB implements Runnable{
	
	private CountDownLatch countDownLatch;
	private String name;
	
	public StudentB(String name,CountDownLatch countDownLatch){
		this.countDownLatch=countDownLatch;
		this.name=name;
	}

	/* ÔºàÈùû JavadocÔº?
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.doStart();
		/*try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		this.countDownLatch.countDown();
	}

	
	
	public void doStart(){
		System.out.println(this.name+"Âº?ßãÁúã‰π¶!!!");
	}
}
