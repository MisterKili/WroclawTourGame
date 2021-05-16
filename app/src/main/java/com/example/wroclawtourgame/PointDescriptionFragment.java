package com.example.wroclawtourgame;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PointDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PointDescriptionFragment extends Fragment {

    private TextView tvPointDescription;
    private ImageView ivGoToQuestion;

    private String mDescription;

    public PointDescriptionFragment() {
        // Required empty public constructor
    }

    public static PointDescriptionFragment newInstance(String description) {
        PointDescriptionFragment fragment = new PointDescriptionFragment();
        fragment.mDescription = description;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_point_description, container, false);

        tvPointDescription = rootView.findViewById(R.id.pointDescriptionTextView);
        tvPointDescription.setText(mDescription);

        ivGoToQuestion = rootView.findViewById(R.id.goToQuestionImageView);
        ivGoToQuestion.setOnClickListener(v -> {
            PointActivity pointActivity = (PointActivity) getActivity();
            assert pointActivity != null;
            pointActivity.startGoToQuestionFragment();
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}