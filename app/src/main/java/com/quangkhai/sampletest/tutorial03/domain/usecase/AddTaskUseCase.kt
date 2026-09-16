package com.quangkhai.sampletest.tutorial03.domain.usecase

import com.quangkhai.sampletest.tutorial03.domain.model.Task
import com.quangkhai.sampletest.tutorial03.domain.repository.TaskRepository

class AddTaskUseCase(private val repo: TaskRepository) {
    operator fun invoke(task: Task) = repo.addTask(task)
}