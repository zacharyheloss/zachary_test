/**
 * 
 */
package com.zachary.qcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @desc 多线程查询数据库获取数据
 */
public class ThreadQueryDB {

	static ExecutorService executorService= Executors.newFixedThreadPool(6);
	
	public static void main(String[] args) {
		try {
			//假如数据库存在一天20W数据，
			int importNum=200000;
			int count = 30000;//假如每个线程查询30000条数据
			int p_number = (importNum- 1) / count + 1;
			
			Map<String, String> param=null;
			List<Future<List<Object>>> futureTaskList = new ArrayList<Future<List<Object>>>();
			for (int i = 0; i <p_number; i++) {
				final int startBatch =i * count;
				param = new HashMap<String, String>();
				param.put("offset", i+"");
				param.put("startBatch", startBatch + "");
				param.put("endBatch", count + "");
				Future<List<Object>> future = executorService.submit(new Task(param));
				futureTaskList.add(future);
			}
			
			List<Object> resultAll = new ArrayList<>();
			for (Future<List<Object>> futureTask : futureTaskList) {
				List<Object> wxOrderLogList = futureTask.get();
				resultAll.addAll(wxOrderLogList);
			}
			executorService.shutdown();
			//long end=System.currentTimeMillis();
			
			//循环list处理数据
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	
	static class Task implements Callable<List<Object>> {

		private Map<String, String> tempMapList;

		public Task(Map<String, String> tempMapList) {
			this.tempMapList = tempMapList;
		}

		@Override
		public List<Object> call() throws Exception {

			// 分页坐标起始位置
			String startBatch = tempMapList.get("startBatch");
			// 分页坐标结束位置
			String endBatch = tempMapList.get("endBatch");
			
			String offset = tempMapList.get("offset");
			
			//List<Object> orderList=orderLogService.selectByCreateTime(beforeWeek, today,Integer.valueOf(startBatch), Integer.valueOf(endBatch));
			//查询数据库
			System.out.println("线程【"+Thread.currentThread().getName()+"】,游标:【"+offset+"】,startBatch:"+startBatch+",endBatch"+endBatch);
			/**
			 * 统计手机型号数据查询
			 */
			return null;

		}

	}

}
