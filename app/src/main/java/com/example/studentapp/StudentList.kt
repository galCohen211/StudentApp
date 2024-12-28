package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.studentapp.adapter.StudentAdapter
import com.example.studentapp.models.Student
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentList : AppCompatActivity() {

    private val students = mutableListOf<Student>()
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val listView: ListView = findViewById(R.id.students_list_view)
        val addStudentButton: FloatingActionButton = findViewById(R.id.student_list_add_btn)

        // Initialize the adapter
        adapter = StudentAdapter(this, students)
        listView.adapter = adapter

        // Add new student button listener
        addStudentButton.setOnClickListener {
            val intent = Intent(this, NewStudent::class.java)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val name = data?.getStringExtra("name") ?: ""
            val id = data?.getStringExtra("id") ?: ""
            val phone = data?.getStringExtra("phone") ?: ""
            val address = data?.getStringExtra("address") ?: ""
            val isChecked = data?.getBooleanExtra("isChecked", false) ?: false

            // Add new student to the list
            val newStudent = Student(name, id, "", isChecked, phone, address)
            students.add(newStudent)
            adapter.notifyDataSetChanged()
        }
    }
}
