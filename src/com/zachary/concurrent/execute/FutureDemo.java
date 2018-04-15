/**
 * 
 */
package com.zachary.concurrent.execute;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author �ų�
 * @desc ���߳�ͳһ����ȴ�ִ����ϣ��ҵ������߳�ִ����Ϻ�������ҵ�� ���确��¼��־������
 */
public class FutureDemo {

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {

		/*ExecutorService executor2 = Executors.newFixedThreadPool(5);
		List<Future<String>> results = new ArrayList<Future<String>>();
		for (int i = 0; i < 5; i++) {
			Future<String> f = executor2.submit(new Task());
			results.add(f);
		}

		boolean flag = true;
		while (flag) {

			for (Iterator<Future<String>> iter = results.iterator(); iter
					.hasNext();) {
				Future<String> f = iter.next();
				if (f.isDone()) {
					System.out.println(f.get());
					iter.remove();

				}
			}

			if (results.size() == 0) {
				flag = false;
			}

		}

		System.out.println("ִ�����");

		executor2.shutdownNow();*/
		
		int importNum=1001;
		int count = 800;
		int p_number = (importNum- 1) / count + 1;
		System.out.println(p_number);
		

	}

	static class Task implements Callable<String> {
		@Override
		public String call() throws Exception {

			Random rand = new Random();
			TimeUnit.SECONDS.sleep(rand.nextInt(10));
			return Thread.currentThread().getName();
		}
	}

}
