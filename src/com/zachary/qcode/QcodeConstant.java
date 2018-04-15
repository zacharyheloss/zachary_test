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
		qcodeMap.put("21411", "奉贤一部Q1");
		qcodeMap.put("21412", "奉贤一部Q6");
		qcodeMap.put("21430", "松江八部Q1");
		qcodeMap.put("21059", "松江九部");
		qcodeMap.put("21053", "松江三部");
		qcodeMap.put("21112", "闸北二部");
		qcodeMap.put("02100", "上海分拨中心");
		qcodeMap.put("21355", "浦东三十部");
		qcodeMap.put("02153", "青浦二十五部");
		qcodeMap.put("21682", "宝山二十六部Q8");
		qcodeMap.put("51558", "闵行六部");
		qcodeMap.put("88632", "松江十部Q3");
		qcodeMap.put("21326", "上海翡迅C");
		qcodeMap.put("21131", "虹口一部");
		qcodeMap.put("21005", "浦东五部");
		qcodeMap.put("02157", "上海营业部");
		qcodeMap.put("21251", "松江十五部");
		qcodeMap.put("21068", "徐汇八部");
		qcodeMap.put("21156", "奉贤三部");
		qcodeMap.put("21056", "松江六部");
		qcodeMap.put("21120", "松江十二部");
		qcodeMap.put("21121", "宝山一部");
		qcodeMap.put("21127", "宝山七部");
		qcodeMap.put("21211", "闵行二十部");
		qcodeMap.put("21115", "闸北五部");
		qcodeMap.put("21075", "青浦五部");
		qcodeMap.put("21209", "闵行十八部");
		qcodeMap.put("21080", "青浦十部");
		qcodeMap.put("21104", "南汇四部");
		qcodeMap.put("21113", "闸北三部");
		qcodeMap.put("21133", "虹口三部");
		qcodeMap.put("21095", "黄浦五部");
		qcodeMap.put("21203", "闵行十一部");
		qcodeMap.put("21198", "金山一部A");
		qcodeMap.put("21161", "卢湾一部");
		qcodeMap.put("21261", "青浦二十一部");
		qcodeMap.put("21281", "崇明岛");
		qcodeMap.put("21067", "徐汇七部");
		qcodeMap.put("21293", "上海镖旗C");
		qcodeMap.put("21182", "普陀十一部");
		qcodeMap.put("21091", "黄浦一部");
		qcodeMap.put("21065", "徐汇五部");
		qcodeMap.put("21199", "金山二部");
		qcodeMap.put("21138", "嘉定三部");
		qcodeMap.put("21196", "长兴岛一部");
		qcodeMap.put("21237", "黄浦十部");
		qcodeMap.put("21033", "普陀三部");
		qcodeMap.put("21029", "闵行九部");
		qcodeMap.put("21107", "南汇七部");
		qcodeMap.put("21229", "青浦十五部");
		qcodeMap.put("21093", "黄浦三部");
		qcodeMap.put("02171", "松江十八部");
		qcodeMap.put("21021", "闵行一部");
		qcodeMap.put("21023", "闵行三部");
		qcodeMap.put("21022", "闵行二部");
		qcodeMap.put("21043", "长宁三部");
		qcodeMap.put("21143", "杨浦三部");
		qcodeMap.put("21027", "闵行七部");
		qcodeMap.put("21106", "南汇六部");
		qcodeMap.put("21063", "徐汇三部");
		qcodeMap.put("21051", "松江一部");
		qcodeMap.put("21259", "青浦二十部");
		qcodeMap.put("21058", "松江八部");
		qcodeMap.put("21284", "上海D代派");
		qcodeMap.put("21099", "青浦一部");
		qcodeMap.put("21062", "徐汇二部");
		qcodeMap.put("21090", "浦东三部");
		qcodeMap.put("21079", "青浦九部");
		qcodeMap.put("21126", "宝山六部");
		qcodeMap.put("21025", "闵行五部");
		qcodeMap.put("21157", "奉贤六部");
		qcodeMap.put("21167", "闸北十一部");
		qcodeMap.put("21085", "嘉定五部");
		qcodeMap.put("21215", "闵行二十三部");
		qcodeMap.put("21073", "青浦三部");
		qcodeMap.put("51201", "昆山分拨中心");
		qcodeMap.put("21266", "虹口一部Q1");
		qcodeMap.put("88615", "松江十部");
		qcodeMap.put("21064", "徐汇四部");
		qcodeMap.put("21216", "闵行二十一部");
		qcodeMap.put("21084", "嘉定四部");
		qcodeMap.put("21012", "浦东十二部");
		qcodeMap.put("59608", "松江二部");
		qcodeMap.put("21082", "嘉定二部");
		qcodeMap.put("21002", "浦东二部");
		qcodeMap.put("21145", "杨浦五部");
		qcodeMap.put("21230", "上海凯雅C");
		qcodeMap.put("21219", "闵行十七部");
		qcodeMap.put("02107", "浦东六部");
		qcodeMap.put("02134", "宝山十二部");
		qcodeMap.put("21134", "宝山");
		qcodeMap.put("21083", "嘉定九部");
		qcodeMap.put("21148", "南汇一部");
		qcodeMap.put("21285", "嘉定十二部");
		qcodeMap.put("21044", "闵行二十六部");
		qcodeMap.put("21151", "奉贤一部");
		qcodeMap.put("27683", "南汇二十部");
		qcodeMap.put("02136", "上海项目部");
		qcodeMap.put("21339", "宝山二十六部");
		qcodeMap.put("21348", "南汇十二部");
		qcodeMap.put("21124", "宝山十一部");
		qcodeMap.put("21333", "上海火红C");
		qcodeMap.put("21149", "宝山二十一部");
		qcodeMap.put("31682", "上海全新");
		qcodeMap.put("21158", "松江十六部");
		qcodeMap.put("21652", "浦东五部Q1");
		qcodeMap.put("21150", "嘉定十六部");
		qcodeMap.put("21155", "嘉定十八部");
		qcodeMap.put("21402", "奉贤九部");
		qcodeMap.put("21312", "南汇十六部");
		qcodeMap.put("21416", "宝山十二部Q1");
		qcodeMap.put("21048", "长宁八部");
		qcodeMap.put("21250", "静安八部");
		qcodeMap.put("21340", "浦东三十一部");
		qcodeMap.put("21109", "南汇九部");
		qcodeMap.put("02158", "青浦二十六部");
		qcodeMap.put("21680", "静安二部H1");
		qcodeMap.put("31673", "杨浦三部Q3");
		qcodeMap.put("21231", "长宁十一部");
		qcodeMap.put("21349", "宝山十部");
		qcodeMap.put("21187", "静安六部");
		qcodeMap.put("21247", "普陀九部");
		qcodeMap.put("21212", "徐汇七部Q1");
		qcodeMap.put("02137", "黄浦十二部");
		qcodeMap.put("21370", "南汇九部Q1");
		qcodeMap.put("21190", "宝山二十八部");
		qcodeMap.put("21301", "浦东一部");
		qcodeMap.put("21210", "闵行十九部");
		qcodeMap.put("21011", "浦东十一部");
		qcodeMap.put("21650", "嘉定十九部Q1");
		qcodeMap.put("21074", "徐汇十一部");
		qcodeMap.put("21414", "奉贤一部Q2");
		qcodeMap.put("21359", "浦东三十部Q1");
		qcodeMap.put("21270", "嘉定六部");
		qcodeMap.put("21207", "闵行十六部");
		qcodeMap.put("21643", "普陀十九部Q1");
		qcodeMap.put("21644", "嘉定一部Q5");
		qcodeMap.put("21221", "松江十一部");
		qcodeMap.put("21028", "闵行八部");
		qcodeMap.put("02146", "上海嘉定京东");
		qcodeMap.put("21102", "南汇二部");
		qcodeMap.put("21098", "青浦十三部");
		qcodeMap.put("21213", "奉贤八部");
		qcodeMap.put("21406", "杨浦三部Q1");
		qcodeMap.put("21245", "普陀十九部");
		qcodeMap.put("21360", "浦东一部Q1");
		qcodeMap.put("88616", "松江六部Q2");
		qcodeMap.put("21357", "嘉定十九部");
		qcodeMap.put("80125", "上海施耐德C");
		qcodeMap.put("21175", "卢湾六部");
		qcodeMap.put("21415", "宝山十二部Q2");
		qcodeMap.put("21417", "宝山十二部Q3");
		qcodeMap.put("21363", "浦东十二部Q1");
		qcodeMap.put("27699", "普陀五部");
		qcodeMap.put("21358", "青浦九部Q1");
		qcodeMap.put("88613", "上海雅仕C");
		qcodeMap.put("21114", "南汇二部Q1");
		qcodeMap.put("21432", "闵行九部Q5");
		qcodeMap.put("21431", "松江八部Q2");
		qcodeMap.put("88618", "宝山三部");
		qcodeMap.put("21648", "青浦十五部H01");
		qcodeMap.put("21649", "闵行十九部Q3");
		qcodeMap.put("21421", "松江九部Q1");
		qcodeMap.put("21428", "虹口六部Q1");
		qcodeMap.put("21436", "奉贤一部DQ1");
		qcodeMap.put("21364", "南汇九部H01");
		qcodeMap.put("21433", "徐汇四部Q3");
		qcodeMap.put("21304", "南汇一部Q2");
		qcodeMap.put("88617", "松江十部Q1");
		qcodeMap.put("21435", "松江三部Q1");
		qcodeMap.put("21367", "浦东十部");
		qcodeMap.put("21369", "普陀十八部Q1");
		qcodeMap.put("21667", "闵行十七部Q2");
		qcodeMap.put("21663", "静安一部Q1");
		qcodeMap.put("50530", "浦东二十部");
		qcodeMap.put("21669", "浦东九部");
		qcodeMap.put("21700", "南汇十八部");
		qcodeMap.put("21373", "徐汇三部Q2");
		qcodeMap.put("21662", "浦东五部Q2");
		qcodeMap.put("21676", "普陀十九部Q2");
		qcodeMap.put("88633", "闵行八部Q2");
		qcodeMap.put("21646", "奉贤六部Q2");
		qcodeMap.put("21665", "宝山十二部Q4");
		qcodeMap.put("21372", "闵行二部Q2");
		qcodeMap.put("21671", "嘉定四部Q2");
		qcodeMap.put("21657", "青浦二十一部Q1");
		qcodeMap.put("21382", "浦东七部");
		qcodeMap.put("21443", "上海羊绒H002");
		qcodeMap.put("02168", "嘉定七部");
		qcodeMap.put("27696", "南汇二十八部");
		qcodeMap.put("21703", "上海群大H001");
		qcodeMap.put("21701", "浦东十八部");
		qcodeMap.put("88636", "长宁九部");
		qcodeMap.put("27688", "南汇二十部H01");
		qcodeMap.put("27679", "普陀十九部Q3");
		qcodeMap.put("21681", "奉贤十六部");
		qcodeMap.put("21706", "浦东八部");
		qcodeMap.put("27689", "普陀一部");
		qcodeMap.put("31385", "青浦六部");
		qcodeMap.put("27687", "松江七部");
		qcodeMap.put("27686", "闵行三十部");
		qcodeMap.put("21385", "松江一部Q3");
		qcodeMap.put("27685", "闵行十五部");
		qcodeMap.put("27682", "青浦一部Q1");
		qcodeMap.put("27690", "徐汇十一部H1");
		qcodeMap.put("21081", "嘉定一部");
		qcodeMap.put("21137", "浦东二十一部");
		qcodeMap.put("21386", "徐汇十部");
		qcodeMap.put("21205", "虹口六部");
		qcodeMap.put("21447", "闸北十二部");
		qcodeMap.put("21387", "浦东十三部");
		qcodeMap.put("27670", "松江二十部");
		qcodeMap.put("21451", "上海330营业部");
		qcodeMap.put("21452", "普陀四部");
		qcodeMap.put("21453", "宝山二十一部Q1");
		qcodeMap.put("21450", "嘉定十部");
		qcodeMap.put("27695", "青浦一部Q3");
		qcodeMap.put("27698", "长宁十八部");
		qcodeMap.put("27697", "嘉定二部H01");
		qcodeMap.put("21449", "长宁十部");
		qcodeMap.put("27693", "青浦一部Q2");
		qcodeMap.put("88647", "闵行二十一部Q2");
		qcodeMap.put("21377", "静安二部");
		qcodeMap.put("27692", "松江十九部");
		qcodeMap.put("21032", "普陀二部");

	}

}
