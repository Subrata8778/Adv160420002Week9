package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.viewmodel.DetailTodoViewModel

class EditTodoFragment : Fragment() {

    private lateinit var viewModel: DetailTodoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val txtJudulTodo = view.findViewById<TextView>(R.id.txtJudulTodo)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)

        txtJudulTodo.text = "Edit Todo"
        btnAdd.text = "Save"

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        btnAdd.setOnClickListener {
            var txtTitle = view.findViewById<EditText>(R.id.txtTitle)
            var txtNotes = view.findViewById<EditText>(R.id.txtNotes)
            var radioGroupPriority = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
            var radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)

            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(),
                radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }

        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            val txtTitle = view?.findViewById<TextView>(R.id.txtTitle)
            val txtNotes = view?.findViewById<TextView>(R.id.txtNotes)
            txtTitle?.setText(it.title)
            txtNotes?.setText(it.notes)
            val radioLow = view?.findViewById<RadioButton>(R.id.radioLow)
            val radioMedium = view?.findViewById<RadioButton>(R.id.radioMedium)
            val radioHigh = view?.findViewById<RadioButton>(R.id.radioHigh)

            when(it.priority){
                1 -> radioLow?.isChecked = true
                2 -> radioMedium?.isChecked = true
                3 -> radioHigh?.isChecked = true
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }
}