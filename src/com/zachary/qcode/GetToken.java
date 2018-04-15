/**
 * 
 */
package com.zachary.qcode;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zhang/7_8
 *         IBFQBZDEeZakBJFq_IYDwAGoPuO9vf0WFAt2LU0n9igyPixxSa91InSL_voXlAPaRrLwZitC0eWC04_DLCB_6z0kDT5L3d1u6pZwcV6zo
 *         -nTH1jYYjcdDeX0AoQWCgADATZF {"ticket":
 *         "gQH98DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyVGFBNlp2TkdlY2gxQkkwTGhxY0MAAgTsBaZaAwSAOgkA"
 *         ,"expire_seconds":604800,"url":
 *         "http:\/\/weixin.qq.com\/q\/02TaA6ZvNGech1BI0LhqcC"}
 */
public class GetToken {


	public static  String GetToken(String APPID, String APPSECRET) throws Exception {

		if (StringUtils.isBlank(APPID) || StringUtils.isBlank(APPSECRET)) {
			throw new NullPointerException("APPID or APPSECRET is null!");
		}
		String getToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
		getToken += "&appid=" + APPID + "&secret=" + APPSECRET;

		String tokenHttp = HttpUtil.doGet(getToken);
		Map<String, Object> returnMap = JSONObject.parseObject(tokenHttp,Map.class);
		String tokenRes=returnMap.get("access_token") + "";
		//System.out.println("get token:"+tokenRes);
		return tokenRes;

	}

}
