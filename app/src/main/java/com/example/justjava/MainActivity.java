package com.example.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void decCount(View view) {
        TextView q = findViewById(R.id.quantity_text_view);
        int i = 0;
        try {
            i = Integer.parseInt(q.getText().toString());
            if(i > 0)
                i--;
        }
        catch(Exception ex){

        }
        q.setText(Integer.toString(i));
    }
    public void incCount(View view) {
        TextView q = findViewById(R.id.quantity_text_view);
        int i = 0;
        try {
            i = Integer.parseInt(q.getText().toString());
            if(i < 9)
                i++;
        }
        catch(Exception ex){

        }
        if(i != 0)
            q.setText(Integer.toString(i));
    }

    public void submitOrder(View view) {
        int amount = 0;
        TextView q = findViewById(R.id.quantity_text_view);
        try {
            amount = Integer.parseInt(q.getText().toString());
        }
        catch(Exception ex){
        }

        float price_per_amount = 5;
        display(amount);
        displayPrice(price_per_amount * amount);
    }

    private void display(int i) {
        TextView q = findViewById(R.id.quantity_text_view);
        q.setText(Integer.toString(i));
    }

    private void displayPrice(float i) {
        TextView q = findViewById(R.id.price_text_view);
        q.setText(NumberFormat.getCurrencyInstance().format(i));
    }
}
