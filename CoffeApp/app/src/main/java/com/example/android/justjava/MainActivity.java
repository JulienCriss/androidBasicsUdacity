package com.example.android.justjava;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        int totalOfOrder = this.quantity * 5;
        String priceMessage = "Total: ";

        priceMessage = priceMessage + NumberFormat.getCurrencyInstance().format(totalOfOrder);
        priceMessage = priceMessage + "\nThank you!";
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    @SuppressLint("SetTextI18n")
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increment the quantity of coffee
     */
    public void increment(View view) {
        this.quantity = this.quantity + 1;
        display(this.quantity);

    }

    /**
     * This method decrements the quantity of coffee
     */
    public void decrement(View view) {
        this.quantity = this.quantity - 1;
        display(this.quantity);
    }

    /**
     * This method display a message
     */
    public void displayMessage(String message) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}
