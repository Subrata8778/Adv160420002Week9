package com.example.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.model.Todo

class TodoListAdapter(val todos:ArrayList<Todo>, val todoOnClick : (Todo) -> Unit):RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var checkTask = holder.view.findViewById<CheckBox>(R.id.checkTask)
        checkTask.text = todos[position].title
        checkTask.isChecked = false

        checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked){
                todoOnClick(todos[position])
            }
        }
        val imgEdit = holder.view.findViewById<ImageView>(R.id.imgEdit)
        imgEdit.setOnClickListener {
            val action = TodoListFragmentDirections.actionEditTodo(todos[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

    }
    fun updateTodoList(newTodos:List<Todo>){
        todos.clear()
        todos.addAll(newTodos)
        notifyDataSetChanged()
    }
}