/**
 * 
 */
package com.zachary.qcode;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhang/
 *{"ticket":"gQH98DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyVGFBNlp2TkdlY2gxQkkwTGhxY0MAAgTsBaZaAwSAOgkA","expire_seconds":604800,"url":"http:\/\/weixin.qq.com\/q\/02TaA6ZvNGech1BI0LhqcC"}
 */
public class GetTicket {
	
	private static String APPID = "wx464fe0211224adf5";
	private static String APPSECRET = "85307b18e72f6530638f631d8f08991e";

	public static String getTicket(String token,String uniqueId) throws Exception{
		String ticketUrl="https://api.weixin.qq.com/cgi-bin/qrcode/create?";
		ticketUrl+="access_token="+token;
		String str="{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\":\""+uniqueId+"\"}}}";
		
		String ticketJson=HttpUtil.postJson(ticketUrl, str,"json");
		Map<String, Object> returnMap = JSONObject.parseObject(ticketJson,Map.class);
		String ticketRes=returnMap.get("ticket")+"";
		//System.out.println("get ticket:"+ticketRes);
		return ticketRes;
		
	}
	
	public static void main(String[] args) throws Exception {
		String token = GetToken.GetToken(APPID, APPSECRET);
		System.out.println(JSONObject.toJSONString(getTicket(token, "dwcwec")));
	}

}
