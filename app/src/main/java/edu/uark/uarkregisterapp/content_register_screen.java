package edu.uark.uarkregisterapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;


public class content_register_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_register_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void startLoginButtonClick (View view) {

        EditText text = (EditText)findViewById(R.id.userText);
        String user = text.getText().toString();

        EditText text2 = (EditText)findViewById(R.id.passText);
        String pass = text2.getText().toString();

        EmployeeTransition obj= new EmployeeTransition(user,pass);
        Intent i=new Intent(this,HomeActivity.class);

        i.putExtra("userTag",obj);

        startActivity(i);
    }

}
