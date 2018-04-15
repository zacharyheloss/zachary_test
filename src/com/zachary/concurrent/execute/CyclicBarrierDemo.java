/**
 * 
 */
package com.zachary.concurrent.execute;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zhang
 * @desc ��һ���̵߳���һ�����ϣ�Ҳ���Խ�ͬ���㣩ʱ��������ֱ�����һ���̵߳�������ʱ�����ϲŻῪ�š����б��������ص��̲߳Ż�����ɻ�
 * CyclicBarrier�������ڶ��̼߳������ݣ����ϲ���������Ӧ�ó���
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

		/* ���� Javadoc��
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(3);
		}
		
	}
}
