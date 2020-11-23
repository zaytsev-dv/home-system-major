package ru.home.system.major.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "last_keyboard_select")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LastKeyboardSelect
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "value", columnDefinition = "text")
	private String value;

	@Column(name = "type", columnDefinition = "text")
	private String type;

	@Column(name = "external_id", columnDefinition = "bigint")
	private Long externalId;

	@Column(name = "created_at", columnDefinition = "timestamp with timezone")
	private LocalDateTime createdAt;
}
