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
	private String key;

	@Getter
	@NotNull
	private String value;

	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
				CascadeType.PERSIST,
				CascadeType.MERGE
			},
			mappedBy = "item")
	private Set<Item> itemIds;
}
