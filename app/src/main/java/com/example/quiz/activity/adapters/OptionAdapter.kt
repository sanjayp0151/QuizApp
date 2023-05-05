package com.example.quiz.activity.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.activity.models.Question
import android.content.Context
import android.widget.Toast

class OptionAdapter(val context : Context,val question : Question) :
    RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {
    private  var option: List<String> = listOf(question.option1,question.option2,question.option3,question.option4)
    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var optionView = itemView.findViewById<TextView>(R.id.quiz_option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.option_item,parent,false)
        return OptionViewHolder(view)

    }

    override fun getItemCount(): Int {
        return option.size
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionView.text = option[position]
        holder.itemView.setOnClickListener{
            question.userAnswer = option[position]
            notifyDataSetChanged()
        }
        if (question.userAnswer == option[position]){
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        }else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }
    }
}