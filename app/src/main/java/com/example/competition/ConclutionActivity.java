package com.example.competition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.competition.adapter.Adapter;
import com.example.competition.databinding.ActivityConclutionBinding;

public class ConclutionActivity extends AppCompatActivity {

    ActivityConclutionBinding binding;
    AppCompatTextView txtName, txtFamily, txtConc;
    RecyclerView recyclerView;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConclutionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pref = getSharedPreferences("account" , MODE_PRIVATE);
        txtName = binding.name;
        txtFamily = binding.family;
        txtConc = binding.conclution;
        recyclerView = binding.recycle;
        recyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false));
        initContent();
    }

    private void initContent() {
        txtName.setText(pref.getString("user_name" , "کاربر مهمان"));
        txtFamily.setText(pref.getString("user_family" , "کاربر مهمان"));
        int[] a = {R.array.answers1,R.array.answers2,R.array.answers3, R.array.answers4};

        String[] serAnswer = {
                pref.getString("a1" , "null")
                ,pref.getString("a2" , "null")
                ,pref.getString("a3" , "null")
                ,pref.getString("a4" , "null")};

        Adapter adapter = new Adapter(this
                ,  getResources().getStringArray(R.array.quiz)
                , serAnswer
                , getResources().getStringArray(R.array.answer));

        recyclerView.setAdapter(adapter);

        int tru = 0;
        for (int i = 0 ; i < serAnswer.length ; i++) {
            Log.e("tag", "get length: " + serAnswer.length + " true questions count : " +(serAnswer[i].equals(getResources().getStringArray(R.array.answer)[i])));
            if (serAnswer[i].equals(getResources().getStringArray(R.array.answer)[i])){
            tru += 1;}
        }
        Log.e("tag" , "cont is : "+serAnswer.length+" questions true is : "+ tru + " false is : " + Math.abs(tru - serAnswer.length)+" "+((100*tru)-(Math.abs(tru - serAnswer.length))/(serAnswer.length*100)));
    }
}