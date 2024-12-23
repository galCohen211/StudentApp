package com.example.studentapp.models

data class Student(
    val name: String,
    val id: String,
    val avatarUrl: String,
    var isChecked: Boolean,
    val phone: String,
    val address: String
)