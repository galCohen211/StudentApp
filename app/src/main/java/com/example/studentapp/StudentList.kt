package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.adapter.StudentAdapter
import com.example.studentapp.models.Student
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class StudentList : AppCompatActivity() {

    private val students: MutableList<Student> = mutableListOf()
    private lateinit var adapter: StudentAdapter

    private val newStudentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                handleAddStudent(result.data)
            }
        }

    private val editStudentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                handleEditOrDeleteStudent(result.data)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val recyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        val addButton: FloatingActionButton = findViewById(R.id.student_list_add_btn)

        // Initialize the adapter
        adapter = StudentAdapter(this, students, editStudentLauncher)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Add new student button listener
        addButton.setOnClickListener {
            val intent = Intent(this, NewStudent::class.java)
            newStudentLauncher.launch(intent)
        }
    }

    private fun handleAddStudent(data: Intent?) {
        val name = data?.getStringExtra("name") ?: return
        val id = data.getStringExtra("id") ?: return
        val phone = data.getStringExtra("phone") ?: ""
        val address = data.getStringExtra("address") ?: ""
        val isChecked = data.getBooleanExtra("isChecked", false)

        // Add the new student to the list
        val newStudent = Student(name, id, "", isChecked, phone, address)
        students.add(newStudent)
        adapter.notifyDataSetChanged()
    }

    private fun handleEditOrDeleteStudent(data: Intent?) {
        val delete = data?.getBooleanExtra("delete", false) ?: false
        val id = data?.getStringExtra("id") ?: return
        val oldId = data?.getStringExtra("oldId") ?: return

        if (delete) {
            // Remove the student from the list
            val studentToRemove = students.find { it.id == oldId }
            if (studentToRemove != null) {
                students.remove(studentToRemove)
                Log.d("StudentList", "Student removed: $oldId")

            }
        } else {
            // Update the student's details
            val name = data.getStringExtra("name") ?: ""
            val phone = data.getStringExtra("phone") ?: ""
            val address = data.getStringExtra("address") ?: ""
            val isChecked = data.getBooleanExtra("isChecked", false)

            val student = students.find { it.id == oldId }
            student?.apply {
                this.name = name
                this.phone = phone
                this.address = address
                this.isChecked = isChecked
                this.id = id
            }
        }

        adapter.notifyDataSetChanged()
    }
}
