package com.example.incometaxcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView totalIncomeTextview,taxableIncomeTextview,incomeTaxTextview;
    private Button gobackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        totalIncomeTextview=findViewById(R.id.totalIncomeTextView);
        taxableIncomeTextview=findViewById(R.id.taxableIncomeTextView);
        incomeTaxTextview=findViewById(R.id.incomeTaxTextView);
        gobackButton=findViewById(R.id.backButton);

        Intent i=getIntent();
        totalIncomeTextview.setText(i.getStringExtra("totalIncome"));
        taxableIncomeTextview.setText(i.getStringExtra("taxableIncome"));
        incomeTaxTextview.setText(i.getStringExtra("incomeTax"));


        gobackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
