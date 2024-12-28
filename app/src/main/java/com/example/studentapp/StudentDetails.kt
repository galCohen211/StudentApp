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

        val avatarImageView: ImageView = findViewById(R.id.details_student_avatar)
        val nameTextView: TextView = findViewById(R.id.details_student_name)
        val idTextView: TextView = findViewById(R.id.details_student_id)
        val phoneTextView: TextView = findViewById(R.id.details_student_phone)
        val addressTextView: TextView = findViewById(R.id.details_student_address)
        val studentCheckBox: CheckBox = findViewById(R.id.details_student_checkbox)

        // Retrieve data from intent
        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")
        val avatarUrl = intent.getStringExtra("avatarUrl")
        val isChecked = intent.getBooleanExtra("isChecked", false)
        val phone = intent.getStringExtra("phone")
        val address = intent.getStringExtra("address")

        // Bind data to views
        nameTextView.text = name
        idTextView.text = id
        phoneTextView.text = phone
        addressTextView.text = address
        studentCheckBox.isChecked = isChecked
        avatarImageView.setImageResource(R.drawable.student) // Use local image
    }
}