package com.example.tejas.spitaccess2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText SignUp_emailEditText,SignUp_passwordEditText;
    Button SignUp_signupButton;
    TextInputLayout SignUp_emaillayout,SignUp_passwordlayout;
    LinearLayout SignUp_linearlayout;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("SPIT Access");
        setContentView(R.layout.activity_sign_up);

        SignUp_linearlayout=(LinearLayout)findViewById(R.id.SignUp_linearLayout);

        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //mobile
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        //wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        if (mobile == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTED) {

        } else  {
            Snackbar.make(SignUp_linearlayout,"No Internet Connection",Snackbar.LENGTH_LONG).show();
        }


        SignUp_emailEditText=(EditText)findViewById(R.id.SignUp_emailEditText);
        SignUp_passwordEditText=(EditText)findViewById(R.id.SignUp_passwordEditText);

        SignUp_emaillayout=(TextInputLayout)findViewById(R.id.SignUp_emailTextLayout);
        SignUp_passwordlayout=(TextInputLayout)findViewById(R.id.SignUp_passwordTextLayout);

        SignUp_signupButton=(Button)findViewById(R.id.SignUp_signUpInButton);

        firebaseAuth = FirebaseAuth.getInstance();

        //if getCurrentUser does not returns null
        if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
        }
        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        SignUp_signupButton.setOnClickListener(this);

    }

    private void registerUser(){
        //getting email and password from edit texts
        SignUp_emaillayout.setError(null);
        SignUp_passwordlayout.setError(null);
        String email=SignUp_emailEditText.getText().toString().trim();
        String password=SignUp_passwordEditText.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            SignUp_emaillayout.setError("Please enter email");
            return;
        }

        if(TextUtils.isEmpty(password)){
            SignUp_passwordlayout.setError("Please enter password");
            return;
        }

        if (password.length()<=5)
        {
            SignUp_passwordlayout.setError("Password must be atleast 6 characters");
            return;
        }
        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
                        }else{
                            //display some message here
                            Toast.makeText(SignUpActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //mobile
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        //wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        if (mobile == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTED) {
            Snackbar.make(SignUp_linearlayout,"Invalid email address",Snackbar.LENGTH_LONG).show();
            SignUp_emailEditText.setText("");
            SignUp_passwordEditText.setText("");

        } else  {
            Snackbar.make(SignUp_linearlayout,"No Internet Connection",Snackbar.LENGTH_LONG).show();
        }
    }


    @Override
    public void onClick(View view) {
        if(view == SignUp_signupButton){
            registerUser();
        }
    }
}
