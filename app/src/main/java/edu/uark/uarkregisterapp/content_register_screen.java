package edu.uark.uarkregisterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;

import static edu.uark.uarkregisterapp.R.id.userText;


public class content_register_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_register_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void startLoginButtonClick (View view) {

        EditText text = (EditText)findViewById(userText);
        String user = text.getText().toString();

        EditText text2 = (EditText)findViewById(R.id.passText);
        String pass = text2.getText().toString();

        EmployeeTransition obj= new EmployeeTransition(user,pass);
        Intent i=new Intent(this,HomeActivity.class);

        i.putExtra("userTag",obj);
        i.putExtra("FROM_ACTIVITY", "login");

        startActivity(i);
    }

}
