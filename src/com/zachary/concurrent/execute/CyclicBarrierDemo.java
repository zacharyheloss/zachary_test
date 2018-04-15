/**
 * 
 */
package com.zachary.concurrent.execute;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zhang
 * @desc 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门。所有被屏障拦截的线程才会继续干活
 * CyclicBarrier可以用于多线程计算数据，最后合并计算结果的应用场景
 */
public class CyclicBarrierDemo {
	
	static CyclicBarrier cyclicBarrier=new CyclicBarrier(2,new A());
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					cyclicBarrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
				System.out.println(1);
			}
		}).start();
		
		/*try {
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}*/
		
	 	System.out.println(2);

	}
	
	
	static class A implements Runnable{

		/* （非 Javadoc）
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(3);
		}
		
	}
}
