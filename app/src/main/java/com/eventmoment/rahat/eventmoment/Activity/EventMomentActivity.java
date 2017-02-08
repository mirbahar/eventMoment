package com.eventmoment.rahat.eventmoment.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.eventmoment.rahat.eventmoment.Model.EventMoment;
import com.eventmoment.rahat.eventmoment.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EventMomentActivity extends AppCompatActivity {

    static final int REQUEST_CAMERA_INTENT = 1;
    String currentMomentPhotoPath;
    Bitmap bitmap;
    EditText momentTitle;
    EditText momentDescription;
    Button takeMomentBtn;
    ImageView imageView;
    Button saveMomentBtn;
    EventMoment eventMoment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_moment);

        momentTitle = (EditText) findViewById(R.id.momentTitleET);
        momentDescription = (EditText) findViewById(R.id.momentDescriptionET);
        takeMomentBtn = (Button) findViewById(R.id.takeMomentBtn);
        imageView = (ImageView) findViewById(R.id.eventMomentImageView);
        saveMomentBtn = (Button) findViewById(R.id.momentSave);
    }

    public void takeMoment(View view) {

        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(cameraIntent.resolveActivity(getPackageManager())!=null){
            File photoFile = null;
            try {
                photoFile = createImageFile();
            }catch (IOException e){
                Toast.makeText(this,e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            if(photoFile!=null){
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(cameraIntent,REQUEST_CAMERA_INTENT);
            }
        }
    }

    private File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName="JPEG_"+timeStamp+"_";
        File storageDir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,".jpg",storageDir);
        currentMomentPhotoPath = image.getAbsolutePath();

        return image;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CAMERA_INTENT && resultCode==RESULT_OK){
            int targetW = imageView.getWidth();
            int targetH = imageView.getHeight();

            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inJustDecodeBounds=true;
            BitmapFactory.decodeFile(currentMomentPhotoPath,options);
            int photoW=options.outWidth;
            int photoH=options.outHeight;

            int scaleFactor=Math.min(photoW/targetW,photoH/targetH);

            options.inJustDecodeBounds=false;
            options.inSampleSize=scaleFactor;
            Toast.makeText(this, currentMomentPhotoPath, Toast.LENGTH_SHORT).show();
            bitmap=BitmapFactory.decodeFile(currentMomentPhotoPath,options);
            imageView.setImageBitmap(bitmap);

        }
    }

    public void saveMoment(View view) {

        eventMoment.setMomentPhotoPath(this.currentMomentPhotoPath);
        eventMoment.setTitle(momentTitle.getText().toString());
        eventMoment.setTitle(momentTitle.getText().toString());

        Intent intent = new Intent(EventMomentActivity.this,MomentViewActivity.class);
        intent.putExtra("eventMoment",eventMoment);
        startActivity(intent);

    }
}
