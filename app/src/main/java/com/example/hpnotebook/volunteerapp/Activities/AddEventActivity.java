package com.example.hpnotebook.volunteerapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hpnotebook.volunteerapp.ModelClasses.Event;
import com.example.hpnotebook.volunteerapp.ModelClasses.User;
import com.example.hpnotebook.volunteerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Objects;

public class AddEventActivity extends AppCompatActivity {

    EditText et_event_title, et_event_description, et_event_date, et_event_time, et_event_location,
             et_event_stipend, et_event_refreshments, et_event_dresscode,
            et_event_language;
    Spinner spinner_event_category;
    ImageView event_image;
    Button btn_event_add;
    Uri imageUri;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    FirebaseStorage storage;
    StorageReference imageRef;
    String key;
    String homeId;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Add Event");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        init();

        btn_event_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // key = homeId;
                // if (homeId == null) {

                    //key = ref.push().getKey();

                //}

                String event_title = et_event_title.getText().toString();
                String event_description = et_event_description.getText().toString();
                String event_date = et_event_date.getText().toString();
                String event_time = et_event_time.getText().toString();
                String event_location = et_event_location.getText().toString();
                String event_category = spinner_event_category.getSelectedItem().toString();
                String event_stipend = et_event_stipend.getText().toString();
                String event_refreshments = et_event_refreshments.getText().toString();
                String event_dresscode = et_event_dresscode.getText().toString();
                String event_language = et_event_language.getText().toString();
                //String event_img = event_image.getDrawable().toString();
                //ArrayList<User> event_users = new ArrayList<>();

                String event_userId = user.getUid();

                Bundle bundle = new Bundle();
                bundle.putString("event_title", event_title);
                bundle.putString("event_description", event_description);
                bundle.putString("event_date", event_date);
                bundle.putString("event_time", event_time);
                bundle.putString("event_location", event_location);
                bundle.putString("event_category", event_category);
                bundle.putString("event_stipend", event_stipend);
                bundle.putString("event_refreshments", event_refreshments);
                bundle.putString("event_dresscode", event_dresscode);
                bundle.putString("event_language", event_language);
                bundle.putString("event_image", imageUri.toString());

                Intent mIntent = new Intent(AddEventActivity.this, PickEventLocationActivity.class);
                mIntent.putExtras(bundle);
                startActivity(mIntent);

                /*
                imageRef = storage.getReference("event images/" + key);

                addEvent(key, event_title,
                        event_userId, event_description,
                        event_date, event_time, event_location, event_category,
                        event_stipend, event_refreshments, event_dresscode,
                        event_language, event_users);
                */
            }
        });
    }
/*
    private void addEvent(final String event_id, final String event_title, final String event_userId, final String event_description,
                          final String event_date, final String event_time,
                          final String event_location, final String event_category, final String event_stipend,
                          final String event_refreshments, final String event_dresscode, final String event_language, final ArrayList<User> event_users) {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading");
        pd.setMessage("Please Wait...");
        pd.show();

        BitmapDrawable drawable = (BitmapDrawable) event_image.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();

        imageRef.putBytes(bytes).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if ((task.isSuccessful())) {
                            String imageUrl = Objects.requireNonNull(task.getResult()).toString();

                            Event event = new Event(event_id, event_title, event_userId,
                                    event_description, event_date, event_time, event_location,
                                    event_category, event_stipend, event_refreshments,
                                    event_dresscode, event_language, imageUrl, event_users);

                            ref.child(key).setValue(event);


                            pd.dismiss();

                            Toast.makeText(AddEventActivity.this, "Listing added", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(AddEventActivity.this, DashboardActivity.class));
                            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

                            finish();
                        }
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                pd.setMessage((int) progress + "% Uploaded");


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddEventActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
*/
    private void init() {

        et_event_title = findViewById(R.id.et_event_title);
        et_event_description = findViewById(R.id.et_event_description);
        et_event_date = findViewById(R.id.et_event_date);
        et_event_time = findViewById(R.id.et_event_time);
        et_event_location = findViewById(R.id.et_event_location);
        spinner_event_category = findViewById(R.id.spinner_event_category);
        et_event_stipend = findViewById(R.id.et_event_stipend);
        et_event_refreshments = findViewById(R.id.et_event_refreshments);
        et_event_dresscode = findViewById(R.id.et_event_dresscode);
        et_event_language = findViewById(R.id.et_event_language);
        event_image = findViewById(R.id.event_image);
        btn_event_add = findViewById(R.id.btn_event_add);

        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference("events");
    }

    public void opengallery(View view) {
        Intent gallery = new Intent();
        gallery.setAction(Intent.ACTION_PICK);
        gallery.setType("image/*");
        int pick_image = 1;
        startActivityForResult(Intent.createChooser(gallery, "Select"), pick_image);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                 imageUri = data.getData();
                event_image.setImageURI(imageUri);
            }
        }
    }

}
