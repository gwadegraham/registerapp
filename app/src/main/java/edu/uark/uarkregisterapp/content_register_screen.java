package edu.uark.uarkregisterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

//import org.apache.commons.codec.digest.DigestUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.uark.uarkregisterapp.models.transition.EmployeeTransition;

import static edu.uark.uarkregisterapp.R.id.userText;


public class content_register_screen extends AppCompatActivity {

    //All of this happens when the activity page initially loads
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_register_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    /* Modify later for inserting SongYun's Query
       - quinn
     */

    @Override
    protected void onStart() {
        super.onStart();
    }

    //when the client clicks the login button
    public void startLoginButtonClick (View view) throws NoSuchAlgorithmException {

        //retrieves the employee ID input from the user
        EditText text = (EditText)findViewById(userText);
        String user = text.getText().toString();

        //retrieves the password input from the user
        EditText text2 = (EditText)findViewById(R.id.passText);
        String pass = text2.getText().toString();

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

        //creating a parcelable object, so the employeeID and password can
        //be passed to the next activity
        EmployeeTransition obj= new EmployeeTransition(user,pass);

        //Creating a new intent for the Home Screen
        //(i.e. navigating to the home screen after the user clicks login)
        Intent i=new Intent(this,HomeActivity.class);

        //passing the parcelable object w/ the intent
        i.putExtra("userTag",obj);

        //passing a string w/ the intent, so the Home Screen knows which parcelable
        //display in the welcome statement
        i.putExtra("FROM_ACTIVITY", "login");

        //starts the next intent (i.e. launches the home screen activity)
        startActivity(i);
    }

}
