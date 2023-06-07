package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentEditTodoBinding
import com.example.todoapp.model.Todo
import com.example.todoapp.viewmodel.DetailTodoViewModel

class EditTodoFragment : Fragment(), FragmentEditTodoLayoutInterface {
    private lateinit var viewModel: DetailTodoViewModel
    private  lateinit var dataBinding: FragmentEditTodoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.radiolistener = this
        dataBinding.savelistener = this

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        observeViewModel()

//        val txtJudulTodo = view.findViewById<TextView>(R.id.txtJudulTodo)
//        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
//
//        btnAdd.setOnClickListener {
//            var txtTitle = view.findViewById<EditText>(R.id.txtTitle)
//            var txtNotes = view.findViewById<EditText>(R.id.txtNotes)
//            var radioGroupPriority = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
//            var radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
//
//            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(),
//                radio.tag.toString().toInt(), uuid)
//            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//        }
    }
    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it

//            val txtTitle = view?.findViewById<TextView>(R.id.txtTitle)
//            val txtNotes = view?.findViewById<TextView>(R.id.txtNotes)
//            txtTitle?.setText(it.title)
//            txtNotes?.setText(it.notes)
//            val radioLow = view?.findViewById<RadioButton>(R.id.radioLow)
//            val radioMedium = view?.findViewById<RadioButton>(R.id.radioMedium)
//            val radioHigh = view?.findViewById<RadioButton>(R.id.radioHigh)
//
//            when(it.priority){
//                1 -> radioLow?.isChecked = true
//                2 -> radioMedium?.isChecked = true
//                3 -> radioHigh?.isChecked = true
//            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentEditTodoBinding>(inflater,
            R.layout.fragment_edit_todo, container, false)
        return dataBinding.root
    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onTodoSave(v: View, obj: Todo) {
        viewModel.updateTodo(obj)
        Toast.makeText(v.context, "Todo updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}