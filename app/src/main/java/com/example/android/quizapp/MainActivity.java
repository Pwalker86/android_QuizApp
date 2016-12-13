package com.example.android.quizapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText) findViewById(R.id.question_1_answer_field);
        View.OnFocusChangeListener focusChangeListener = new MyFocusChangeListener();
        editText.setOnFocusChangeListener(focusChangeListener);
    }

    public void submitAnswer(View view) {
        checkAnswers();
        presentEndGameScreen();
    }

    public void checkAnswers() {
        String question1Answer = getString(R.string.question_1_answer);
        String question2Answer = getString(R.string.question_2_answer);
        String question3Answer = getString(R.string.question_3_answer);

        String question1UserChoice = ((EditText) findViewById(R.id.question_1_answer_field)).getText().toString();

        RadioGroup group = (RadioGroup) findViewById(R.id.answer_2_area);
        int selectedButtonId = group.getCheckedRadioButtonId();
        String question2UserChoice = null;
        if (selectedButtonId != -1) {
            question2UserChoice = ((RadioButton) findViewById(selectedButtonId)).getText().toString();
        }

        group = (RadioGroup) findViewById(R.id.answer_3_area);
        selectedButtonId = group.getCheckedRadioButtonId();

        String question3UserChoice = null;
        if (selectedButtonId != -1) {
            question3UserChoice = ((RadioButton) findViewById(selectedButtonId)).getText().toString();
        }

        if (Objects.equals(question1Answer, question1UserChoice)) {
            score++;
        }

        if (Objects.equals(question2Answer, question2UserChoice)) {
            score++;
        }

        if (Objects.equals(question3Answer, question3UserChoice)) {
            score++;
        }

        CheckBox question4Option1 = (CheckBox) findViewById(R.id.question_4_option_1);
        CheckBox question4Option2 = (CheckBox) findViewById(R.id.question_4_option_2);
        CheckBox question4Option3 = (CheckBox) findViewById(R.id.question_4_option_3);
        CheckBox question4Option4 = (CheckBox) findViewById(R.id.question_4_option_4);

        if (question4Option1.isChecked() || question4Option2.isChecked() || question4Option3.isChecked() || question4Option4.isChecked()) {
            score++;
        }

        if (score >= 3) {
            showSuccessToast();
        } else {
            showFailureToast();
        }
    }

    public void showSuccessToast() {
        Toast toast = Toast.makeText(this, R.string.success_message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showFailureToast() {
        Toast toast = makeText(this, R.string.failure_message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void presentEndGameScreen() {
        LinearLayout rootView = (LinearLayout) findViewById(R.id.activity_main);
        rootView.removeAllViews();

        TextView scoreLabel = new TextView(this);
        scoreLabel.setText(R.string.final_score_label);
        scoreLabel.setTextSize(48);
        scoreLabel.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView scoreText = new TextView(this);
        String finalScore = String.valueOf(score) + getString(R.string.possible_points_text);
        scoreText.setText(finalScore);
        scoreText.setTextSize(48);
        scoreText.setGravity(Gravity.CENTER_HORIZONTAL);

        rootView.addView(scoreLabel);
        rootView.addView(scoreText);
    }

    private class MyFocusChangeListener implements View.OnFocusChangeListener {

        public void onFocusChange(View v, boolean hasFocus) {

            if (v.getId() == R.id.question_1_answer_field && !hasFocus) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        }
    }
}

