package edu.cmu.footinguidemo.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import edu.cmu.footinguidemo.R;
import edu.cmu.footinguidemo.controller.JournalConnector;
import edu.cmu.footinguidemo.controller.UserConnector;

public class UI_NewJournalActivity extends AppCompatActivity {

    // Intent extra strings
    public static final String NEW_JOURNAL = "new_journal";  // True if this Activity is started as new journal (empty content)

    private String imagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_journal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent.getBooleanExtra(NEW_JOURNAL, true) == false) {
            // Activity is started from a list item, get journal data


        }
    }

    public void addRecording(View view) {
        Snackbar.make(view, "Recording is already added", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    /**
     * onClick event of the photo ImageView
     * @param view
     */
    public void addPhoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }

    /**
     * Return from picking a photo from gallery
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (data != null) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                cursor.close();

                // String picturePath contains the path of selected Image

                ImageView imageView = (ImageView) findViewById(R.id.journal_photo);
                imageView.setImageURI(selectedImage);
            }
        } catch (SecurityException e) {
            // Do nothing
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Content Not Saved")
                .setMessage("Do you really want to return without saving?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    public void saveJournal(View view) {
        String title = ((EditText) findViewById(R.id.journal_title)).getText().toString();
        String content = ((EditText) findViewById(R.id.journal_content)).getText().toString();

        if (title.isEmpty() || content.isEmpty()) {
            alert("Empty Contents", "Title and Content cannot be empty.");
        } else {
            // Save to database
            JournalConnector db = new JournalConnector(this);
            long journalId = db.insert(title, content, imagePath, "");
            db.close();

            // Save to global variable
            if (journalId != -1) {
                UI_MainActivity.GlobalClass.usr_numJournals++;
                UI_MainActivity.GlobalClass.usr_journalId_csv =
                        UI_MainActivity.GlobalClass.usr_journalId_csv.isEmpty() ? "" + journalId : UI_MainActivity.GlobalClass.usr_journalId_csv + "," + journalId;

                // Update user database
                UserConnector udb = new UserConnector(this);
                udb.updateJournal(UI_MainActivity.GlobalClass.usr_email, UI_MainActivity.GlobalClass.usr_journalId_csv);
                udb.close();

                finish();
            } else {
                alert("Error", "Unable to save the journal. Please contact customer support if this problem still exists.");
            }

        }
    }

    protected void alert(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
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
