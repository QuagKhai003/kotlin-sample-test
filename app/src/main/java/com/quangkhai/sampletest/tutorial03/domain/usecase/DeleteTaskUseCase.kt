package com.quangkhai.sampletest.tutorial03.domain.usecase

import com.quangkhai.sampletest.tutorial03.domain.repository.TaskRepository

class DeleteTaskUseCase(private val repo: TaskRepository) {
    operator fun invoke(taskId: String) = repo.deleteTask(taskId)
}