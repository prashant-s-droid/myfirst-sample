package com.example.glimpsek.model

import android.view.View
import android.widget.Toast

data class User (
    val id: Int,
    val name: String,
    val email: String,
    val password:String
)

{
    fun onButtonClick(view : View){
        Toast.makeText(view.context, "hello ", Toast.LENGTH_SHORT).show()
    }
}

