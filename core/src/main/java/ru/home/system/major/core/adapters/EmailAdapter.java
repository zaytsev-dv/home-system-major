package ru.home.system.major.core.adapters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import ru.home.system.major.core.adapters.base.AbstractAdapter;
import ru.home.system.major.core.adapters.base.Adapter;
import ru.home.system.major.core.adapters.base.NotificationAdapter;

import javax.mail.internet.MimeMessage;


@Component
@Slf4j
public class EmailAdapter extends AbstractAdapter implements NotificationAdapter
{
	private final JavaMailSender emailSender;

	public EmailAdapter(JavaMailSender emailSender)
	{
		this.emailSender = emailSender;
	}

	@Override
	public void sendNotification(String subject, String message, String recipient)
	{
		try
		{
			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);
			helper.setText(message, false);
			helper.setTo(recipient);
			helper.setSubject(subject);
			helper.setFrom("Home_System");

			emailSender.send(mimeMessage);
		}
		catch (Exception ex)
		{
			log.error("Stacktrace:", ex);
			log.error(ex.getMessage());
			throw new RuntimeException(ex.getMessage());
		}
	}

	@Override
	public Adapter getNotificationType()
	{
		return Adapter.EMAIL;
	}
}
