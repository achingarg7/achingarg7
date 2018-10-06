package com.example.todo.task.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.todo.task.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Modifying
	@Transactional
	@Query("update Task t set t.status = 'true' where t.id = ?1")
	public Integer updateTask(Integer id);

}
