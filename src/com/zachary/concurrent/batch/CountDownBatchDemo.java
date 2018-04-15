/**
 * 
 */
package com.zachary.concurrent.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zachary.qcode.BeanUtils;

/**
 * @author zhang
 * @desc CountDownLatch��ʽ������ȡ����
 */
public class CountDownBatchDemo {

	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();

		for (int i = 0; i <= 201; i++) {
			list.add(i + "");
		}

		// ����
		int importNum = list.size();
		// ����Ĭ����1��1000
		int count = 200;
		// ��������
		int p_number = (importNum - 1) / count + 1;
		System.out.println(p_number);
		ExecutorService executorService = Executors
				.newFixedThreadPool(p_number + 1);
		CountDownLatch countDownLatch = new CountDownLatch(p_number + 1);
		for (int i = 0; i < p_number + 1; i++) {

			// �±�
			final int startBatch = importNum / p_number * i;
			// ����
			int length = importNum / p_number * (i + 1);
			// ��ȡ����(�±� 0-799,800-1599)
			final List<String> tempMapList = BeanUtils.getSubList(list,
					startBatch, length);
			executorService.execute(new Task(tempMapList, countDownLatch));
		}
	}

	static class Task implements Runnable {

		private List<String> tempMapList;
		private CountDownLatch countDownLatch;

		public Task(List<String> tempMapList, CountDownLatch countDownLatch) {
			this.tempMapList = tempMapList;
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {

			for (String qcode : tempMapList) {
				// ��ȡtoken
				try {
					System.out.println(111111);
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			countDownLatch.countDown();

		}

	}
}
