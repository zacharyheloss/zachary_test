/**
 * 
 */
package com.zachary.concurrent.po;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhang
 *
 */
public class StudentA implements Runnable{
	
	private CountDownLatch countDownLatch;
	private String name;
	
	public StudentA(String name,CountDownLatch countDownLatch){
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
	
	public static void main(String[] args) {
		System.out.println(new Random().nextInt(120));
	}
}
