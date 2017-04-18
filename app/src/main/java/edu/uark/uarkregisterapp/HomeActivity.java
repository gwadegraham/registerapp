package edu.uark.uarkregisterapp;

import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

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

        //upon clicking the start transaction button, the client is sent to StartTransactionLandingActivity
        Intent i = new Intent(this, StartTransactionLandingActivity.class);
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

}
