package com.google.firebase.cs.database;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.cs.database.R;
import com.google.firebase.cs.database.models.Equipment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateActivity extends AppCompatActivity {

    public static final String POST_KEY = "key";

    private Button btnupdate;
    private EditText edtitle;
    private EditText edmodel;
    private String PostKey;


    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private ValueEventListener mPostListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PostKey = getIntent().getStringExtra(POST_KEY);

        setContentView(R.layout.activity_update);
        edmodel = (EditText) findViewById(R.id.edmodel);
        edtitle = (EditText) findViewById(R.id.edtitle);
        btnupdate = (Button) findViewById(R.id.button5);

        edmodel.setText(PostKey);


    }
    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Equipment object and use the values to update the UI


                Equipment post = dataSnapshot.getValue(Equipment.class);
                // [START_EXCLUDE]

                if(dataSnapshot.exists()) {
                    edmodel.setText(post.ttitle1);
                    edtitle.setText(post.tmodel1);
                }
                else{
                    finish();
                }
                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Equipment failed, log a message
                // [START_EXCLUDE]
                Toast.makeText(UpdateActivity.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        database.addValueEventListener(postListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mPostListener = postListener;

    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mPostListener != null) {
            database.removeEventListener(mPostListener);
        }

        // Clean up comments listener
    }

};
