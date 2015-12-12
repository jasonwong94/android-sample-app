package com.example.jasonwong94.productapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class ProductCreateActivity extends AppCompatActivity {
    final static String productCreated = "Product succesfully created succesfully!";
    EditText titleText ,descriptionText ,costText, quantityText;
    TextView totalCostText;
    Locale locale;
    Currency currency;
    String currencySymbol;
    ProductDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        dbHelper = new ProductDBHelper( ProductCreateActivity.this );

        //UI input fields
        titleText = (EditText) findViewById( R.id.productTitle );
        descriptionText = (EditText) findViewById( R.id.productDescription );
        costText = (EditText) findViewById( R.id.productCost );
        quantityText = (EditText) findViewById( R.id.productQuantity );
        totalCostText = (TextView) findViewById( R.id.productTotalCost );

        //events
        costText.addTextChangedListener( calculateWatcher );
        quantityText.addTextChangedListener( calculateWatcher );

        //locale/system configuration
        locale = Locale.getDefault();
        currency = Currency.getInstance(locale);
        currencySymbol = currency.getSymbol(locale);
        costText.setText( currencySymbol );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void calculateCost(){
        String costString = costText.getText().toString();
        String quantityString = quantityText.getText().toString();
        double cost;

        NumberFormat totalCostFormat = NumberFormat.getCurrencyInstance( locale );

        try{
            cost = totalCostFormat.parse( costString ).doubleValue();

            if( !costString.isEmpty() && !quantityString.isEmpty() ){
//            calculate cost only if both parameters have a numeric value (inputType: number )
                int quantity = Integer.valueOf( quantityString );
                double totalCost = cost * quantity;

                totalCostText.setText( String.valueOf( totalCostFormat.format( totalCost ) ) );
            }
        } catch ( java.text.ParseException e ){
            e.printStackTrace();
        }
    };

    private final TextWatcher calculateWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if( s.length() > 0 ){
                calculateCost();
            }
        }
    };

    public void reset( View view ){
//        clears out the form
        titleText.setText( "" );
        descriptionText.setText( "" );
        costText.setText( currencySymbol );
        quantityText.setText( "" );
        totalCostText.setText( "" );
    }

    public void createProduct( View view ){
        //write to database
        dbHelper.insertProduct(
                titleText.getText().toString(),
                descriptionText.getText().toString(),
                costText.getText().toString(),
                quantityText.getText().toString(),
                totalCostText.getText().toString(),
                DateFormat.getInstance().toString()
        );

        //success notification!
        Toast.makeText( ProductCreateActivity.this, productCreated, Toast.LENGTH_SHORT).show();
    }

}
