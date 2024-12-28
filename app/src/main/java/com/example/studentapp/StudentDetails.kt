package com.example.studentapp

import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudentDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val avatarImage: ImageView = findViewById(R.id.details_student_avatar)
        val nameStudent: TextView = findViewById(R.id.details_student_name)
        val idStudent: TextView = findViewById(R.id.details_student_id)
        val phoneStudent: TextView = findViewById(R.id.details_student_phone)
        val addressStudent: TextView = findViewById(R.id.details_student_address)
        val checkBoxStudent: CheckBox = findViewById(R.id.details_student_checkbox)


        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")
        val avatarUrl = intent.getStringExtra("avatarUrl")
        val isChecked = intent.getBooleanExtra("isChecked", false)
        val phone = intent.getStringExtra("phone")
        val address = intent.getStringExtra("address")


        nameStudent.text = name
        idStudent.text = id
        phoneStudent.text = phone
        addressStudent.text = address
        checkBoxStudent.isChecked = isChecked
        avatarImage.setImageResource(R.drawable.student)
    }
}