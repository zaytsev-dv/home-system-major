package ru.home.system.major.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "telegram_command_keys")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelegramCommandKeys
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "value", columnDefinition = "text")
	private String value;

	@Column(name = "name", columnDefinition = "text")
	private String name;

	@Column(name = "type", columnDefinition = "text")
	private String type;

	@Column(name = "msg", columnDefinition = "text")
	private String msg;
}
