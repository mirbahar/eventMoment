package com.eventmoment.rahat.eventmoment.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.eventmoment.rahat.eventmoment.Model.EventMoment;
import com.eventmoment.rahat.eventmoment.R;

public class MomentViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment_view);

       EventMoment eventMoment = (EventMoment) getIntent().getSerializableExtra("eventMoment");

        Toast.makeText(this, eventMoment.getMomentPhotoPath(), Toast.LENGTH_SHORT).show();
    }
}
