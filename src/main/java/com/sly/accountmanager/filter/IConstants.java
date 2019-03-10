package com.sly.accountmanager.filter;


/**
 * xss替换
 * @author sly
 * @time 2018年12月12日
 */
public class IConstants {

 
	/**
	 * _需要过滤的字符
	 */
	private final static String[] SQLSTR = { "'", "exec", "execute", "insert",
			 "delete", "update", "drop", "\\%", "master",
			"truncate", "declare", "sitename", "xp_cmdshell", "create",
			"table", "grant", "group_concat", "column", "schema",
			"union", ";--", "<", ">", "\\(", "\\)",
			"eval\\((.*)\\)"
			 };
	/**
	 * _过滤后的字符
	 */
	private final static String[] NSQLSTR = { "＇", "ｅｘｅｃ", "ｅｘｅｃｕｔｅ", "ｉｎｓｅｒｔ",
			 "ｄｅｌｅｔｅ", "ｕｐｄａｔｅ", "ｄｒｏｐ", "％", "ｍａｓｔｅｒ",
			"ｔｒｕｎｃａｔｅ", "ｄｅｃｌａｒｅ", "ｓｉｔｅｎａｍｅ", "ｘｐ＿ｃｍｄｓｈｅｌｌ", "ｃｒｅａｔｅ",
			"ｔａｂｌｅ", "ｇｒａｎｔ", "ｇｒｏｕｐ＿ｃｏｎｃａｔ", "ｃｏｌｕｍｎ", "ｓｃｈｅｍａ",
			"ｕｎｉｏｎ", "；－－", "＜", "＞", "（", "）",
			"ｅｖａｌ＼＼（（．＊）＼＼）"};

	/**
	 * _替换字符
	 * @param value
	 * @return
	 * @author sly
	 * @time 2018年12月12日
	 */
	public static String replaceStr(String value) {
		// 循环比较SQL和xss关键字
		for (int i = 0; i < SQLSTR.length; i++) { 
			value = value.replaceAll("(?i)" + SQLSTR[i], NSQLSTR[i]);
		}
		return value;
	}
}
