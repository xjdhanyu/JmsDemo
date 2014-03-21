package org.message.spring.jms.integration;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.message.dto.UserMessage;
import org.message.spring.mailSender.MailSenderService;
import org.message.util.DateUtils;

public class ReceiveMessageListener implements MessageListener{

	private static final Logger log = Logger.getLogger(ReceiveMessageListener.class);
	
	private MailSenderService mailSenderServcie;
	
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage text = (TextMessage) message;
			try {
				log.info("Received message:" + text.getText() );
				log.info("发送邮件前：" + DateUtils.dateToStr(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS_SSS));
				this.sendMail(text.getText());
				log.info("发送邮件后：" + DateUtils.dateToStr(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS_SSS));
			} catch (JMSException e) {
				log.error(e);
			} catch (Exception e) {
				log.error(e);
			}
		}
	}

	private void sendMail(String text) {
		UserMessage um = new UserMessage();
		um.setText(text);
		this.mailSenderServcie.sendMessage(um);
	}

	public void setMailSenderServcie(MailSenderService mailSenderServcie) {
		this.mailSenderServcie = mailSenderServcie;
	}
	
}
