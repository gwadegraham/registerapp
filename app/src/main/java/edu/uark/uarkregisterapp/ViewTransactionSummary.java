package edu.uark.uarkregisterapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;

import edu.uark.uarkregisterapp.models.transition.ProductTransition;
import edu.uark.uarkregisterapp.models.transition.TransactionTransition;

public class ViewTransactionSummary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transaction_summary);

        //getting the transactionTransition object from the previous intent
        this.transactionTransition = this.getIntent().getParcelableExtra(this.getString(R.string.intent_extra_transaction_transition));

        productLinkedList = transactionTransition.getProducts();

        //productCounter = productLinkedList.size();

        for (ProductTransition productTransition : transactionTransition.getProducts()) {
            productCounter++;
        }

        this.getTotalCountTextView().setText("The total number of products in your cart: " + productCounter);

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        //getting the transactionTransition object from the previous intent
//        this.transactionTransition = this.getIntent().getParcelableExtra(this.getString(R.string.intent_extra_transaction_transition));
//
//        productLinkedList = transactionTransition.getProducts();
//
//        productCounter = productLinkedList.size();
//
//        for (ProductTransition productTransition : transactionTransition.getProducts()) {
//            //productCounter = productCounter + 1;
//        }
//
//        this.getTotalCountTextView().setText("The total number of products in your cart: " + productCounter);    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        //getting the transactionTransition object from the previous intent
//        this.transactionTransition = this.getIntent().getParcelableExtra(this.getString(R.string.intent_extra_transaction_transition));
//
//        productLinkedList = transactionTransition.getProducts();
//
//        productCounter = productLinkedList.size();
//
//        for (ProductTransition productTransition : transactionTransition.getProducts()) {
//            //productCounter = productCounter + 1;
//        }
//
//        this.getTotalCountTextView().setText("The total number of products in your cart: " + productCounter);    }

    private TextView getTotalCountTextView() {
        return (TextView)this.findViewById(R.id.totalCount);
    }

    public void checkoutButtonClick(View view) {

        showAlertDialog();
    }

    public void showAlertDialog () {

        AlertDialog alertDialog = new AlertDialog.Builder(ViewTransactionSummary.this).create();
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
    private LinkedList productLinkedList;
    private int productCounter;
}
