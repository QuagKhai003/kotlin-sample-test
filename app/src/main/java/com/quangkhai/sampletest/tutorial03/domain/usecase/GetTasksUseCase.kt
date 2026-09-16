package com.quangkhai.sampletest.tutorial03.domain.usecase

import com.quangkhai.sampletest.tutorial03.domain.model.Task
import com.quangkhai.sampletest.tutorial03.domain.repository.TaskRepository

class GetTasksUseCase(private val repo: TaskRepository) {
    operator fun invoke(): List<Task> = repo.getTasks()
}