package com.example.cis4520researchprojectphone;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cis4520researchprojectphone.code.ResultGenerator;
import com.example.cis4520researchprojectphone.databinding.ProjectDisplayFragmentBinding;

import java.io.File;
import java.io.IOException;

/**
 * ProjectDisplayFragment - The UI logic for the project
 */
public class ProjectDisplayFragment extends Fragment {

    private ProjectDisplayFragmentBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = ProjectDisplayFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set onClickListener for SHA-512 button to generate parameter-cost comparison data
        binding.generateSha512Button.setOnClickListener(v -> {
                // Show toast
                Toast generateToast = Toast.makeText(getActivity(), "Generating SHA-512 Data...", Toast.LENGTH_LONG);
                generateToast.show();
                // Run tests
                ResultGenerator resultGenerator = new ResultGenerator();
                try {
                    File fileDir = requireActivity().getFilesDir();
                    System.out.println(fileDir.getPath());
                    resultGenerator.generateSHA512Results(fileDir);
                    // Display success
                    binding.statusDisplay.setText("Generated SHA-512 data!");
                } catch (IOException e) {
                    Log.e("FirstFragment:SHA512Button", "Error occurred when generating SHA-512 data", e);
                }
            }
        );

        // Set onClickListener for BCrypt button to generate parameter-cost comparison data
        binding.generateBcryptButton.setOnClickListener(v -> {
                // Show toast
                Toast generateToast = Toast.makeText(getActivity(), "Generating BCrypt Data...", Toast.LENGTH_LONG);
                generateToast.show();
                // Run tests
                ResultGenerator resultGenerator = new ResultGenerator();
                try {
                    File fileDir = requireActivity().getFilesDir();
                    System.out.println(fileDir.getPath());
                    resultGenerator.generateBCryptResults(fileDir);
                    // Display success
                    binding.statusDisplay.setText("Generated BCrypt data!");
                } catch (IOException e) {
                    Log.e("FirstFragment:BCryptButton", "Error occurred when generating Argon2id data", e);
                }
            }
        );

        // Set onClickListener for Argon2id button to generate parameter-cost comparison data
        binding.generateArgon2Button.setOnClickListener(v -> {
                // Show toast
                Toast generateToast = Toast.makeText(getActivity(), "Generating Argon2id Data...", Toast.LENGTH_LONG);
                generateToast.show();
                // Run tests
                ResultGenerator resultGenerator = new ResultGenerator();
                try {
                    File fileDir = requireActivity().getFilesDir();
                    System.out.println(fileDir.getPath());
                    resultGenerator.generateArgon2idResults(fileDir);
                    // Display success
                    binding.statusDisplay.setText("Generated Argon2id data!");
                } catch (IOException e) {
                    Log.e("FirstFragment:Argon2idButton", "Error occurred when generating Argon2id data", e);
                }
            }
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}