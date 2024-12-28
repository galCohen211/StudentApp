package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.adapter.StudentAdapter
import com.example.studentapp.models.Student
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentList : AppCompatActivity() {

    private val students = mutableListOf<Student>()
    private lateinit var adapter: StudentAdapter

    private val newStudentResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                val name = data?.getStringExtra("name") ?: ""
                val id = data?.getStringExtra("id") ?: ""
                val phone = data?.getStringExtra("phone") ?: ""
                val address = data?.getStringExtra("address") ?: ""
                val isChecked = data?.getBooleanExtra("isChecked", false) ?: false

                // Add the new student to the list
                val newStudent = Student(name, id, "", isChecked, phone, address)
                students.add(newStudent)
                adapter.notifyDataSetChanged()

                Toast.makeText(this, "Student added: $name", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val listView: ListView = findViewById(R.id.students_list_view)
        val addButton: FloatingActionButton = findViewById(R.id.student_list_add_btn)

        // Initialize the adapter
        adapter = StudentAdapter(students)
        listView.adapter = adapter

        addButton.setOnClickListener {
            val intent = Intent(this, NewStudent::class.java)
            newStudentResultLauncher.launch(intent)
        }
    }
}
