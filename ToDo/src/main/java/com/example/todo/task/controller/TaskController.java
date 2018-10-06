package com.example.todo.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.task.model.Task;
import com.example.todo.task.service.TaskService;

@RestController
@RequestMapping(path = "/task/v1")
public class TaskController {

	@Autowired
	TaskService taskService;

	@RequestMapping(path = "/tasks", method = RequestMethod.GET)
	public List<Task> getAllTasks() {
		return taskService.getAllTask();
	}

	@RequestMapping(path = "/task/{id}", method = RequestMethod.GET)
	public Task getTask(@PathVariable Integer id) {
		return taskService.getTask(id);
	}

	@RequestMapping(path = "/task", method = RequestMethod.POST)
	public Task addTask(@RequestBody Task task) {
		return taskService.addTask(task);
	}

	@RequestMapping(path = "/task/{id}", method = RequestMethod.DELETE)
	public Boolean deleteTask(@PathVariable Integer id) {
		taskService.deleteTask(id);
		return true;
	}

	@RequestMapping(path = "/task/{id}", method = RequestMethod.PUT)
	public Integer updateTask(@PathVariable Integer id) {
		return taskService.updateTask(id);
	}
}
