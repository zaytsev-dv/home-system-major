package ru.home.system.major.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "telegram_question")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelegramQuestion
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "external_id", columnDefinition = "bigint")
	private Long externalId;

	@Column(name = "value", columnDefinition = "text")
	private String value;

	@Column(name = "type", columnDefinition = "text")
	private String type;

	@Column(name = "sub_type", columnDefinition = "text")
	private String subType;

	@Column(name = "created_at", columnDefinition = "timestamp with timezone")
	private LocalDateTime createdAt;

//	@Column(name = "is_last", columnDefinition = "bool")
//	private boolean isLast;

	@Column(name = "is_answered", columnDefinition = "bool")
	private boolean isAnswered;
}
