/**
 * 
 */
package com.zachary.qcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


import com.alibaba.fastjson.JSONObject;
import com.zachary.qcode.QcodeConstant.Qcode;

/**
 * @author zhang/7_8
 *         IBFQBZDEeZakBJFq_IYDwAGoPuO9vf0WFAt2LU0n9igyPixxSa91InSL_voXlAPaRrLwZitC0eWC04_DLCB_6z0kDT5L3d1u6pZwcV6zo
 *         -nTH1jYYjcdDeX0AoQWCgADATZF {"ticket":
 *         "gQH98DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyVGFBNlp2TkdlY2gxQkkwTGhxY0MAAgTsBaZaAwSAOgkA"
 *         ,"expire_seconds":604800,"url":
 *         "http:\/\/weixin.qq.com\/q\/02TaA6ZvNGech1BI0LhqcC"}
 */
public class qcodeTest {

	private static String APPID = "wx99bfd554b65dbe61";
	private static String APPSECRET = "716b3ee397ed70d9ef89588167c43dd1";

	static ExecutorService executorService= Executors.newFixedThreadPool(6);
	private static String token;
	static{
		try {
			token = GetToken.GetToken(APPID, APPSECRET);
			//System.out.println("Token 消息:"+token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		try {
			List<Qcode> qcodeList=new ArrayList<Qcode>();
			
			Map<String,String> qcodeMap=QcodeConstant.qcodeMap;
			for(String qocde:qcodeMap.keySet()){
				Qcode qcode=new Qcode();
				qcode.setBusiCode(qocde);
				qcode.setBusiName(qcodeMap.get(qocde));
				qcodeList.add(qcode);
			}
			
			System.out.println(JSONObject.toJSONString(qcodeList));
			
			int importNum=qcodeList.size();
			int count = 20;
			int p_number = (importNum- 1) / count + 1;
			
			List<Future<String>> futureTaskList=new ArrayList<Future<String>>();
			for (int i = 0; i < p_number+1; i++) {
				
				final int startBatch = importNum / p_number * i;
				int length = importNum / p_number * (i + 1);
				final List<Qcode> tempMapList = BeanUtils.getSubList(qcodeList, startBatch,length);
				System.out.println(JSONObject.toJSONString(tempMapList));
				
				Future<String> future= executorService.submit(new Task(tempMapList));
				futureTaskList.add(future);
			}
			executorService.shutdown();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	
	static class Task implements Callable<String>{
		
		private List<Qcode> tempMapList;
		public Task(List<Qcode> tempMapList){
			this.tempMapList=tempMapList;
		}
		@Override
		public String call() throws Exception {
			
			String qcodeUrl="";
			for(Qcode qcode:tempMapList){
				// 获取token
				String ticket = GetTicket.getTicket(token, qcode.getBusiCode());
				qcodeUrl=GetQcode.getQcode(ticket, qcode.getBusiName() + ".png");
				Thread.sleep(500);
			}
			return qcodeUrl;
			
		}
		
	}

}
