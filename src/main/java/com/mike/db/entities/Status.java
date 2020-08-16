package com.mike.db.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Entity
public class Status
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true)
	private String statusVal;

	public Status()
	{
	}

	public Status(String statusVal)
	{
		this.statusVal = statusVal;
	}

	@Override
	public String toString()
	{
		return "Status{" + "id=" + id + ", status='" + statusVal + '\'' + '}';
	}
}
