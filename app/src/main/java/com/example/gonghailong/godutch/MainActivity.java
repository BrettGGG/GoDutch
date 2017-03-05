package com.example.gonghailong.godutch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText totalAmount,persNbr;
    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalAmount = (EditText)findViewById(R.id.etBill);
        totalAmount.setFilters(new InputFilter[] {new LengthFilter()});
        persNbr = (EditText)findViewById(R.id.etNbr);
        display = (TextView) findViewById(R.id.tvAmount);
    }

    public void btnCompute(View v){
        if((totalAmount.length()== 0)|| (persNbr.length() == 0)){
            //为空
            Toast.makeText(getBaseContext(),"Please input the data!",Toast.LENGTH_LONG).show();
            return ;
        }
        else
        {
            Double total = Double.parseDouble(totalAmount.getText().toString());
            Double persons = Double.parseDouble(persNbr.getText().toString());
            if(persons == 0)
                Toast.makeText(getBaseContext(),"there are some errors with your input,please type again!",Toast.LENGTH_LONG).show();
            else
            {
                Double share = total / persons;
                share = Double.valueOf(Math.round(share * 100));
                share = share / 100;
                display.setText(String.valueOf(share));
                Toast.makeText(getBaseContext(), "the share to each person is:" + String.valueOf(share)+ " yuan", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class LengthFilter implements InputFilter {

        /** 输入框小数的位数, 如保留两位小数*/
        private static final int DECIMAL_COUNT = 2;

        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            // 删除等特殊字符，直接返回
            if ("".equals(source.toString())) {
                return null;
            }
            String destValue = dest.toString();
            String[] splitArray = destValue .split("\\.");
            if (splitArray.length > 1) {
                String result = splitArray[1];
                int d = result.length() + 1 - DECIMAL_COUNT;
                if (d > 0) {
                    return source.subSequence(start, end - d);
                }
            }
            return null;
        }
    }



}
