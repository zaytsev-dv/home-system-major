package ru.home.system.major.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notes
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "value", columnDefinition = "text")
	private String value;

	@Column(name = "description", columnDefinition = "text")
	private String description;

	@ManyToOne
	@JoinColumn(name = "telegram_user_id", columnDefinition = "fk")
	private TelegramUser telegramUser;
}
