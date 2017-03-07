package edu.uark.uarkregisterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;

import static edu.uark.uarkregisterapp.R.id.fnameText;
import static edu.uark.uarkregisterapp.R.id.userText;

public class create_employee extends AppCompatActivity {

    //All of this happens when the activity initially launches
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //when the client clicks the "Save" button
    public void startSaveButtonClick (View view) {

        //retrieves the first name input from the user
        EditText text = (EditText)findViewById(fnameText);
        String firstName = text.getText().toString();

        //retrieves the last name input from the user
        EditText text2 = (EditText)findViewById(R.id.lnameText);
        String lastName = text2.getText().toString();

        //retrieves the password input from the user
        EditText text3 = (EditText)findViewById(R.id.passText);
        String pass = text3.getText().toString();

        //creating a parcelable object, so the first name, last name,  and password can
        //be passed to the next activity
        EmployeeTransition obj= new EmployeeTransition(firstName,lastName, pass);

        //Creating a new intent for the Home Screen
        //(i.e. navigating to the home screen after the user clicks save)
        Intent i=new Intent(this,HomeActivity.class);

        //passing the parcelable object w/ the intent
        i.putExtra("createEmployee",obj);

        //passing a string w/ the intent, so the Home Screen knows which parcelable
        //display in the welcome statement
        i.putExtra("FROM_ACTIVITY", "createEmployee");

        //starts the next intent (i.e. launches the home screen activity)
        startActivity(i);
    }

}
