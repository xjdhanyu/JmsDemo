package org.message.spring.mailSender;

import org.message.dto.UserMessage;
import org.message.util.MessageResponse;

public interface MailSenderService {
	
	 MessageResponse sendMessage(UserMessage um);

}
