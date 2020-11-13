package ru.home.system.major.core.adapters.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.system.major.core.domain.TelegramQuestion;
import ru.home.system.major.core.service.TelegramQuestionService;

import java.time.LocalDateTime;

@Component
@Slf4j
public class TelegramMessageHandler
{
	private final TelegramQuestionService telegramQuestionService;

	public TelegramMessageHandler(TelegramQuestionService telegramQuestionService)
	{
		this.telegramQuestionService = telegramQuestionService;
	}

	SendMessage handle(Long chatId, TelegramQuestion lastQuestion)
	{
		SendMessage response = new SendMessage();
		response.setChatId(String.valueOf(chatId));

		switch (lastQuestion.getSubType())
		{
			case "FIO":
			{
				response.setText("Супер! А теперь EMAIL");
				break;
			}

			case "EMAIL":
			{
				response.setText("На этом все:)");
				break;
			}
		}

		return response;
	}

	void isNeedToSave(Message message)
	{
		boolean needSave = false;
		String type = null;
		String subType = null;

		if (MessageToSave.registrationFIO.contains(message.getText()))
		{
			needSave = true;
			type = "REGISTRATION";
			subType = "FIO";
		}

		if (MessageToSave.registrationEMAIL.contains(message.getText()))
		{
			needSave = true;
			type = "REGISTRATION";
			subType = "EMAIL";
		}

		if (needSave)
		{
			TelegramQuestion question = TelegramQuestion.builder()
					.externalId(message.getChat().getId())
					.value(message.getText())
					.type(type)
					.subType(subType)
					.createdAt(LocalDateTime.now())
					.build();
			telegramQuestionService.save(question);
		}
	}
}
