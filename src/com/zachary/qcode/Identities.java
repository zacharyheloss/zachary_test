package com.zachary.qcode;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @desc uuid日期化封装
 * @author zhangcheng
 * @since ccp-core-1.0.0-SNAPSHOT
 */
public class Identities {

	
	private static SecureRandom random = new SecureRandom();
	private static final ReentrantLock reenLock = new ReentrantLock();

	/** 初始值 */
	private static int serial = 0;

	/**
	 * 最大序列值：999
	 */
	private static final int MAX_SERIAL = 999;

	/**
	 * 长度：12
	 */
	private static final int SEQUENCE_LENTH = 12;

	/**
	 * 同步方式uuid生成策略<br>
	 * 非公平竞争情况下，获取锁生成日期uuid 否则生成uuid
	 */
	public static String makeUUID() {
		if (reenLock.tryLock()) {
			try {
				return aSyncMakeUUID();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				reenLock.unlock();
			}
		}
		return uuid();
	}

	/**
	 * 非同步方式uuid生成策略
	 */
	public static String aSyncMakeUUID() {
		StringBuilder ret = new StringBuilder();
		SimpleDateFormat dfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		dfDate.setLenient(false);
		ret.append(dfDate.format((new GregorianCalendar()).getTime()));

		DecimalFormat dfNum = new DecimalFormat("000");
		ret.append(dfNum.format(serial++));

		if (serial > MAX_SERIAL) {
			serial = 0;
		}
		//生成UUID
		UUID uuid = UUID.randomUUID();
		ret.append(uuid.toString().replace("-", "")
				.subSequence(0, SEQUENCE_LENTH));
		return ret.toString();
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		long next = random.nextLong();
		if(next != 0){
			Math.abs(next);
		}
		return 0l;
	}

	/**
	 * 生成UUID
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	
	public static void main(String[] args) {
		Integer threadCount=100;
		Thread[]  threads=new Thread[threadCount];
		for(int i=0;i<threadCount;i++){
			threads[i]=new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(makeUUID());
					
				}
			});
			threads[i].start();
		}
	}

}
