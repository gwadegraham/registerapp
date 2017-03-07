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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void startSaveButtonClick (View view) {

        EditText text = (EditText)findViewById(fnameText);
        String firstName = text.getText().toString();

        //EditText text2 = (EditText)findViewById(R.id.lnameText);
        //String lastName = text2.getText().toString();

        EditText text3 = (EditText)findViewById(R.id.passText);
        String pass = text3.getText().toString();

        EmployeeTransition obj= new EmployeeTransition(firstName, pass);
        Intent i=new Intent(this,HomeActivity.class);

        i.putExtra("createEmployee",obj);
        i.putExtra("FROM_ACTIVITY", "createEmployee");

        startActivity(i);
    }

}
