package com.camo.ettask1

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camo.ettask1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MAViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: QuestionsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MAViewModel::class.java)
        binding.textView.setText(viewModel.question.value)
        setupListeners()
        adapter = QuestionsAdapter(viewModel.questions,::addOption,::removeOption,:: removeQuestion)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        binding.recyclerView.adapter = adapter
    }

    private fun removeQuestion(pos: Int){
        viewModel.removeQuestion(pos)
        adapter.setData(viewModel.questions)
    }
    private fun addOption(pos: Int, option: String){
        viewModel.addOption(pos, option)
        adapter.setData(viewModel.questions)
    }
    private fun removeOption(qPos: Int, oPos: Int){
        viewModel.removeOption(qPos, oPos)
        adapter.setData(viewModel.questions)
    }

    private fun setupListeners() {
        binding.button.setOnClickListener {
            viewModel.addQuestion(binding.textView.text.toString())
            adapter.setData(viewModel.questions)
            binding.textView.setText("")
        }
    }
}