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
	 * @desc: 获取本地合适的线程数量
	 * @return
	 * @return int
	 */
	public int getThreadCount() {
		return Runtime.getRuntime().availableProcessors();
	}

}
