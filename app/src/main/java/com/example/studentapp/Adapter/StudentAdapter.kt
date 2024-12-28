package com.example.studentapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.BaseAdapter
import com.example.studentapp.R
import com.example.studentapp.models.Student

class StudentAdapter(private val students: MutableList<Student>?) : BaseAdapter() {

    override fun getCount(): Int = students?.size ?: 0

    override fun getItem(position: Int): Any = students?.get(position) ?: Student("", "", "", false, "", "")

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(parent?.context)

        // Reuse or inflate the view
        val view = convertView ?: inflater.inflate(R.layout.student_list_row, parent, false).apply {
            findViewById<CheckBox>(R.id.student_row_check_box).apply {
                setOnClickListener {
                    (tag as? Int)?.let { tag ->
                        val student = students?.get(tag)
                        student?.isChecked = (it as? CheckBox)?.isChecked ?: false
                    }
                }
            }
        }

        // Get the current student
        val student = students?.get(position)

        // Bind the data
        val nameTextView: TextView? = view?.findViewById(R.id.student_row_name_text_view)
        val idTextView: TextView? = view?.findViewById(R.id.student_row_id_text_view)
        val studentCheckBox: CheckBox? = view?.findViewById(R.id.student_row_check_box)
        val avatarImageView: ImageView? = view?.findViewById(R.id.student_row_image_view)

        nameTextView?.text = student?.name
        idTextView?.text = student?.id

        studentCheckBox?.apply {
            isChecked = student?.isChecked ?: false
            tag = position
        }

        avatarImageView?.setImageResource(R.drawable.student)

        return view!!
    }
}
