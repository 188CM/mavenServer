package com.gscdn.model;

import javax.servlet.http.HttpServletRequest;

import com.gscdn.util.GsUtil;

public class AccessLog {
	String ip;
	String browser;
	String language;
	String time;
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public void setAccessLog(HttpServletRequest req)
	{
		setIp(req.getRemoteAddr());
		setBrowser(req.getHeader("User-Agent"));
		setLanguage(req.getHeader("accept-language"));
		setTime(GsUtil.getToday());
		
	}
}
