package com.example.competition.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.competition.R;
import com.example.competition.databinding.LayerAdapterBinding;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context ctx;
    String[] titles;
    String[] answers;
    String[] answer;

    int tru = 0, fals = 0;
    public Adapter(Context ctx, String[] titles, String[] answers, String[] answer) {
        this.ctx = ctx;
        this.titles = titles;
        this.answers = answers;
        this.answer = answer;
    }

    public int getTru() {
        return tru;
    }

    public int getFals() {
        return fals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.layer_adapter , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fill(titles[position], answers[position] ,answer[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView txtTitle, trueAnswer, userAnswer;
        SharedPreferences pref;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title);
            trueAnswer = itemView.findViewById(R.id.true_answer);
            userAnswer = itemView.findViewById(R.id.user_answer);
        }

        private void fill(String title , String userA , String answer){
            txtTitle.setText(title);
            trueAnswer.setText(answer);
            userAnswer.setText(" جواب شما : "+userA);
            if (userA.equals(answer)){
                trueAnswer.setTextColor(ContextCompat.getColor(ctx,R.color.teal_200));
                userAnswer.setTextColor(ContextCompat.getColor(ctx,R.color.teal_200));
                tru += 1;
            }else {
                trueAnswer.setTextColor(0xffff0000);
                userAnswer.setTextColor(0xffff0000);
                fals += 1;
            }
        }
    }
}
