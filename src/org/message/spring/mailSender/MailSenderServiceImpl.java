package org.message.spring.mailSender;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.message.dto.UserMessage;
import org.message.util.MessageResponse;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class MailSenderServiceImpl implements MailSenderService{
	private Logger log = Logger.getLogger(MailSenderServiceImpl.class);
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;
	
	private String from;
	private String to;
	private String subject;
	private String cc;
	private String templateLocation;

	@Override
	public MessageResponse sendMessage(final UserMessage um) {
		MessageResponse mr = new MessageResponse();
		
		mr.setSucceed(false);
		try {
			MimeMessagePreparator mp = initPreparator(um);
			this.mailSender.send(mp);
			mr.setSucceed(true);
		} catch (MailException e) {
			mr.setMessage(e.getMessage());
			log.error(e);
		}catch(Exception e){
			log.error(e);
		}
		return mr;
	}

	private MimeMessagePreparator initPreparator(final UserMessage um) {
		MimeMessagePreparator mp = new MimeMessagePreparator(){
			public void prepare(MimeMessage mm) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mm);
				um.setTo(to.split(","));
				um.setEmail(from);
				um.setSubject(subject);
				um.setCc(cc.split(","));
				um.setUserName(from.split("@")[0]);
	            message.setTo(um.getTo());
	            message.setFrom(from); 
	            message.setSubject(subject);
	            message.setCc(um.getCc());
	            Map<String, Object> model = new HashMap<String, Object>();
	            model.put("user", um);
	            String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation,"UTF-8",model);
	            message.setText(text, true);
			}
		};
		return mp;
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public void setTemplateLocation(String templateLocation) {
		this.templateLocation = templateLocation;
	}

	public String getTemplateLocation() {
		return templateLocation;
	}

}
