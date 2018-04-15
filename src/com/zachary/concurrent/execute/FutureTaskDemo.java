/**
 * 
 */
package com.zachary.concurrent.execute;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author 张程
 * @desc 多线程统一处理等待执行完毕，挂挡单个线程执行完毕后处理其它业务 比如‘记录日志分析’
 */
public class FutureTaskDemo {
	
	public static void main(String[] args) {  
		
        ExecutorService executor = Executors.newCachedThreadPool();  
        
        for(int i=0; i<5; i++) {  
           // Callable<String> c = new Task();  
            MyFutureTask ft = new MyFutureTask(new Task());  
            executor.submit(ft);  
        }  
        executor.shutdown();  
    }  
	
	
	
	static class MyFutureTask extends FutureTask<String> {  
		  
	    public MyFutureTask(Callable<String> callable) {  
	        super(callable);  
	    }  
	  
	    @Override  
	    protected void done() {  
	        try {  
	            System.out.println(get() + " 线程执行完毕！~");  
	        } catch (InterruptedException | ExecutionException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	    }  
	      
	      
	}  
	
	
	static class Task implements Callable<String> {  
		  
	    @Override  
	    public String call() throws Exception {  
	        Random rand = new Random();  
	        TimeUnit.SECONDS.sleep(rand.nextInt(12));  
	        return Thread.currentThread().getName();  
	    }
	}

}
