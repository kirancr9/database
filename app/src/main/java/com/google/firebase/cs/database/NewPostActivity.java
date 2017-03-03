package com.google.firebase.cs.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.cs.database.models.Equipment;
import com.google.firebase.cs.database.models.User;

import java.util.HashMap;
import java.util.Map;


public class NewPostActivity extends BaseActivity {

    private static final String TAG = "NewPostActivity";
    private static final String REQUIRED = "Required";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]
    private EditText mTitleField;
    private EditText mBodyField;
    private EditText mlocationTxt;
    private EditText mSerialTxt;
    private EditText tTitlefield1;
    private EditText tMakefield1;
    private EditText tModelfield1;
    private EditText tSerialfield1;
    private EditText tTitlefield2;
    private EditText tMakefield2;
    private EditText tModelfield2;
    private EditText tSerialfield2;
    private EditText tTitlefield3;
    private EditText tMakefield3;
    private EditText tModelfield3;
    private EditText tSerialfield3;


    private FloatingActionButton mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]

        tTitlefield1 = (EditText) findViewById(R.id.tTitle1_field);
        tMakefield1 = (EditText) findViewById(R.id.tMake1_field);
        tModelfield1 = (EditText) findViewById(R.id.tModel1_field);
        tSerialfield1 = (EditText) findViewById(R.id.tSerial1_field);

        tTitlefield2 = (EditText) findViewById(R.id.tTitle2_field);
        tMakefield2 = (EditText) findViewById(R.id.tMake2_field);
        tModelfield2 = (EditText) findViewById(R.id.tModel2_field);
        tSerialfield2 = (EditText) findViewById(R.id.tSerial2_field);

        tTitlefield3 = (EditText) findViewById(R.id.tTitle3_field);
        tMakefield3 = (EditText) findViewById(R.id.tMake3_field);
        tModelfield3 = (EditText) findViewById(R.id.tModel3_field);
        tSerialfield3 = (EditText) findViewById(R.id.tSerial3_field);


        mTitleField = (EditText) findViewById(R.id.field_title);
        mBodyField = (EditText) findViewById(R.id.field_body);
        mlocationTxt = (EditText) findViewById(R.id.location_txt);
        mSerialTxt = (EditText) findViewById(R.id.serial_txt);
        mSubmitButton = (FloatingActionButton) findViewById(R.id.fab_submit_post);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPost();
            }
        });

       // mSubmitB.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        submitPost();
        //    }
      //  });
    }

    private void submitPost() {

        final String ttitle1 = tTitlefield1.getText().toString();
        final String tmake1 = tMakefield1.getText().toString();
        final String tmodel1 = tModelfield1.getText().toString();
        final String tserial1 = tSerialfield1.getText().toString();

        final String ttitle2 = tTitlefield2.getText().toString();
        final String tmake2 = tMakefield2.getText().toString();
        final String tmodel2 = tModelfield2.getText().toString();
        final String tserial2 = tSerialfield2.getText().toString();

        final String ttitle3 = tTitlefield3.getText().toString();
        final String tmake3 = tMakefield3.getText().toString();
        final String tmodel3 = tModelfield3.getText().toString();
        final String tserial3 = tSerialfield3.getText().toString();

        final String title = mTitleField.getText().toString();
        final String body = mBodyField.getText().toString();
        final String location = mlocationTxt.getText().toString();
        final String serial = mSerialTxt.getText().toString();
        // Title is required
        if (TextUtils.isEmpty(title)) {
            mTitleField.setError(REQUIRED);
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(body)) {
            tMakefield1.setError(REQUIRED);
            return;
        }

        if (TextUtils.isEmpty(title)) {
            tModelfield1.setError(REQUIRED);
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(body)) {
            tSerialfield1.setError(REQUIRED);
            return;
        }

        if (TextUtils.isEmpty(title)) {
            mTitleField.setError(REQUIRED);
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(body)) {
            mBodyField.setError(REQUIRED);
            return;
        }

        if (TextUtils.isEmpty(title)) {
            mSerialTxt.setError(REQUIRED);
            return;
        }

        // Body is required
        if (TextUtils.isEmpty(body)) {
            mlocationTxt.setError(REQUIRED);
            return;
        }



        // Disable button so there are no multi-posts
        setEditingEnabled(false);
        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        // [START single_value_read]
        final String userId = getUid();
        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        User user = dataSnapshot.getValue(User.class);

                        // [START_EXCLUDE]
                        if (user == null) {
                            // User is null, error out
                            Log.e(TAG, "User " + userId + " is unexpectedly null");
                            Toast.makeText(NewPostActivity.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Write new post
                            writeNewPost(userId, user.username, title, body, location, serial,ttitle1, tmake1, tmodel1, tserial1);

                        }

                        // Finish this Activity, back to the stream
                        setEditingEnabled(true);
                        finish();
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        setEditingEnabled(true);
                        // [END_EXCLUDE]
                    }
                });
        // [END single_value_read]
    }

    public void nextView(View v)
    {
        RelativeLayout one = (RelativeLayout) findViewById(R.id.rLayout);
        one.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed()
    {
        //do whatever you want the 'Back' button to do
        //as an example the 'Back' button is set to start a new Activity named 'NewActivity'
        this.startActivity(new Intent(NewPostActivity.this,DrawerActivity.class));
        finish();
        return;
    }

    private void setEditingEnabled(boolean enabled) {
        mTitleField.setEnabled(enabled);
        mBodyField.setEnabled(enabled);
        mlocationTxt.setEnabled(enabled);
        mSerialTxt.setEnabled(enabled);
        if (enabled) {
            mSubmitButton.setVisibility(View.VISIBLE);
        } else {
            mSubmitButton.setVisibility(View.GONE);
        }
    }


    // [START write_fan_out]
    private void writeNewPost(String userId, String username, String title, String body, String location, String serial,String ttitle1, String tmake1, String tmodel1, String tserial1) {
        // Create new equipment at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("posts").push().getKey();
        Equipment equipment = new Equipment(userId, username, title, body, location, serial,ttitle1, tmake1, tmodel1, tserial1);
        Map<String, Object> postValues = equipment.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        mDatabase.updateChildren(childUpdates);
    }


    // [END write_fan_out]
}
