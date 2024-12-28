package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val addStudentButton: FloatingActionButton = findViewById(R.id.student_list_add_btn)

        // Set an OnClickListener to navigate to AddStudentActivity
        addStudentButton.setOnClickListener {
            // Create an Intent to start the AddStudentActivity
            val intent = Intent(this, NewStudent::class.java)
            startActivity(intent) // Start the Activity
        }
    }
}
