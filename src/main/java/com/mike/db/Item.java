package com.mike.db;

import com.sun.istack.NotNull;
import lombok.Getter;

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

	@Getter
	private String comment;

	@Getter
	private Long serialNr;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(name="connector_item_parameter",
			joinColumns = { @JoinColumn(name="itemId") },
			inverseJoinColumns = { @JoinColumn(name="parameterId") })
	private Set<Parameter> itemIds;
}
