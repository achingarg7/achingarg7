package com.example.todo.task.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "task_name")
	private String taskName;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private Boolean status;

	public Task(Integer id, String taskName, String description, Boolean status) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.description = description;
		this.status = status;
	}

	public Task() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", description=" + description + ", status=" + status
				+ "]";
	}

}
