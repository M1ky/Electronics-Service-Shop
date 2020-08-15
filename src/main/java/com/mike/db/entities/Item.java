package com.mike.db.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "item")
public class Item
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String category;

	@NotNull
	private String model;

	@NotNull
	private String comment;

	private Long serialNr;

	@ManyToMany
	@JoinTable(name = "item_parameters", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
	private Set<Parameter> parameters;

	public Item()
	{
	}

	@Override
	public String toString()
	{
		//@formatter:off
		return "Item: id: " + id +
				", category: " + category +
				", model: " + model +
				", comment: " + comment +
				", serialNr: " + serialNr;
		//@formatter:on
	}
}
