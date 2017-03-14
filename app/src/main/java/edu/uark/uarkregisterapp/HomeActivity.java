package edu.uark.uarkregisterapp;

import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    //All of this happens when this activity page initially loads
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Code that determines which activity the user came from previously,
        //(Login or CreateEmployee), and then takes the correct parcelable object
        //from that corresponding intent
        String previousActivity= getIntent().getStringExtra("FROM_ACTIVITY");

        //if the user came from the login page
        if (previousActivity.equals("login")){

            EmployeeTransition nameobj= getIntent().getParcelableExtra("userTag");
            TextView textView = (TextView) findViewById(R.id.welcomeStatement);
            String welcomeName = nameobj.getUserName();
            textView.setText("Welcome " + welcomeName + "! What would you like to do next?");
        }

        //if the user came from the create employee page
        else if (previousActivity.equals("createEmployee")){

            EmployeeTransition employeeobj= getIntent().getParcelableExtra("createEmployee");
            TextView textView = (TextView) findViewById(R.id.welcomeStatement);
            String welcomeName = employeeobj.getUserName();
            textView.setText("Welcome " + welcomeName + "! What would you like to do next?");
        }

    }

    //when client clicks the "Start Transaction" button
    public void startTransactionButtonClick (View view) {
        showAlertDialog();
    }

    //when client clicks the "Create Employee" button
    public void createEmployeeButtonClick (View view) {

        showAlertDialog();
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

}
