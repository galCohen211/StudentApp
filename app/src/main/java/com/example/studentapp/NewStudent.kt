package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewStudent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        val saveButton: Button = findViewById(R.id.add_student_save_button)
        val cancelButton: Button = findViewById(R.id.add_student_cancel_button)

        val name: EditText = findViewById(R.id.new_student_name)
        val id: EditText = findViewById(R.id.new_student_id)
        val phone: EditText = findViewById(R.id.new_student_phone)
        val address: EditText = findViewById(R.id.new_student_address)
        val checkbox: CheckBox = findViewById(R.id.new_student_checkbox)

        saveButton.setOnClickListener {
            val name = name.text.toString()
            val id = id.text.toString()
            val phone = phone.text.toString()
            val address = address.text.toString()
            val isChecked = checkbox.isChecked

            // Pass the data to StudentList
            val resultIntent = Intent().apply {
                putExtra("name", name)
                putExtra("id", id)
                putExtra("phone", phone)
                putExtra("address", address)
                putExtra("isChecked", isChecked)
            }

            setResult(RESULT_OK, resultIntent)
            finish()
        }

        cancelButton.setOnClickListener {
            // Close the activity
            finish()
        }
    }
}