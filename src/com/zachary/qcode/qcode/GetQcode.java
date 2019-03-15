/**
 * 
 */
package com.zachary.qcode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class GetQcode {

	

	public static String getQcode(String ticket,String name) throws Exception {
		String qcodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?";
		qcodeUrl += "ticket=" + ticket;
		/*String tokenHttp = HttpUtil.doGet(qcodeUrl);
		String tokenRes = returnMap.get("access_token") + "";
		System.out.println(JSONObject.toJSONString(result.getResponse()));*/
		System.out.println("qcodeUrl:>>"+qcodeUrl);
		download(qcodeUrl, name, "D:/qcode");
		return qcodeUrl;

	}

	public static void download(String urlString, String filename,
			String savePath) throws Exception {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		// 设置请求超时为5s
		con.setConnectTimeout(5 * 1000);
		// 输入流
		InputStream is = con.getInputStream();

		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}

}
