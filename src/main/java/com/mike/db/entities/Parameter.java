package com.mike.db.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "parameter")
public class Parameter
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true)
	private String value;

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

	@Override
	public String toString()
	{
		//@formatter:off
		return "Parameter{" + "id=" + id +
				", value='" + value + '\'' +
				", description='" + description + '\'' +
				", items=" + items + '}';
		//@formatter:on
	}
}
