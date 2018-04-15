/**
 * 
 */
package com.zachary.concurrent.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author zhang
 * @desc Future��ʽ������ȡ����
 */
public class FutureBatchDemo {
	
	static ExecutorService executorService= Executors.newFixedThreadPool(8);
	
	public static void main(String[] args) {
		
		List<String> list=new ArrayList<String>();
		
		for(int i =0;i<=201;i++){
			list.add(i+"");
		}
		
		
		//����
		int importNum=list.size();
		//����Ĭ����1��1000
		int count = 200;
		//��������
		int p_number = (importNum- 1) / count + 1;
		System.out.println(p_number);
		
		
		List<Future<String>> futureTaskList=new ArrayList<Future<String>>();
		for (int i = 0; i < p_number+1; i++) {
			
			//�±�
			final int startBatch = importNum / p_number * i;
			//����
			int length = importNum / p_number * (i + 1);
			//��ȡ����(�±� 0-799,800-1599)
			final List<String> tempMapList = getSubList(list, startBatch,length);
			System.out.println(tempMapList.toString());
			/*
			Future<String> future= executorService.submit(new Task(tempMapList));
			futureTaskList.add(future);*/
			
			
			//System.out.println(tempMapList.toString());
		}
		
		
		/*for(Future<String> futureTask :futureTaskList){
			try {
					System.out.println(futureTask.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		
	}
	
	
	
	static class BOSS extends FutureTask<String>{

		/**
		 * @param callable
		 */
		public BOSS(Callable<String> callable) {
			super(callable);
		}

		@Override
		protected void done() {
			try {  
	            System.out.println(get() + " �߳�ִ����ϣ�~");  
	        } catch (InterruptedException | ExecutionException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
		}

		/* ���� Javadoc��
		 * @see java.util.concurrent.FutureTask#isDone()
		 */
		
		 
		
	}
	
	
	static class Task implements Callable<String>{

		/* ���� Javadoc��
		 * @see java.util.concurrent.Callable#call()
		 */
		@Override
		public String call() throws Exception {
			return Thread.currentThread().getName();
		}
		
	}
	
	
	
	
	public static <T> List<T> getSubList(List<T> list, int from, int maxnum) {
		if (list == null || list.size() <= from) {
			return new ArrayList<T>();
		}
		return new ArrayList(list.subList(from, Math.min(maxnum, list.size())));
	}

}
