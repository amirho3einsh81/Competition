package com.example.competition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.competition.databinding.ActivityHomeBinding;
import com.google.android.material.textfield.TextInputEditText;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityHomeBinding binding;
    TextInputEditText inName, inFamily;
    AppCompatTextView btnSubmit, btnRetry;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        pref = getSharedPreferences("account" , MODE_PRIVATE);
        if(getSupportActionBar()!= null){
            getSupportActionBar().setTitle("بازی مسابقه");
        }
        inFamily = binding.inFamily;
        inName = binding.inName;
        btnSubmit = binding.btnSubmit;
        btnRetry = binding.btnRetry;
        btnSubmit.setOnClickListener(this);
        btnRetry.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit:

                String name = inName.getText().toString() , family = inFamily.getText().toString();
                if ((name.isEmpty() || family.isEmpty())){
                    Toast.makeText(this, "لطفا هر دو فیلد ورودی را پر کنید", Toast.LENGTH_SHORT).show();
                } else if ((family.length()<3 || name.length() < 3)){
                    Toast.makeText(this, "لطفا نام و نام خانوادگی خود را به درستی وارد کنید", Toast.LENGTH_SHORT).show();
                }else {
                    pref.edit()
                            .putString("user_name", inName.getText().toString())
                            .putString("user_family" , inFamily.getText().toString())
                            .commit();
                    Intent i = new Intent(this , QuestionActivity.class);
                    i.putExtra("ques_number" , 1);
                    startActivity(i);
                    inName.setEnabled(false);
                    inFamily.setEnabled(false);
                    btnRetry.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_retry:
                inName.setEnabled(true);
                inFamily.setEnabled(true);
                btnRetry.setVisibility(View.GONE);
                break;

            default:break;

        }
    }
}