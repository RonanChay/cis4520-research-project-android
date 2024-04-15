package com.example.cis4520researchprojectphone;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cis4520researchprojectphone.databinding.ActivityMainBinding;

/**
 * Main runner class - Creates and runs the application
 */
public class ResearchProject extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
    }
}