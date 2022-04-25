package com.camo.ettask1

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MAViewModel: ViewModel() {

    private val _questions =  mutableListOf<Question>()
    val questions: List<Question> get() =  _questions
    private val _question = MutableStateFlow("")
    val question: StateFlow<String> get() = _question

    fun addQuestion(q: String){
        if(q.isBlank()) return
        _questions.add(Question(q, mutableListOf()))
        _question.value = ""
    }

    fun addOption(pos: Int, option: String){
        if(option.isBlank() || pos>= _questions.size) return
        _questions[pos].options.add(option)
    }

    fun removeOption(qPos: Int, oPos: Int){
        Log.d("tag","$qPos   $oPos")
        if(qPos >= _questions.size || oPos >= _questions[qPos].options.size) return
        _questions[qPos].options.removeAt(oPos)
    }

    fun removeQuestion(pos: Int) {
        _questions.removeAt(pos)
    }
}