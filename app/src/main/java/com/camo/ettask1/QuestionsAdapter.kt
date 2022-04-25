package com.camo.ettask1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camo.ettask1.databinding.OptionBinding
import com.camo.ettask1.databinding.QuestionBinding

class Question(val string: String, val options: MutableList<String>)

const val MAX_OPTIONS = 5

class QuestionsAdapter(
    private var questions: List<Question>,
    val addOption: (pos: Int, option: String) -> Unit,
    val removeOption: (qPos: Int, oPos: Int) -> Unit,
    val removeQuestion: (pos:Int) -> Unit
) : RecyclerView.Adapter<QuestionsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: QuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Question) {
            binding.tvQuestion.text = item.string
            binding.ibAddOption.setOnClickListener {
                addOption(adapterPosition, binding.textView2.text.toString())
                binding.textView2.setText("")
            }
            if (item.options.size == MAX_OPTIONS) {
                binding.ibAddOption.visibility = View.GONE
            } else {
                binding.ibAddOption.visibility = View.VISIBLE
            }
            var i = 0
            binding.llOptions.removeAllViews()
            while (i < item.options.size) {
                val bindingO = OptionBinding.inflate(LayoutInflater.from(binding.root.context))
                binding.llOptions.addView(bindingO.root,i)
                bindingO.textView3.text = item.options[i]
                bindingO.imageButton.setOnClickListener {
                    removeOption(adapterPosition, i-1)
                }
                i += 1
            }
            binding.imageButton2.setOnClickListener {
                removeQuestion(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.question, parent, false)
        val binding = QuestionBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = questions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(true)
        return holder.bind(questions[position])
    }

    fun setData(list: List<Question>) {
        questions = list
        notifyDataSetChanged()
    }
}