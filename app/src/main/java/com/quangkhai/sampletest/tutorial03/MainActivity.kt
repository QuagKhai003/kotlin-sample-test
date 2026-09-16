package com.quangkhai.sampletest.tutorial03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.quangkhai.sampletest.tutorial03.presentation.task.TaskNavGraph
import com.quangkhai.sampletest.tutorial03.presentation.task.TaskViewModel
import com.quangkhai.sampletest.tutorial03.ui.theme.Tutorial03Theme

class MainActivity : ComponentActivity() {

    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tutorial03Theme {
                val navController = rememberNavController()
                TaskNavGraph(navController = navController, viewModel = taskViewModel)
            }
        }
    }
}

