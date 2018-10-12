package com.example.ramazan.mid

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface TodoDAO {

    @Query("SELECT * FROM todos")
    fun getAll() : List<Todo>

    @Insert
    fun insert(todo: Todo)

    @Query("UPDATE todos SET done = 1 WHERE id = :id")
    fun complete(id: Int)
}