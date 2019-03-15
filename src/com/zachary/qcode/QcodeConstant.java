/**
 * 
 */
package com.zachary.qcode;

import java.util.HashMap;
import java.util.Map;

public class QcodeConstant {
	
	
	public static class Qcode{
		public String busiCode;
		private String busiName;
		/**
		 * @return busiCode
		 */
		public String getBusiCode() {
			return busiCode;
		}
		/**
		 * @param busiCode 要设置的 busiCode
		 */
		public void setBusiCode(String busiCode) {
			this.busiCode = busiCode;
		}
		/**
		 * @return busiName
		 */
		public String getBusiName() {
			return busiName;
		}
		/**
		 * @param busiName 要设置的 busiName
		 */
		public void setBusiName(String busiName) {
			this.busiName = busiName;
		}
	}

	public static Map<String, String> qcodeMap;
	static {
		qcodeMap = new HashMap<>();
		qcodeMap.put("21411", "11");
		

	}

}
