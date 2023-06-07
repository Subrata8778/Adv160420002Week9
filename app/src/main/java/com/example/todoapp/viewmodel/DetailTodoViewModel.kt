package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoDatabase
import com.example.todoapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    val todoLD = MutableLiveData<Todo>()

    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun fetch(uuid:Int){
        launch {
            val db = buildDb(getApplication())
            todoLD.postValue(db.todoDao().selectTodo(uuid))
        }
    }

    fun addTodo(todo:Todo){
        launch {
            val db = buildDb(getApplication())
            db.todoDao().insertAll(todo)
        }
    }
    fun update(title:String, notes:String, priority:Int, is_done:Int, uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().update(title, notes, priority, is_done, uuid)
        }
    }
    fun updateTodo(todo: Todo) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().updateTodo(todo)
        }
    }

}