package edu.uark.uarkregisterapp;

import edu.uark.uarkregisterapp.models.api.Employee;
import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.api.Transaction;
import edu.uark.uarkregisterapp.models.api.enums.TransactionApiRequestStatus;
import edu.uark.uarkregisterapp.models.api.enums.ProductApiRequestStatus;
import edu.uark.uarkregisterapp.models.api.services.EmployeeService;
import edu.uark.uarkregisterapp.models.api.services.ProductService;
import edu.uark.uarkregisterapp.models.api.services.TransactionService;
import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;
import edu.uark.uarkregisterapp.models.transition.TransactionTransition;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HomeActivity extends AppCompatActivity {

    //All of this happens when this activity page initially loads
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        this.employeeTransition = this.getIntent().getParcelableExtra(this.getString(R.string.intent_extra_employee));
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.getEmployeeWelcomeTextView().setText("Welcome " + this.employeeTransition.getFirstName() + " (" + this.employeeTransition.getEmployeeId() + ")!");
    }

    //when client clicks the "Start Transaction" button
    public void startTransactionButtonClick (View view) {

        this.savingTransactionAlert = new AlertDialog.Builder(this).
                setMessage(R.string.alert_dialog_transaction_save).
                create();

        transactionTransition = new  TransactionTransition();

        //upon clicking the start transaction button, the client is sent to StartTransactionLandingActivity
        Intent i = new Intent(this, StartTransactionLandingActivity.class);

        //putting TransactionTransition object as extra on intent
        i.putExtra(
                getString(R.string.intent_extra_transaction_transition),
                transactionTransition
        );

        //starting next activity with intent
        startActivity(i);

    }

    //when client clicks the "Create Employee" button
    public void createEmployeeButtonClick (View view) {

        //upon clicking the create employee button, the client is sent to create_employee
        Intent i = new Intent(this, create_employee.class);
        startActivity(i);
    }

    //when client clicks the "Sales Report: Product" button
    public void reportProductButtonClick (View view) {

        showAlertDialog();
    }

    //when client clicks the "Sales Report: Cashier" button
    public void reportCashierButtonClick (View view) {

        showAlertDialog();
    }

    //when client clicks the "Log Out" button
    public void logOutButtonClick (View view) {

        //upon clicking the log out button, the client is sent back to the login page
        Intent i = new Intent(this, content_register_screen.class);
        startActivity(i);
    }

    //function to launch an alert dialog box
    public void showAlertDialog () {

        AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
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

    private TextView getEmployeeWelcomeTextView() {
        return (TextView)this.findViewById(R.id.welcomeStatement);
    }

    private EmployeeTransition employeeTransition;

    private class SaveActivityTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            Transaction transaction = (new TransactionService()).putProduct( ( new Transaction() ) );

            if (transaction.getApiRequestStatus() == TransactionApiRequestStatus.OK) {
                transactionTransition.setTotalPrice(this.totalPrice);
                transactionTransition.setRecordID(this.recordID);
            }

            return (transaction.getApiRequestStatus() == TransactionApiRequestStatus.OK);
        }

        @Override
        protected void onPostExecute(Boolean successfulSave) {
            String message;

            savingTransactionAlert.dismiss();

            if (successfulSave) {
                message = getString(R.string.alert_dialog_transaction_save_success);
            } else {
                message = getString(R.string.alert_dialog_transaction_save_failure);
            }

            new AlertDialog.Builder(this.activity).
                    setMessage(message).
                    setPositiveButton(
                            R.string.button_dismiss,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            }
                    ).
                    create().
                    show();
        }

        private int totalPrice;
        private String recordID;
        private HomeActivity activity;

        private SaveActivityTask(HomeActivity activity, String recordID, int totalPrice) {
            this.totalPrice = totalPrice;
            this.activity = activity;
            this.recordID = recordID;
        }
    }

    private TransactionTransition transactionTransition;
    private AlertDialog savingTransactionAlert;

}
