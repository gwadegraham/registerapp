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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    public void startSaveButtonClick (View view) throws NoSuchAlgorithmException {

        //retrieves the first name input from the user
        EditText text = (EditText)findViewById(fnameText);
        String firstName = text.getText().toString();

        //retrieves the last name input from the user
        EditText text2 = (EditText)findViewById(R.id.lnameText);
        String lastName = text2.getText().toString();

        //retrieves the password input from the user
        EditText text3 = (EditText)findViewById(R.id.passText);
        String pass = text3.getText().toString();

        //implementing SHA-256 hash of the password -------------------------------------
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(pass.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Password in regular format : " + pass);
        System.out.println("Password in hex format : " + sb.toString());
        //finished hashing--------------------------------------------------------------

        //creating a parcelable object, so the first name, last name,  and password can
        //be passed to the next activity
        EmployeeTransition employee= new EmployeeTransition();
        employee.setPassword(pass);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);

        //Creating a new intent for the Home Screen
        //(i.e. navigating to the home screen after the user clicks save)
        Intent nextIntent = new Intent(this,HomeActivity.class);

        //passing the parcelable object w/ the intent
        nextIntent.putExtra("createEmployee",employee);

        //passing a string w/ the intent, so the Home Screen knows which parcelable
        //display in the welcome statement
        nextIntent.putExtra("FROM_ACTIVITY", "createEmployee");

        //starts the next intent (i.e. launches the home screen activity)
        startActivity(nextIntent);
    }

}
