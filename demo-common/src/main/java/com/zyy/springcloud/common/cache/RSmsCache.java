package com.zyy.springcloud.common.cache;

import com.zyy.springcloud.cache.core.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class RSmsCache {
	private static final Logger logger = LoggerFactory.getLogger(RSmsCache.class);

	@Autowired
	@Qualifier("smsCache")
    Cache smsCache;

	/**
	 * @throws Exception @Title: setSmsCode @Description:
	 * 设置短信验证码进session @param @param request @param @param code 设定文件 @return
	 * void 返回类型 @throws
	 */
	public void setSmsCode(String mobile, String code) throws Exception {
		smsCache.set(mobile + "_sms_code", code);
	}

	/**
	 * @throws Exception @Title: getSmsCode @Description: 返回短信验证码 @param @param
	 * request @param @return 设定文件 @return String 返回类型 @throws
	 */
	public String getSmsCode(String mobile) throws Exception {
		return (String) smsCache.get(mobile + "_sms_code");
	}

	/**
	 * @throws Exception @Title: removeSmsCode @Description:
	 * 移除短信验证码 @param @param request 设定文件 @return void 返回类型 @throws
	 */
	public void removeSmsCode(String mobile) throws Exception {
		smsCache.remove(mobile + "_sms_code");
	}

	public void setSmsCodeTimeout(String mobile, Date date) throws Exception {
		smsCache.set(mobile + "_sms_code_timeout", date);
	}

	public Date getSmsCodeTimeout(String mobile) throws Exception {
		return (Date) smsCache.get(mobile + "_sms_code_timeout");
	}

	/**
	 * @throws Exception @Title: setSmsCodeTimeout @Description:
	 * 设置短信重发时间 @param @param request 设定文件 @return void 返回类型 @throws
	 */
	public void setSmsCodeTimeout(Date date, String mobile, String openid) throws Exception {
		smsCache.set(openid + "_sms_code_timeout", date);
	}

	/**
	 * @throws Exception @Title: getSmsCodeTimeout @Description:
	 * 得到短信重发时间 @param @param request @param @return 设定文件 @return String
	 * 返回类型 @throws
	 */
	public Date getSmsCodeTimeout(String mobile, String openid) throws Exception {
		return (Date) smsCache.get(openid + "_sms_code_timeout");
	}

	/**
	 * @throws Exception @Title: setSmsCodeCount @Description:
	 * 设置短信已发送条数 @param @param request @param @param count 设定文件 @return void
	 * 返回类型 @throws
	 */
	public void setSmsCodeCount(int count, String mobile, String openid) throws Exception {
		Date date = getSmsCodeTimeout(mobile, openid);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(new Date());
		c2.setTime(date != null ? date : new Date());
		int year = c1.get(Calendar.YEAR);// 获取年份
		int month = c1.get(Calendar.MONTH) + 1;// 获取月份
		int day = c1.get(Calendar.DATE);// 获取日
		String befor = year + "-" + month + "-" + day;

		int year2 = c2.get(Calendar.YEAR);// 获取年份
		int month2 = c2.get(Calendar.MONTH) + 1;// 获取月份
		int day2 = c2.get(Calendar.DATE);// 获取日

		String after = year2 + "-" + month2 + "-" + day2;
		if (befor.equals(after) || count == 0) {
			count = count + 1;
		} else {
			count = 0;
		}
		smsCache.set(openid + "_sms_code_count", count);
	}
	
	public void setSmsCodeCount(int count, String mobile) throws Exception {
		Date date = getSmsCodeTimeout(mobile);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(new Date());
		c2.setTime(date != null ? date : new Date());
		int year = c1.get(Calendar.YEAR);// 获取年份
		int month = c1.get(Calendar.MONTH) + 1;// 获取月份
		int day = c1.get(Calendar.DATE);// 获取日
		String befor = year + "-" + month + "-" + day;

		int year2 = c2.get(Calendar.YEAR);// 获取年份
		int month2 = c2.get(Calendar.MONTH) + 1;// 获取月份
		int day2 = c2.get(Calendar.DATE);// 获取日

		String after = year2 + "-" + month2 + "-" + day2;
		if (befor.equals(after) || count == 0) {
			count = count + 1;
		} else {
			count = 0;
		}
		smsCache.set(mobile + "_sms_code_count", count);
	}

	/**
	 * @throws Exception @Title: getSmsCodeCount @Description:
	 * 得到短信已发送条数 @param @param request @param @return 设定文件 @return String
	 * 返回类型 @throws
	 */
	public int getSmsCodeCount(String mobile, String openid) throws Exception {
		Date date = getSmsCodeTimeout(mobile, openid);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(new Date());
		c2.setTime(date != null ? date : new Date());
		int year = c1.get(Calendar.YEAR);// 获取年份
		int month = c1.get(Calendar.MONTH) + 1;// 获取月份
		int day = c1.get(Calendar.DATE);// 获取日
		String befor = year + "-" + month + "-" + day;

		int year2 = c2.get(Calendar.YEAR);// 获取年份
		int month2 = c2.get(Calendar.MONTH) + 1;// 获取月份
		int day2 = c2.get(Calendar.DATE);// 获取日
		String after = year2 + "-" + month2 + "-" + day2;

		Object count = 0;
		if (befor.equals(after)) {
			count = smsCache.get(openid + "_sms_code_count");
		}
		return count == null ? 0 : (int) count;
	}
	
	public int getSmsCodeCount(String mobile) throws Exception {
		Date date = getSmsCodeTimeout(mobile);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(new Date());
		c2.setTime(date != null ? date : new Date());
		int year = c1.get(Calendar.YEAR);// 获取年份
		int month = c1.get(Calendar.MONTH) + 1;// 获取月份
		int day = c1.get(Calendar.DATE);// 获取日
		String befor = year + "-" + month + "-" + day;

		int year2 = c2.get(Calendar.YEAR);// 获取年份
		int month2 = c2.get(Calendar.MONTH) + 1;// 获取月份
		int day2 = c2.get(Calendar.DATE);// 获取日
		String after = year2 + "-" + month2 + "-" + day2;

		Object count = 0;
		if (befor.equals(after)) {
			count = smsCache.get(mobile + "_sms_code_count");
		}
		return count == null ? 0 : (int) count;
	}
}
