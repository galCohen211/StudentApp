package com.example.studentapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.R
import com.example.studentapp.StudentDetails
import com.example.studentapp.models.Student

class StudentAdapter(
    private val context: Context,
    private val students: MutableList<Student>,
    private val launcher: ActivityResultLauncher<Intent>
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.student_list_row, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student)
    }

    override fun getItemCount(): Int = students.size

    inner class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.student_row_name_text_view)
        private val idTextView: TextView = view.findViewById(R.id.student_row_id_text_view)
        private val avatarImageView: ImageView = view.findViewById(R.id.student_row_image_view)
        private val studentCheckBox: CheckBox = view.findViewById(R.id.student_row_check_box)

        fun bind(student: Student) {
            nameTextView.text = student.name
            idTextView.text = student.id
            avatarImageView.setImageResource(R.drawable.student) // השתמש בתמונה מקומית
            studentCheckBox.isChecked = student.isChecked

            itemView.setOnClickListener {
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
        }
    }
}
