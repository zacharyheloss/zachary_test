/**
 * 
 */
package com.zachary.qcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSONObject;
import com.zachary.qcode.QcodeConstant.Qcode;

/**
 * * @多线程生成二维码 CountDownLatch
 * 截取List
 * @author zhang/7_8
 *         IBFQBZDEeZakBJFq_IYDwAGoPuO9vf0WFAt2LU0n9igyPixxSa91InSL_voXlAPaRrLwZitC0eWC04_DLCB_6z0kDT5L3d1u6pZwcV6zo
 *         -nTH1jYYjcdDeX0AoQWCgADATZF {"ticket":
 *         "gQH98DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyVGFBNlp2TkdlY2gxQkkwTGhxY0MAAgTsBaZaAwSAOgkA"
 *         ,"expire_seconds":604800,"url":
 *         "http:\/\/weixin.qq.com\/q\/02TaA6ZvNGech1BI0LhqcC"}
 */
public class qcodeTest1 {

	private static String APPID = "wx4fb76ce7931dc08e";
	private static String APPSECRET = "bf83355d146b43b736aa1f068bdc8056";

	static int threadPool = 6;
	static ExecutorService executorService = Executors
			.newFixedThreadPool(threadPool);
	private static String token;
	static {
		try {
			token = GetToken.GetToken(APPID, APPSECRET);
			// System.out.println("Token ��Ϣ:"+token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			long start = System.currentTimeMillis();
			List<com.zachary.qcode.QcodeConstant.Qcode> qcodeList = new ArrayList<Qcode>();

			Map<String, String> qcodeMap = QcodeConstant.qcodeMap;
			for (String qocde : qcodeMap.keySet()) {
				Qcode qcode = new Qcode();
				qcode.setBusiCode(qocde);
				qcode.setBusiName(qcodeMap.get(qocde));
				qcodeList.add(qcode);
			}

			System.out.println(JSONObject.toJSONString(qcodeList));

			int importNum = qcodeList.size();
			int count = 20;
			int p_number = (importNum - 1) / count + 1;
			CountDownLatch countDownLatch = new CountDownLatch(p_number + 1);
			for (int i = 0; i < p_number + 1; i++) {

				final int startBatch = importNum / p_number * i;
				int length = importNum / p_number * (i + 1);
				final List<Qcode> tempMapList = BeanUtils.getSubList(qcodeList,
						startBatch, length);
				System.out.println(JSONObject.toJSONString(tempMapList));
				executorService.execute(new Task(tempMapList, countDownLatch));
			}

			countDownLatch.await();
			System.out.println("���ɶ�ά���ܺ�ʱ:"
					+ (System.currentTimeMillis() - start) + "/ms");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class Task implements Runnable {

		private List<Qcode> tempMapList;
		private CountDownLatch countDownLatch;

		public Task(List<Qcode> tempMapList, CountDownLatch countDownLatch) {
			this.tempMapList = tempMapList;
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {

			for (Qcode qcode : tempMapList) {
				// ��ȡtoken
				String ticket;
				try {
					ticket = GetTicket.getTicket(token, qcode.getBusiCode());
					GetQcode.getQcode(ticket,
							qcode.getBusiCode() + "_" + qcode.getBusiName()
									+ ".png");
					Thread.sleep(500);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			countDownLatch.countDown();

		}

	}

}
