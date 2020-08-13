package com.mike.db;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parameter")
public class Parameter
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@NotNull
	@Column(unique = true)
	private String value;

	@Getter
	@NotNull
	private String description;

	@ManyToMany(
			mappedBy = "parameters"
	)
	private Set<Item> items;

	public Parameter()
	{
	}

	public Parameter(String value, String description)
	{
		this.value = value;
		this.description = description;
	}
}
