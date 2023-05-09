package com.example.competition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.competition.databinding.ActivityQuestionBinding;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityQuestionBinding binding;
    AppCompatTextView txtTitle, btnNext;
    RadioGroup radioGroup;
    RadioButton btn1, btn2, btn3, btn4, btnSelected;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pref = getSharedPreferences("account", MODE_PRIVATE);
        if (getSupportActionBar() != null && getIntent().getExtras() != null) {
            getSupportActionBar().setTitle("اسم شرکت کننده: " + pref.getString("user_name", "کاربر مهمان"));
            getSupportActionBar().setSubtitle("فامیلی شرکت کننده: " + pref.getString("user_family", "کاربر مهمان"));
        }
        txtTitle = binding.title;
        btnNext = binding.btnNext;
        btnNext.setOnClickListener(this);

        radioGroup = binding.radioGroup;

        btn1 = binding.answer1;
        btn2 = binding.answer2;
        btn3 = binding.answer3;
        btn4 = binding.answer4;

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                btnNext.setVisibility(View.VISIBLE);
                btnSelected = radioGroup.findViewById(i);
            }
        });

        Bundle b = getIntent().getExtras();
        question(b);
    }

    int quesNumber;

    private void question(Bundle b) {
        if (b != null) {
            quesNumber = b.getInt("ques_number");
            if (quesNumber == 4) {
                btnNext.setText("دیدن نتایج");
            }
        }
        int[] q = {R.string.quiz1, R.string.quiz2, R.string.quiz3, R.string.quiz4};
        String title = getResources().getString(q[quesNumber - 1]);
        txtTitle.setText(title);
        int[] answers = {R.array.answers1, R.array.answers2, R.array.answers3, R.array.answers4};
        List<Integer> answer = new ArrayList<>();
        answer.add(R.array.answers1);
        answer.add(R.array.answers2);
        answer.add(R.array.answers3);
        answer.add(R.array.answers4);
        String[] a = getResources().getStringArray(answer.get(quesNumber - 1));
        btn1.setText(a[0]);
        btn2.setText(a[1]);
        btn3.setText(a[2]);
        btn4.setText(a[3]);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                if (quesNumber != 4) {
                    Intent i = new Intent(this, QuestionActivity.class);
                    i.putExtra("ques_number", quesNumber + 1);
                    startActivity(i);
                } else {
                    Intent i = new Intent(this, ConclutionActivity.class);
                    startActivity(i);
                }
                String a = btnSelected.getText().toString();
                pref.edit().putString("a" + quesNumber, a).apply();
                finish();
                break;
            default:
                break;
        }
    }
}