package org.message.spring.jms.integration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.message.util.DateUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class SendMessageController extends MultiActionController{
	private String successView;
	private MessageSender messageSender;
	
	private static Logger log = Logger.getLogger(SendMessageController.class);

	public ModelAndView sendMessage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		String message = request.getParameter("message");
		log.info("发送消息前：" + DateUtils.dateToStr(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS_SSS));
		messageSender.sendMessage(message);
		log.info("发送消息后：" + DateUtils.dateToStr(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS_SSS));
		return new ModelAndView(successView, retMap);
	}
	
	public static void main(String[] args){
		
	}

	public String getSuccessView() {
		return successView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public MessageSender getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}
}
