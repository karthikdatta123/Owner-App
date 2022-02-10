package com.example.ownerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new UpdateMenuMain());

        DAOOwner daoOwner=new DAOOwner();

    }

    private void replaceFragment(UpdateMenuMain updateMenuMain) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.FrameLayout, updateMenuMain);
        transaction.commit();
    }
}
