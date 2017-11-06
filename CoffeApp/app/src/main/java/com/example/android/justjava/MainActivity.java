package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @param hasWhippedCream if user wants whipped cream, then add 1$ to price
     * @param hasChocolate    if user wants chocolate, then add 2$ to price
     * @return the price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int coffeePrice = 5;

        if (hasWhippedCream) {
            coffeePrice += 1;
        }

        if (hasChocolate) {
            coffeePrice += 2;
        }

        return this.quantity * coffeePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(number));
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
        String summaryMessage = getString(R.string.order_summary_name, name);
        summaryMessage += "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream);
        summaryMessage += "\n" + getString(R.string.order_summary_add_chocolate, addChocolate);
        summaryMessage += "\n" + getString(R.string.quantity, this.quantity);
        summaryMessage += "\n" + getString(R.string.total, NumberFormat.getCurrencyInstance().format(price));
        summaryMessage += "\n" + getString(R.string.thank_you);
        return summaryMessage;
    }

    /**
     * Create an intent in order to send an email with the command
     *
     * @param subject Email subject
     * @param body    Contains the summary order
     */
    public void composeEmail(String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        // get Whipped Cream checkbox status
        CheckBox whippedCreamCheckBOx = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBOx.isChecked();

        // Get Chocolate checkbox status
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        // Get the Name
        EditText nameField = findViewById(R.id.name_field);
        String customerName = nameField.getText().toString();

        String summaryOrder = createOrderSummary(price, hasWhippedCream, hasChocolate, customerName);
        String mailSubject = "Coffee order for " + customerName;
        composeEmail(mailSubject, summaryOrder);
    }

    /**
     * This method increment the quantity of coffee
     */
    public void increment(View view) {

        // You cannot have more than 100 coffees
        if (this.quantity == 100) {
            Toast.makeText(MainActivity.this, "You cannot have more than 100 coffees",
                    Toast.LENGTH_SHORT).show();
            return;

        }
        this.quantity = this.quantity + 1;
        displayQuantity(this.quantity);
    }

    /**
     * This method decrements the quantity of coffee
     */
    public void decrement(View view) {

        // You cannot have less than 1 coffee
        if (this.quantity == 1) {
            Toast.makeText(MainActivity.this, "You cannot have less than 1 coffee",
                    Toast.LENGTH_SHORT).show();
            return;

        }

        this.quantity = this.quantity - 1;
        displayQuantity(this.quantity);
    }


}
