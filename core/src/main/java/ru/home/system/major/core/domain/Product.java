package ru.home.system.major.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "value", columnDefinition = "text")
	private String value;

	@Column(name = "description", columnDefinition = "text")
	private String description;

	@Column(name = "price", columnDefinition = "double precision")
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "company_id", columnDefinition = "fk")
	private Company company;
}
