package com.quangkhai.sampletest.tutorial03.domain.repository

import com.quangkhai.sampletest.tutorial03.domain.model.Task

interface TaskRepository {
    fun getTasks(): List<Task>
    fun addTask(task: Task)
    fun deleteTask(taskId: String)
    fun getTaskById(taskId: String): Task?
}