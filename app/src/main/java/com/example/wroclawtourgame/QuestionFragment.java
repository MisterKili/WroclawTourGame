package com.example.wroclawtourgame;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {

    private TextView tvQuestion;
    private EditText etAnswer;
    private Button bAnswer;

    private String mQuestion;
    private String mAnswer;

    public QuestionFragment() {
        // Required empty public constructor
    }


    public static QuestionFragment newInstance(String question, String answer) {
        QuestionFragment fragment = new QuestionFragment();
        fragment.mQuestion = question;
        fragment.mAnswer = answer;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_question, container, false);
        tvQuestion = rootView.findViewById(R.id.questionValueTextView);
        tvQuestion.setText(mQuestion);

        etAnswer = rootView.findViewById(R.id.answerEditText);

        bAnswer = rootView.findViewById(R.id.answerButton);
        bAnswer.setOnClickListener(v -> {
            if (answerIsCorrect()) {
                Toast.makeText(rootView.getContext(), (R.string.good_answer), Toast.LENGTH_SHORT).show();

                PointActivity pointActivity = (PointActivity) getActivity();
                assert pointActivity != null;
                pointActivity.pointFinished();
            } else {
                Toast.makeText(rootView.getContext(), (R.string.wrong_answer_try_again), Toast.LENGTH_SHORT).show();
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }

    private boolean answerIsCorrect(){
        return etAnswer.getText().toString().toLowerCase().equals(mAnswer.toLowerCase());
    }
}