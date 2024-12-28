package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class StudentDetails : AppCompatActivity() {

    private val editStudentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                if (data != null) {
                    setResult(RESULT_OK, data)
                    finish()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val avatarImage: ImageView = findViewById(R.id.details_student_avatar)
        val nameStudent: TextView = findViewById(R.id.details_student_name)
        val idStudent: TextView = findViewById(R.id.details_student_id)
        val phoneStudent: TextView = findViewById(R.id.details_student_phone)
        val addressStudent: TextView = findViewById(R.id.details_student_address)
        val checkBox: CheckBox = findViewById(R.id.details_student_checkbox)
        val editButton: Button = findViewById(R.id.student_details_edit_btn)


        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")
        val phone = intent.getStringExtra("phone")
        val address = intent.getStringExtra("address")
        val isChecked = intent.getBooleanExtra("isChecked", false)

        avatarImage.setImageResource(R.drawable.student)
        nameStudent.text = name
        idStudent.text = id
        phoneStudent.text = phone
        addressStudent.text = address
        checkBox.isChecked = isChecked

        editButton.setOnClickListener {
            val intent = Intent(this, EditStudent::class.java).apply {
                putExtra("name", name)
                putExtra("id", id)
                putExtra("phone", phone)
                putExtra("address", address)
                putExtra("isChecked", isChecked)
            }
            editStudentLauncher.launch(intent)
        }
    }
}