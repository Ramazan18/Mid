package com.example.ramazan.mid

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_todo_details.*
import kotlin.concurrent.thread

class TodoDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_details)

        val todo = intent.getParcelableExtra<Todo>("todo")

        todoTitle.text = todo.title
        todoDate.text = todo.date
        todoDescription.text = todo.description

        val myDB = TodoDatabase.getInstance(this)

        doneBtn.setOnClickListener {
            thread {
                myDB?.todoDao()?.complete(todo.id)
                runOnUiThread {
                    setResult(Activity.RESULT_OK, Intent()
                            .putExtra("todo", todo))
                    finish()
                }
            }

            setResult(Activity.RESULT_OK, Intent()
                    .putExtra("todo", todo))
            finish()

        }
    }
}
