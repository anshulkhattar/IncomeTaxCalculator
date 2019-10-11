package com.example.incometaxcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText basicSalary,hra,rent,specialAllowance,housingLoanInterest,exemption80C,otherDeduction;
    private Button submit,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basicSalary=findViewById(R.id.basicSalaryEditText);
        hra=findViewById(R.id.hraMonthlyEditText);
        rent=findViewById(R.id.rentMonthlyEditText);
        specialAllowance=findViewById(R.id.specialAllowanceEditText);
        housingLoanInterest=findViewById(R.id.housingLoanInterestEditText);
        exemption80C=findViewById(R.id.totalExemptionEditText);
        otherDeduction=findViewById(R.id.otherdeductionsEditText);

        submit=findViewById(R.id.submitButton);
        reset=findViewById(R.id.resetButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int basicSalaryValue,hraValue,rentValue,specialAllowanceValue,housingLoanInterestValue,exemption80CValue,otherdeductionValue;

                basicSalaryValue=Integer.parseInt(basicSalary.getText().toString());
                hraValue=Integer.parseInt(hra.getText().toString());
                rentValue=Integer.parseInt(rent.getText().toString());
                if(TextUtils.isEmpty(specialAllowance.getText().toString())){
                    specialAllowanceValue=0;
                }
                else specialAllowanceValue=Integer.parseInt(specialAllowance.getText().toString());
                if(TextUtils.isEmpty(housingLoanInterest.getText().toString())){
                    housingLoanInterestValue=0;
                }
                else housingLoanInterestValue=Integer.parseInt(housingLoanInterest.getText().toString());
                exemption80CValue=Integer.parseInt(exemption80C.getText().toString());
                if(exemption80CValue>150000) exemption80CValue=150000;
                otherdeductionValue=Integer.parseInt(otherDeduction.getText().toString());
                if(otherdeductionValue>25000) otherdeductionValue=25000;

                double hrayearly1=0,hrayearly2=0,hraearly3=0;
                hrayearly1=(basicSalaryValue*0.5);
                hrayearly2=rentValue-(0.1*basicSalaryValue);
                hraearly3=rentValue;

                double hraFinalValue=Math.min(hrayearly1,Math.min(hrayearly2,hraearly3));

                double totalIncome=basicSalaryValue+hraFinalValue+specialAllowanceValue;


                double taxableIncome=totalIncome-housingLoanInterestValue-exemption80CValue-otherdeductionValue-50000;

                double incomeTax=0.0;

                if(taxableIncome<250000) incomeTax=0;
                else if(taxableIncome>=250000 && taxableIncome<500000)  incomeTax=(.05*taxableIncome);
                else if(taxableIncome>=500000 && taxableIncome<1000000)  incomeTax=(.2*taxableIncome);
                else incomeTax=(.3*taxableIncome);

                Log.d("Income Tax= ",incomeTax+" ");


                Intent intent=new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra("totalIncome",totalIncome+"");
                intent.putExtra("taxableIncome",taxableIncome+"");
                intent.putExtra("incomeTax",incomeTax+"");
                startActivity(intent);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                basicSalary.setText("");
                hra.setText("");
                rent.setText("");
                specialAllowance.setText("");
                housingLoanInterest.setText("");
                exemption80C.setText("");
                otherDeduction.setText("");
            }
        });
    }
}
