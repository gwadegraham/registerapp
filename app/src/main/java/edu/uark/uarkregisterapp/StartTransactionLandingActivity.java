package edu.uark.uarkregisterapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartTransactionLandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_transaction_landing);
    }

    public void searchProductsButtonClick(View view) {

        //upon clicking the Search Products button, the client is sent to ProductsListingActivity
        Intent i = new Intent(this, ProductsListingActivity.class);
        startActivity(i);
    }

    public void transactionSummaryButtonClick(View view) {

        //upon clicking the Transaction Summary button, the client is sent to ViewTransactionSummary
        Intent i = new Intent(this, ViewTransactionSummary.class);
        startActivity(i);
    }

    public void backButtonClick (View view) {

        //upon clicking the back button, the client is sent to the previous activity screen
        //showAlertDialog();
        finish();

    }

    public void showAlertDialog () {

        AlertDialog alertDialog = new AlertDialog.Builder(StartTransactionLandingActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Functionality not yet implemented");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
