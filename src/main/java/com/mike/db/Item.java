package com.mike.db;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@NotNull
	@Column(unique = true)
	private String category;

	@Getter
	@NotNull
	@Column(unique = true)
	private String model;

	@Getter @Setter
	private String comment;

	@Getter @Setter
	private Long serialNr;

	@ManyToMany
	@JoinTable(
			name = "item_parameters",
			joinColumns = @JoinColumn(name = "item_id"),
			inverseJoinColumns = @JoinColumn(name = "parameter_id")
	)
	private Set<Parameter> parameters;

	public Item()
	{
	}

	public Item(String category, String model)
	{
		this.category = category;
		this.model = model;
	}

}
