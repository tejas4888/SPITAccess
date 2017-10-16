package com.example.tejas.spitaccess2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText Login_emailEditText,Login_PasswordEditText,Login_forgotPasswordEditText;
    TextInputLayout Login_emaillayout,Login_passwordLayout,Login_forgotPasswordLayout;
    Button Login_loginButton,Login_forgotPasswordButton;
    TextView Login_signUpIntentTextView,Login_forgotPasswordTextView;
    LinearLayout Login_linearlayout;
    SignInButton Login_GoogleSignInButton;
    //firebase auth object
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    //progress dialog
    private ProgressDialog progressDialog;
    private ProgressDialog googleSigninProgressDialog;

    GoogleApiClient mGoogleApiClient;
    int RC_SIGN_IN=9001;
    String TAG="Log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("SPIT Access");
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        //if the objects getcurrentuser method is not null
        //means user is already logged in
        if(firebaseAuth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
        }

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                    Intent intent=new Intent(MainActivity.this, HomeScreenActivity.class);
                    startActivity(intent);

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        Login_linearlayout=(LinearLayout)findViewById(R.id.Login_linearLayout);

        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //mobile
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        //wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        if (mobile == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTED) {
            //Snackbar.make(Login_linearlayout,"No Internet Connection",Snackbar.LENGTH_LONG).show();

        } else  {
            Snackbar.make(Login_linearlayout,"No Internet Connection",Snackbar.LENGTH_LONG).show();
        }

        Login_emailEditText=(EditText)findViewById(R.id.Login_emailEditText);
        Login_PasswordEditText=(EditText)findViewById(R.id.Login_passwordEditText);
        Login_forgotPasswordEditText=(EditText)findViewById(R.id.Login_forgotPasswordEditText);

        Login_emaillayout=(TextInputLayout)findViewById(R.id.Login_emailTextLayout);
        Login_passwordLayout=(TextInputLayout)findViewById(R.id.Login_passwordTextLayout);
        Login_forgotPasswordLayout=(TextInputLayout)findViewById(R.id.Login_forgotPasswordTextLayout);

        Login_loginButton=(Button)findViewById(R.id.Login_loginInButton);
        Login_forgotPasswordButton=(Button)findViewById(R.id.Login_forgotPasswordButton);

        Login_GoogleSignInButton=(SignInButton)findViewById(R.id.Login_GoogleSignInButton);

        Login_signUpIntentTextView=(TextView)findViewById(R.id.Login_signUpTextView);
        Login_forgotPasswordTextView=(TextView)findViewById(R.id.Login_forgotPasswordTextView);

        progressDialog = new ProgressDialog(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                }  /*OnConnectionFailedListener*/ )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        Login_loginButton.setOnClickListener(this);
        Login_forgotPasswordButton.setOnClickListener(this);
        Login_signUpIntentTextView.setOnClickListener(this);
        Login_forgotPasswordTextView.setOnClickListener(this);
        Login_GoogleSignInButton.setOnClickListener(this);
    }

    private void userLogin(){
        Login_emaillayout.setError(null);
        Login_passwordLayout.setError(null);
        String email = Login_emailEditText.getText().toString().trim();
        String password  = Login_PasswordEditText.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Login_emaillayout.setError("Please enter email");
            return;
        }

        if(TextUtils.isEmpty(password)){
            Login_passwordLayout.setError("Please enter password");
            return;
        }

        if (password.length()<=5)
        {
            Login_passwordLayout.setError("Password must be atleast 6 characters");
            return;
        }
        //if the email and password are not empty
        //displaying a progress dialog
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
                        }
                    }
                });

        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        //mobile
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        //wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        if (mobile == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTED) {
            Snackbar.make(Login_linearlayout,"Invalid Credentials",Snackbar.LENGTH_LONG).show();
            Login_emailEditText.setText("");
            Login_PasswordEditText.setText("");

        } else  {
            Snackbar.make(Login_linearlayout,"No Internet Connection",Snackbar.LENGTH_LONG).show();
        }
    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        googleSigninProgressDialog=new ProgressDialog(MainActivity.this);
        googleSigninProgressDialog.setMessage("Logging in...");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);

            } else {
                // Google Sign In failed, update UI appropriately
                // ...
                if (googleSigninProgressDialog.isShowing())
                {
                    googleSigninProgressDialog.dismiss();
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public void onClick(View view) {
        if(view == Login_loginButton){
            userLogin();
        }

        if(view == Login_signUpIntentTextView){
            finish();
            startActivity(new Intent(this, SignUpActivity.class));
        }

        if (view==Login_forgotPasswordTextView){
            Log.v("HEY","The control goes inside the forgotPasswordText is inside");
            Login_forgotPasswordLayout.setVisibility(View.VISIBLE);
            Login_forgotPasswordEditText.setVisibility(View.VISIBLE);
            Login_forgotPasswordButton.setVisibility(View.VISIBLE);
        }

        if (view == Login_forgotPasswordButton) {
            Login_forgotPasswordLayout.setError(null);
            String forgotpasswordemail=Login_forgotPasswordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(forgotpasswordemail))
            {
                Login_forgotPasswordLayout.setError("Enter email address for verification");
                return;
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(forgotpasswordemail)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.v("TERE","hello man");
                                Toast.makeText(MainActivity.this,"Email verification sent",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

        if (view == Login_GoogleSignInButton)
        {
            Log.v("HEllo","Clicked");
            signIn();
        }

    }
}
