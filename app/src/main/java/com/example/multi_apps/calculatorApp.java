package com.example.multi_apps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class calculatorApp extends AppCompatActivity {
    double in1 = 0, i2 = 0;
    EditText edittext;
    boolean Add, Sub, Multiply, Divide, Remainder, deci;
    Button button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_Add, button_Sub,
            button_Mul, button_Div, button_Equ, button_Del, button_Dot, button_Remainder, mback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_app);
        button_0 = (Button) findViewById(R.id.b0);
        button_1 = (Button) findViewById(R.id.b1);
        button_2 = (Button) findViewById(R.id.b2);
        button_3 = (Button) findViewById(R.id.b3);
        button_4 = (Button) findViewById(R.id.b4);
        button_5 = (Button) findViewById(R.id.b5);
        button_6 = (Button) findViewById(R.id.b6);
        button_7 = (Button) findViewById(R.id.b7);
        button_8 = (Button) findViewById(R.id.b8);
        button_9 = (Button) findViewById(R.id.b9);
        button_Dot = (Button) findViewById(R.id.bDot);
        button_Add = (Button) findViewById(R.id.badd);
        button_Sub = (Button) findViewById(R.id.bsub);
        button_Mul = (Button) findViewById(R.id.bmul);
        button_Div = (Button) findViewById(R.id.biv);
        button_Remainder = (Button) findViewById(R.id.BRemain);
        button_Del = (Button) findViewById(R.id.buttonDel);
        button_Equ = (Button) findViewById(R.id.buttoneql);
        mback = (Button) findViewById(R.id.back);

        edittext = (EditText) findViewById(R.id.editText);

        button_1.setOnClickListener(v -> edittext.setText(edittext.getText()+"1"));

        button_2.setOnClickListener(v -> edittext.setText(edittext.getText() + "2"));

        button_3.setOnClickListener(v -> edittext.setText(edittext.getText() + "3"));

        button_4.setOnClickListener(v -> edittext.setText(edittext.getText() + "4"));

        button_5.setOnClickListener(v -> edittext.setText(edittext.getText() + "5"));

        button_6.setOnClickListener(v -> edittext.setText(edittext.getText() + "6"));

        button_7.setOnClickListener(v -> edittext.setText(edittext.getText() + "7"));

        button_8.setOnClickListener(v -> edittext.setText(edittext.getText() + "8"));

        button_9.setOnClickListener(v -> edittext.setText(edittext.getText() + "9"));

        button_0.setOnClickListener(v -> edittext.setText(edittext.getText() + "0"));

        button_Add.setOnClickListener(v -> {
            if (edittext.getText().length() != 0) {
                in1 = Float.parseFloat(edittext.getText() + "");
                Add = true;
                deci = false;
                edittext.setText(null);
            }
        });

        button_Sub.setOnClickListener(v -> {
            if (edittext.getText().length() != 0) {
                in1 = Float.parseFloat(edittext.getText() + "");
                Sub = true;
                deci = false;
                edittext.setText(null);
            }
        });

        button_Mul.setOnClickListener(v -> {
            if (edittext.getText().length() != 0) {
                in1 = Float.parseFloat(edittext.getText() + "");
                Multiply = true;
                deci = false;
                edittext.setText(null);
            }
        });

        button_Div.setOnClickListener(v -> {
            if (edittext.getText().length() != 0) {
                in1 = Float.parseFloat(edittext.getText() + "");
                Divide = true;
                deci = false;
                edittext.setText(null);
            }
        });

        button_Remainder.setOnClickListener(v -> {
            if (edittext.getText().length() != 0) {
                in1 = Float.parseFloat(edittext.getText() + "");
                Remainder = true;
                deci = false;
                edittext.setText(null);
            }
        });

        button_Equ.setOnClickListener(v -> {
            if (Add || Sub || Multiply || Divide || Remainder) {
                i2 = Float.parseFloat(edittext.getText() + "");
            }

            if (Add) {

                edittext.setText(in1 + i2 + "");
                Add = false;
            }

            if (Sub) {

                edittext.setText(in1 - i2 + "");
                Sub = false;
            }

            if (Multiply) {
                edittext.setText(in1 * i2 + "");
                Multiply = false;
            }

            if (Divide) {
                edittext.setText(in1 / i2 + "");
                Divide = false;
            }
            if (Remainder) {
                edittext.setText(in1 % i2 + "");
                Remainder = false;
            }
        });

        button_Del.setOnClickListener(v -> {
            edittext.setText("");
            in1 = 0.0;
            i2 = 0.0;
        });

        button_Dot.setOnClickListener(v -> {
            if (deci) {
                //do nothing or you can show the error
            } else {
                edittext.setText(edittext.getText() + ".");
                deci = true;
            }

        });

        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(calculatorApp.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
