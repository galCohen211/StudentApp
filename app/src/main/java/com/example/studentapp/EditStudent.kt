package com.example.studentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton

class EditStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)
        val nameEditText: EditText = findViewById(R.id.edit_student_name)
        val idEditText: EditText = findViewById(R.id.edit_student_id)
        val phoneEditText: EditText = findViewById(R.id.edit_student_phone)
        val addressEditText: EditText = findViewById(R.id.edit_student_address)
        val checkBox: CheckBox = findViewById(R.id.edit_student_checkbox)
        val cancelButton: Button = findViewById(R.id.edit_student_btn_cancel)
        val saveButton: Button = findViewById(R.id.edit_student_btn_save)
        val deleteButton: Button = findViewById(R.id.edit_student_btn_delete)

        val backButton: ImageButton = findViewById(R.id.edit_student_back_button)
        backButton.setOnClickListener {
            val data = Intent()
            data.putExtra("result_key", "Some result data")
            setResult(Activity.RESULT_OK, data)
            finish()
        }

        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")
        val phone = intent.getStringExtra("phone")
        val address = intent.getStringExtra("address")
        val isChecked = intent.getBooleanExtra("isChecked", false)

        nameEditText.setText(name)
        idEditText.setText(id)
        phoneEditText.setText(phone)
        addressEditText.setText(address)
        checkBox.isChecked = isChecked

        cancelButton.setOnClickListener {
            finish() // Redirect to StudentList without changes
        }

        saveButton.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("name", nameEditText.text.toString())
                putExtra("id", idEditText.text.toString())
                putExtra("phone", phoneEditText.text.toString())
                putExtra("address", addressEditText.text.toString())
                putExtra("isChecked", checkBox.isChecked)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        deleteButton.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("delete", true)
                putExtra("id", id)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }

    }
}