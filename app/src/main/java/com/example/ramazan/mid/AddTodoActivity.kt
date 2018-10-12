package com.example.ramazan.mid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_todo.*

class AddTodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo)

        val sharedPref = this.getSharedPreferences("data", Context.MODE_PRIVATE)
        val user = sharedPref?.getString("login", "0")

        okBtn.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = desccriptinEditText.text.toString()

            val todo = Todo(0, title, "11-10-2018", description, user!!, false)

            setResult(Activity.RESULT_OK, Intent()
                    .putExtra("todo", todo))
            finish()
        }
    }
}
