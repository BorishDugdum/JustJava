package com.example.justjava;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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

        updateOrderSummary(view);
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

        updateOrderSummary(view);
    }

    public void updateOrderSummary(View view) {
        int amount = 0;
        TextView q = findViewById(R.id.quantity_text_view);
        try {
            amount = Integer.parseInt(q.getText().toString());
        }
        catch(Exception ex){
        }

        float base_price_per_amount = 5.0f;
        float price_per_whipped_cream = 1.0f;
        float price_per_chocolate = 2.0f;

        float price_per_amount = base_price_per_amount;

        //Toppings
        if(((CheckBox) findViewById(R.id.add_whipped_cream)).isChecked())
            price_per_amount += price_per_whipped_cream;
        if(((CheckBox) findViewById(R.id.add_chocolate)).isChecked())
            price_per_amount += price_per_chocolate;


        display(amount);
        displayPrice(price_per_amount * amount);
    }
    public void submitOrder(View view) {
        Log.v("MainActivity","Order Submitted!");

        //Name for order
        TextView name_text = findViewById(R.id.name_text);
        String name = "Name:\t" + name_text.getText().toString();

        //Number ordered
        TextView number_text = findViewById(R.id.quantity_text_view);
        String number = "Quantity:\t" + number_text.getText().toString();

        //Toppings
        CheckBox w_cream = findViewById(R.id.add_whipped_cream);
        String toppings = (w_cream.isChecked()) ? "w/ Whipped Cream\n" : "";
        CheckBox choco = findViewById(R.id.add_chocolate);
        toppings += ( (choco.isChecked()) ? "w/ Chocolate\n" : "" );

        //Amount spent
        TextView total_text = findViewById(R.id.order_summary_text_view);
        String total = "Total:\t" + total_text.getText().toString();

        //Submit text
        TextView q = findViewById(R.id.order_submitted_text_view);
        String returnResult =
                "Order Placed!\n\n" +
                name + "\n" +
                number + "\n" +
                toppings +
                total + "\n\n" +
                "Thank you!";
        //q.setText(returnResult);

        //Toast.makeText(this, returnResult, Toast.LENGTH_LONG).show();


        /*
        //Scroll to bottom
        ScrollView scrollLayout = findViewById(R.id.scroll_view);
        View lastChild = scrollLayout.getChildAt(scrollLayout.getChildCount() - 1);
        int bottom = lastChild.getBottom() + scrollLayout.getPaddingBottom();
        int sy = scrollLayout.getScrollY();
        int sh = scrollLayout.getHeight();
        int delta = bottom - (sy + sh);

        scrollLayout.smoothScrollBy(0, delta);
        */

        composeEmail(new String[] {}, "Order Summary", returnResult);
    }

    public void composeEmail(String[] addresses, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void display(int i) {
        TextView q = findViewById(R.id.quantity_text_view);
        q.setText(Integer.toString(i));
    }

    private void displayPrice(float i) {
        TextView q = findViewById(R.id.order_summary_text_view);
        q.setText(NumberFormat.getCurrencyInstance().format(i));
        Log.v("MainActivity","Price is " + NumberFormat.getCurrencyInstance().format(i));

        //CheckBox c = findViewById(R.id.add_whipped_cream);
        //Log.v("MainActivity","Add Whipped Cream: " + ((CheckBox) findViewById(R.id.add_whipped_cream)).isChecked());
    }
}
