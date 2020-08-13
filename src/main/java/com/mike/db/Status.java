package com.mike.db;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.persistence.*;

@Entity
public class Status
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@NotNull
	@Column(unique = true)
	private String status;

	public Status()
	{
	}

	public Status(String status)
	{
		this.status = status;
	}
}
