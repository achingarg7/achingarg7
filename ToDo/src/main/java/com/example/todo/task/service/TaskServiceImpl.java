package com.example.todo.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.task.model.Task;
import com.example.todo.task.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository repo;

	/* (non-Javadoc)
	 * @see com.example.todo.task.TaskService#getAllTask()
	 */
	@Override
	public List<Task> getAllTask() {
		return repo.findAll();
	}

	/* (non-Javadoc)
	 * @see com.example.todo.task.TaskService#getTask(java.lang.Integer)
	 */
	@Override
	public Task getTask(Integer id) {
		return repo.findById(id).get();
	}

	/* (non-Javadoc)
	 * @see com.example.todo.task.TaskService#addTask(com.example.todo.task.model.Task)
	 */
	@Override
	public Task addTask(Task task) {
		return repo.saveAndFlush(task);
	}

	/* (non-Javadoc)
	 * @see com.example.todo.task.TaskService#deleteTask(java.lang.Integer)
	 */
	@Override
	public void deleteTask(Integer id) {
		repo.deleteById(id);
	}

	/* (non-Javadoc)
	 * @see com.example.todo.task.TaskService#updateTask(java.lang.Integer)
	 */
	@Override
	public Integer updateTask(Integer id) {
		return repo.updateTask(id);
	}
}
