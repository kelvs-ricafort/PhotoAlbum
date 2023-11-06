package com.newtechieblog.wordpress.views.photoalbum;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.Manifest;

public class AddImageActivity extends AppCompatActivity {
    private ImageView imageViewAddImage;
    private EditText editTextAddTitle, editTextAddDescription;
    private Button btnSave;
    ActivityResultLauncher<Intent> activityResultLauncherForSelectImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(R.string.add_image);
        setContentView(R.layout.activity_add_image);

        registerActivityForSelectImage();

        imageViewAddImage = findViewById(R.id.imageViewAddImage);
        editTextAddTitle = findViewById(R.id.editTextAddTitle);
        editTextAddDescription = findViewById(R.id.editTextAddDescription);
        btnSave = findViewById(R.id.btnSave);

        imageViewAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(AddImageActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AddImageActivity.this,
                            new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncherForSelectImage.launch(intent);
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void registerActivityForSelectImage() {
        activityResultLauncherForSelectImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {

                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncherForSelectImage.launch(intent);
        }
    }
}