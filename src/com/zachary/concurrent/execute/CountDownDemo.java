/**
 * 
 */
package com.zachary.concurrent.execute;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zachary.concurrent.po.StudentA;
import com.zachary.concurrent.po.StudentB;
import com.zachary.concurrent.po.Teacher;


/**
 * @author �ų�
 * @desc ���߳�ͳһ����ȴ�ִ�����
 */
public class CountDownDemo{
	
	/*public static void main(String[] args) {
		ExecutorService executorService=Executors.newCachedThreadPool();
		int i =0;
		
		do {
			CountDownLatch countDownLatch=new CountDownLatch(2);
			
			StudentA studentA=new StudentA("小明", countDownLatch);
			StudentB studentB=new StudentB("小康", countDownLatch);
			Teacher teacher=new Teacher(countDownLatch);
			
			executorService.submit(studentA);
			executorService.submit(studentB);
			executorService.submit(teacher);
			i++;
		} while (i<10);
		executorService.shutdown();
	}*/
}
