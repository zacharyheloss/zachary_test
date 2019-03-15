package com.zachary.qcode;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;


/**
 * @ClassName:     HttpUtil.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         lipeng
 * @version        V1.0  
 * @email          lipeng@zjian.net 
 * @Date           2015年10月29日 下午1:55:41 
 */


public class HttpUtil {



	public static String postNotice(String postUrl, String param) {
		HttpURLConnection conn = null;
		try {
			URL url = new URL(postUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=GBK");
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(10000);

			OutputStream out = conn.getOutputStream();
			out.write(param.getBytes("GBK"));
			out.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
			return JSON.toJSONString(sb.toString());
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

	}
	/***
	 * http发送get请求
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static  String doGet(String url) throws Exception{
		String result="";
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpGet httpget = new HttpGet(url);
		try {
			//配置请求的超时设置
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(10000).setConnectTimeout(10000).setSocketTimeout(10000).build();
			httpget.setConfig(requestConfig);
			CloseableHttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			result= EntityUtils.toString(entity,"UTF-8");//, "utf-8");
		} catch (Exception e) {
		}finally{
			httpget.releaseConnection();
		}
		return result;
	}
	
	

	
	private final static int BUFFER = 1024;
	
	/***
	 * 调用接口下载文件
	 * @param url
	 * @param
	 * @throws IOException 
	 */
	public static String downFile(String url,String filePath,String downName) throws IOException {
		String fileName="";
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpGet httpget = new HttpGet(url);
	   InputStream in =null;
	   FileOutputStream out =null;
		try {
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			in = entity.getContent();

			if(null==downName){
				Header[] headerResponse = response.getAllHeaders();
				String headerStr = "";
				for(int i=0;i<headerResponse.length;i++){
					if("Content-disposition".equals(headerResponse[i].getName())){
						headerStr= headerResponse[i].getValue();
					}
				}
				if(!"".equals(headerStr)){
					fileName=headerStr.substring(headerStr.indexOf("\"")+1, headerStr.lastIndexOf("\""));
//				if(fileName.lastIndexOf(".amr")!=-1){//amr格式的文件，转换成mp3
//					String newFileName=fileName.replace(".amr", ".mp3");
//					String sourcePath=filePath+fileName;
//					String targetPath=filePath+newFileName;
//					fileName=newFileName;
//
//				}
				}
			}else{
				fileName=downName;
			}

			File file =new File(filePath);
			if  (!file .exists()  && !file .isDirectory()){//不存在，创建文件夹
				file .mkdir();
			}
			out = new FileOutputStream(new File(filePath+fileName));
			byte[] b = new byte[BUFFER];
			int len = 0;
			while((len=in.read(b))!= -1){
				out.write(b,0,len);
			}
			out.flush();

		}catch (Exception e){
		}finally{
			in.close();
			out.close();
			httpget.releaseConnection();
		}
       	return fileName;
       }

	/***
	 *
	 * @param url
	 * @param json
	 * @param contentType "json",null
	 * @return
     * @throws Exception
     */
	public static String postJson(String url,String json,String contentType) throws Exception{
        // 目标地址    
        HttpPost httppost = new HttpPost(url); 
        String body="";
		try {
			CloseableHttpClient httpclient = HttpClientBuilder.create().build();   
	        // 构造最简单的字符串数据    
	         StringEntity reqEntity = new StringEntity(json,"UTF-8");
	        // 设置类型    
	         reqEntity.setContentType("application/x-www-form-urlencoded");
			if("json".equals(contentType)){
				reqEntity.setContentType("application/json;charset=utf-8");

			}
	        // 设置请求的数据    
	         httppost.setEntity(reqEntity);    
	        // 执行    
	        HttpResponse httpresponse = httpclient.execute(httppost);
	        HttpEntity entity = httpresponse.getEntity();  
	        body = EntityUtils.toString(entity,"UTF-8");
		} catch (Exception e) {
		}finally{
	        httppost.releaseConnection();  
		}
        return body;
	}



	/***
	 *
	 */
	public static String postJson(String url,String json) throws Exception{
		// 目标地址
		HttpPost httppost = new HttpPost(url);
		String body="";
		try {
			CloseableHttpClient httpclient = HttpClientBuilder.create().build();
			// 构造最简单的字符串数据
			StringEntity reqEntity = new StringEntity(json,"UTF-8");
			// 设置类型
			reqEntity.setContentType("application/x-www-form-urlencoded;charset=GBK");
			// 设置请求的数据
			httppost.setEntity(reqEntity);
			// 执行
			HttpResponse httpresponse = httpclient.execute(httppost);
			HttpEntity entity = httpresponse.getEntity();
			body = EntityUtils.toString(entity,"UTF-8");
		} catch (Exception e) {
		}finally{
			httppost.releaseConnection();
		}
		return body;
	}
/***
 * postxml格式数据
 * 
 * @return
 */
//public static String postXML(String requestUrl, String xmlData,
//		String contentType, String charset) {
//	logger.info("postXML数据：" + xmlData);
//	HttpClient httpclient = new HttpClient();
//	// timeout set
//	httpclient.getHttpConnectionManager().getParams()
//			.setConnectionTimeout(20000);
//	httpclient.getHttpConnectionManager().getParams()
//			.setSoTimeout(20000);
//	PostMethod post = new PostMethod(requestUrl);
//	String bodyContent = "";
//	try {
//		RequestEntity requestEntity = new StringRequestEntity(xmlData,
//				contentType, charset);
//		post.setRequestEntity(requestEntity);
//		httpclient.executeMethod(post);
//		int statusCode = post.getStatusCode();
//		if (statusCode == HttpStatus.SC_OK) {
//			BufferedReader reader = new BufferedReader(
//					new InputStreamReader(post.getResponseBodyAsStream(),
//							"UTF-8"));
//			StringBuffer stringBuffer = new StringBuffer();
//			while ((bodyContent = reader.readLine()) != null) {
//				stringBuffer.append(bodyContent);
//			}
//			bodyContent = stringBuffer.toString();
//
//		}
//	} catch (Exception e) {
//		logger.error(ExceptionUtil.getExceptionInfo(e));
//	} finally {
//		post.releaseConnection();
//	}
//	return bodyContent;
//
//}



	/***
	 * post map 数据
	 * @param url
	 * @param prarmsMap
	 * @param charset
	 * @return
	 */
	public static String postMap(String url, Map<String, String> prarmsMap,String charset) {
		String content="";
		CloseableHttpResponse response = null;
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Content-Type","application/x-www-form-urlencoded;charset="+charset);
		RequestConfig config = RequestConfig.custom()
				.setConnectionRequestTimeout(10000).setConnectTimeout(10000)
				.setSocketTimeout(10000).build();
		try{
			// 设置Http Post数据
			if (prarmsMap != null) {
				List formparams = new ArrayList();
				Iterator<Map.Entry<String, String>> iter = prarmsMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> entry = iter.next();
					String key = entry.getKey();
					String val = entry.getValue();
					formparams.add(new BasicNameValuePair(key, val));
				}
				httppost.setEntity(new UrlEncodedFormEntity(formparams, "UTF-8"));
			}


			httppost.setConfig(config);
			response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity);

		} catch (IOException e) {
		} finally {
			httppost.releaseConnection();
		}
		return content;
	}




}



