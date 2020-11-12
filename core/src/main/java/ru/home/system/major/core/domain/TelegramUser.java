package ru.home.system.major.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "telegram_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelegramUser
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "external_id", columnDefinition = "bigint")
	private Long externalId;

	@Column(name = "firstname", columnDefinition = "text")
	private String firstname;

	@Column(name = "lastname", columnDefinition = "text")
	private String lastname;

	@Column(name = "username", columnDefinition = "text")
	private String username;
}
