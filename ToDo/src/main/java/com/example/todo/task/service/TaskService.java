package com.example.todo.task.service;

import java.util.List;

import com.example.todo.task.model.Task;

public interface TaskService {

	List<Task> getAllTask();

	Task getTask(Integer id);

	Task addTask(Task task);

	void deleteTask(Integer id);

	Integer updateTask(Integer id);

}