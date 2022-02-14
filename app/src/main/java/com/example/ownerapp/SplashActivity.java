package com.example.ownerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		ImageView icon=findViewById(R.id.imageView);
		icon.setAlpha(0f);
		icon.animate().setDuration(2000).alpha(1f).withEndAction(new Runnable() {
			@Override
			public void run() {
				Intent i= new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);
				overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
			}
		});
	}
}