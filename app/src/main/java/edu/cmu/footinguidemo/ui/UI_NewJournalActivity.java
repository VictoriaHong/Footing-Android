package edu.cmu.footinguidemo.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.cmu.footinguidemo.R;

public class UI_NewJournalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_journal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void addRecording(View view) {
        Snackbar.make(view, "Recording is already added", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void addPhoto(View view) {
        Snackbar.make(view, "Photo is already added", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void saveJournal(View view) {
        String title = ((EditText) findViewById(R.id.journal_title)).getText().toString();
        String content = ((EditText) findViewById(R.id.journal_content)).getText().toString();

        if (title.isEmpty() || content.isEmpty()) {
            alert("Title and Content cannot be empty.");
        } else {
            finish();
        }
    }

    protected void alert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Empty Contents");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
