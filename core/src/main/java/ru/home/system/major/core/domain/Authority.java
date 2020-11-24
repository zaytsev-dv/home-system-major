package ru.home.system.major.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "authority")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authority
{
	@Id
	@Column(name = "name")
	private String name;
}
