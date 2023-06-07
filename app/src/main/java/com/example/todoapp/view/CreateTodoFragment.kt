package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.model.Todo
import com.example.todoapp.viewmodel.DetailTodoViewModel

class CreateTodoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DetailTodoViewModel::class.java]

        var btnAdd = view.findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            var txtTitle = view.findViewById<EditText>(R.id.txtTitle)
            var txtNotes = view.findViewById<EditText>(R.id.txtNotes)
            var radioGroupPriority = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
            var radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)

            val todo = Todo(txtTitle.text.toString(),txtNotes.text.toString(), radio.tag.toString().toInt(), 0)

            viewModel.addTodo(todo)
            Toast.makeText(view.context, "Todo added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}