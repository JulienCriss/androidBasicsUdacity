package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice() {
        return this.quantity * 5;
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
    }

    /**
     * This method displayQuantity a message
     */
    public void displayMessage(String message) {
        TextView orderSummaryView = findViewById(R.id.order_summary_text_view);
        orderSummaryView.setText(message);
    }

    /**
     * Display the summary of the order
     *
     * @param price           of command
     * @param addWhippedCream Bool if user wants whipped cream
     * @param addChocolate    Bool is user wants chocolate
     * @param name            Name of customer
     * @return The summary of the order
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String name) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        int price = calculatePrice();
        // get Whipped Cream checkbox status
        CheckBox whippedCreamCheckBOx = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBOx.isChecked();

        // Get Chocolate checkbox status
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Get the Name
        EditText nameField = findViewById(R.id.name_field);
        String customerName = nameField.getText().toString();

        String summaryOrder = createOrderSummary(price, hasWhippedCream, hasChocolate, customerName);
        displayMessage(summaryOrder);
    }


    /**
     * This method increment the quantity of coffee
     */
    public void increment(View view) {
        this.quantity = this.quantity + 1;
        displayQuantity(this.quantity);

    }

    /**
     * This method decrements the quantity of coffee
     */
    public void decrement(View view) {
        this.quantity = this.quantity - 1;
        displayQuantity(this.quantity);
    }


}
