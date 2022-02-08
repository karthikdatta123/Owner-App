package com.example.ownerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.ownerapp.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    View view;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        view=inflater.inflate(R.layout.fragment_first,container,false);
        return view;
    }

}