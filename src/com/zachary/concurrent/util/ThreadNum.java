/**
 * 
 */
package com.zachary.concurrent.util;

/**
 * @author zhang
 * 
 */
public class ThreadNum {

	/**
	 * 
	 * @desc: ��ȡ���غ��ʵ��߳�����
	 * @return
	 * @return int
	 */
	public int getThreadCount() {
		return Runtime.getRuntime().availableProcessors();
	}

}
