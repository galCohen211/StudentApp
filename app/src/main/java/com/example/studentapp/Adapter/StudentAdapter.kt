package com.example.studentapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import com.example.studentapp.R
import com.example.studentapp.StudentDetails
import com.example.studentapp.models.Student

class StudentAdapter(private val context: Context, private val students: MutableList<Student>, private val launcher: ActivityResultLauncher<Intent>) : BaseAdapter() {

    override fun getCount(): Int = students.size

    override fun getItem(position: Int): Any = students[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val view = convertView ?: inflater.inflate(R.layout.student_list_row, parent, false)

        val student = students[position]

        val nameTextView: TextView = view.findViewById(R.id.student_row_name_text_view)
        val idTextView: TextView = view.findViewById(R.id.student_row_id_text_view)
        val avatarImageView: ImageView = view.findViewById(R.id.student_row_image_view)
        val studentCheckBox: CheckBox = view.findViewById(R.id.student_row_check_box)

        nameTextView.text = student.name
        idTextView.text = student.id
        avatarImageView.setImageResource(R.drawable.student) // Use local image
        studentCheckBox.isChecked = student.isChecked

        // Set click listener to open StudentDetails activity
        view.setOnClickListener {
            val intent = Intent(context, StudentDetails::class.java).apply {
                putExtra("name", student.name)
                putExtra("id", student.id)
                putExtra("avatarUrl", student.avatarUrl)
                putExtra("isChecked", student.isChecked)
                putExtra("phone", student.phone)
                putExtra("address", student.address)
            }
            launcher.launch(intent)
        }

        return view
    }
}
