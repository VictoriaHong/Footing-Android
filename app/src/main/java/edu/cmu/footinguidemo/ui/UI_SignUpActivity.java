package edu.cmu.footinguidemo.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.cmu.footinguidemo.R;
import edu.cmu.footinguidemo.controller.Validator;

public class UI_SignUpActivity extends AppCompatActivity {

    // UI (text inputs) references
    private EditText emailView;
    private EditText usernameView;
    private EditText passwordView;
    private EditText passwordConfirmView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the sign up form
        emailView = (EditText) findViewById(R.id.new_email);
        usernameView = (EditText) findViewById(R.id.new_username);
        passwordView = (EditText) findViewById(R.id.new_password);
        passwordConfirmView = (EditText) findViewById(R.id.password_confirm);

        Button signUpButton = (Button) findViewById(R.id.email_sign_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignUp();
            }
        });
    }

    /**
     * Attempts to sign up.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual sign up attempt is made.
     */
    private void attemptSignUp() {
        // Reset errors
        emailView.setError(null);
        usernameView.setError(null);
        passwordView.setError(null);
        passwordConfirmView.setError(null);

        // Store values at the time of the sign up attempt
        String email = emailView.getText().toString().trim();
        String username = usernameView.getText().toString().trim();
        String password = passwordView.getText().toString();
        String passwordConfirm = passwordConfirmView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password confirm
        if (TextUtils.isEmpty(passwordConfirm)) {
            passwordConfirmView.setError(getString(R.string.error_field_required));
            focusView = passwordConfirmView;
            cancel = true;
        } else if (!Validator.isPasswordConfirmValid(password, passwordConfirm)) {
            passwordConfirmView.setError(getString(R.string.error_invalid_password_confirm));
            focusView = passwordConfirmView;
            cancel = true;
        }

        // Check for a valid password
        if (TextUtils.isEmpty(password)) {
            passwordView.setError(getString(R.string.error_field_required));
            focusView = passwordView;
            cancel = true;
        } else if (!Validator.isPasswordValid(passwordConfirm)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }

        // Check for a valid username
        if (TextUtils.isEmpty(username)) {
            usernameView.setError(getString(R.string.error_field_required));
            focusView = usernameView;
            cancel = true;
        }

        // Check for a valid email address
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!Validator.isEmailValid(email)) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            cancel = true;
        }

        // TODO: Validate email and password from database

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // TODO: Write data into database

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Success");
            alertDialog.setMessage(getString(R.string.message_sign_up_success));
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });
            alertDialog.show();
        }
    }
}
