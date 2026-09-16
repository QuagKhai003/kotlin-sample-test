package com.quangkhai.sampletest.restful.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quangkhai.sampletest.restful.presentation.ui.AddStudentScreen
import com.quangkhai.sampletest.restful.presentation.ui.EditStudentScreen
import com.quangkhai.sampletest.restful.presentation.ui.StudentListScreen
import com.quangkhai.sampletest.restful.presentation.viewmodel.StudentViewModel

@Composable
fun StudentApp(viewModel: StudentViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "studentList") {
        composable("studentList") {
            viewModel.getStudents()
            StudentListScreen(
                viewModel = viewModel,
                onAddStudentClick = { navController.navigate("addStudent") },
                onEditStudentClick = { id -> navController.navigate("editStudent/$id") }
            )
        }
        composable("addStudent") {
            AddStudentScreen(
                viewModel = viewModel,
                onStudentAdded = { navController.popBackStack() }
            )
        }
        composable("editStudent/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (id != null) {
                EditStudentScreen(
                    studentId = id,
                    viewModel = viewModel,
                    onStudentUpdated = { navController.popBackStack() }
                )
            }
        }
    }
}