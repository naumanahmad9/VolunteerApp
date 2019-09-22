package com.example.hpnotebook.volunteerapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hpnotebook.volunteerapp.ModelClasses.Event;
import com.example.hpnotebook.volunteerapp.ModelClasses.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewApplicantsAdapter extends RecyclerView.Adapter<ApplicantsViewHolder> {

    private ArrayList<User> users;
    private Context mContext;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String eventid;

    public ViewApplicantsAdapter(ArrayList<User> users, Context context, String eventid) {
        this.users = users;
        this.mContext = context;
        this.eventid = eventid;
    }

    @NonNull
    @Override
    public ApplicantsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_applicants_item, viewGroup, false);
        return new ApplicantsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicantsViewHolder applicantsViewHolder, int i) {

        final User user = users.get(i);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("applicants").child(eventid).child(user.getUid());

        applicantsViewHolder.applicant_name.setText(user.getName());
        applicantsViewHolder.applicant_email.setText(user.getEmail());
        applicantsViewHolder.applicant_contact.setText(user.getContact());

        if (user.getImageURL().equals("default")) {
            applicantsViewHolder.applicant_image.setImageResource(R.drawable.user_shape);
        } else {
            Glide.with(mContext).load(user.getImageURL()).into(applicantsViewHolder.applicant_image);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}

// ViewHolder
class ApplicantsViewHolder extends RecyclerView.ViewHolder {

    CircleImageView applicant_image;
    TextView applicant_name;
    TextView applicant_email;
    TextView applicant_contact;

    public ApplicantsViewHolder(@NonNull View itemView) {
        super(itemView);

        applicant_image = itemView.findViewById(R.id.applicant_image);
        applicant_name = itemView.findViewById(R.id.applicant_name);
        applicant_email = itemView.findViewById(R.id.applicant_email);
        applicant_contact = itemView.findViewById(R.id.applicant_contact);
    }
}