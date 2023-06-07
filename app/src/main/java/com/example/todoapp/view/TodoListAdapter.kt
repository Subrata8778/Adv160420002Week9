package com.example.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageButton
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.TodoItemLayoutBinding
import com.example.todoapp.model.Todo

class TodoListAdapter(val todos:ArrayList<Todo>, val todoOnClick : (Todo) -> Unit):RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(), TodoItemLayoutInterface {
    class TodoViewHolder(var view: TodoItemLayoutBinding)
        :RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = TodoItemLayoutBinding.inflate(inflater, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.view.todo = todos[position]
        holder.view.checklistener = this
        holder.view.editlistener = this


//        var checkTask = holder.view.findViewById<CheckBox>(R.id.checkTask)
//        checkTask.text = todos[position].title
//        checkTask.isChecked = false
//
//        checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
//            if(isChecked){
//                todoOnClick(todos[position])
//            }
//        }
//        val imgEdit = holder.view.findViewById<ImageView>(R.id.imgEdit)
//        imgEdit.setOnClickListener {
//            val action = TodoListFragmentDirections.actionEditTodo(todos[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }
    }
    fun updateTodoList(newTodos:List<Todo>){
        todos.clear()
        todos.addAll(newTodos)
        notifyDataSetChanged()
    }

    override fun onCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked){
            todoOnClick(obj)
        }
    }

    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionEditTodo(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}