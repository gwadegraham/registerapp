package edu.uark.uarkregisterapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.uark.uarkregisterapp.models.transition.TransactionTransition;

public class StartTransactionLandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_transaction_landing);

        //getting the transactionTransition object from the previous intent
        this.transactionTransition = this.getIntent().getParcelableExtra(this.getString(R.string.intent_extra_transaction_transition));
    }

    public void searchProductsButtonClick(View view) {

        //upon clicking the Search Products button, the client is sent to ProductsListingActivity
        Intent i = new Intent(this, ProductsListingActivity.class);

        //putting TransactionTransition object as extra on intent
        i.putExtra(
                getString(R.string.intent_extra_transaction_transition),
                transactionTransition
        );

        startActivity(i);
    }

    public void transactionSummaryButtonClick(View view) {

        //upon clicking the Transaction Summary button, the client is sent to ViewTransactionSummary
        Intent i = new Intent(this, ViewTransactionSummary.class);

        //putting TransactionTransition object as extra on intent
        i.putExtra(
                getString(R.string.intent_extra_transaction_transition),
                transactionTransition
        );

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

    private TransactionTransition transactionTransition;
}
