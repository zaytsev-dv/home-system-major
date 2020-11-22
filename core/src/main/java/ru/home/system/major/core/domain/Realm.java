package ru.home.system.major.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "realm")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Realm
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "value", columnDefinition = "text")
	private String value;

	@Column(name = "description", columnDefinition = "text")
	private String description;
}
