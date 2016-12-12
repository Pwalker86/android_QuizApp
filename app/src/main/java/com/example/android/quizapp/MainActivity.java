package com.example.android.quizapp;

import android.graphics.Color;
import android.media.audiofx.LoudnessEnhancer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Objects;

import static android.R.attr.button;
import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {
    int questionNumber;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionNumber = 1;
    }

    public void submitAnswer(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.answer_area);
        int selectedButtonId = group.getCheckedRadioButtonId();
        String userAnswer = ((RadioButton) findViewById(selectedButtonId)).getText().toString();

        checkAnswer(questionNumber, userAnswer);
        questionNumber += 1;
        group.clearCheck();
        advanceQuestion();
    }

    private void advanceQuestion() {
        TextView questionView = (TextView) findViewById(R.id.question_area);
        RadioButton answer_option1 = (RadioButton) findViewById(R.id.answer_option_1);
        RadioButton answer_option2 = (RadioButton) findViewById(R.id.answer_option_2);
        RadioButton answer_option3 = (RadioButton) findViewById(R.id.answer_option_3);
        RadioButton answer_option4 = (RadioButton) findViewById(R.id.answer_option_4);
        switch (questionNumber) {
            case 1:
                questionView.setText(R.string.question_1_text);
                answer_option1.setText(getString(R.string.question_1_option_1));
                answer_option2.setText(getString(R.string.question_1_option_2));
                answer_option3.setText(getString(R.string.question_1_option_3));
                answer_option4.setText(getString(R.string.question_1_option_4));
                break;
            case 2:
                questionView.setText(R.string.question_1_text);
                answer_option1.setText(getString(R.string.question_2_option_1));
                answer_option2.setText(getString(R.string.question_2_option_2));
                answer_option3.setText(getString(R.string.question_2_option_3));
                answer_option4.setText(getString(R.string.question_2_option_4));
                break;
            case 3:
                questionView.setText(R.string.question_1_text);
                answer_option1.setText(getString(R.string.question_3_option_1));
                answer_option2.setText(getString(R.string.question_3_option_2));
                answer_option3.setText(getString(R.string.question_3_option_3));
                answer_option4.setText(getString(R.string.question_3_option_4));
                break;
            case 4:
                questionView.setText(R.string.question_1_text);
                answer_option1.setText(getString(R.string.question_4_option_1));
                answer_option2.setText(getString(R.string.question_4_option_2));
                answer_option3.setText(getString(R.string.question_4_option_3));
                answer_option4.setText(getString(R.string.question_4_option_4));
                break;
            default:
//                End game screen
                questionView.setVisibility(View.GONE);
                Button submitButton = (Button) findViewById(R.id.submit_button);
                submitButton.setVisibility(View.GONE);
                RadioGroup group = (RadioGroup) findViewById(R.id.answer_area);
                group.setVisibility(View.GONE);
                TextView scoreLabel = (TextView) findViewById(R.id.score_header);
                scoreLabel.setText("Final Score:");
                TextView scoreText = (TextView) findViewById(R.id.score_text);
                scoreText.setText(String.format(Locale.ENGLISH, "%d", score));
                break;
        }
    }

    public void checkAnswer(int questionNumber, String userAnswer) {
        String correctAnswer;
        TextView scoreText = (TextView) findViewById(R.id.score_text);
        switch (questionNumber) {
            case 1:
                correctAnswer = getString(R.string.question_1_answer);
                break;
            case 2:
                correctAnswer = getString(R.string.question_2_answer);
                break;
            case 3:
                correctAnswer = getString(R.string.question_3_answer);
                break;
            case 4:
                correctAnswer = "anything";
                break;
            default:
                correctAnswer = "Something went wrong";
                break;
        }

        if (questionNumber == 4) {
            score += 1;
            scoreText.setText(String.format(Locale.ENGLISH, "%d", score));
            Toast toast = Toast.makeText(this, "You did it!", Toast.LENGTH_SHORT);
            toast.show();
        } else if (Objects.equals(userAnswer, correctAnswer)) {
            score += 1;
            scoreText.setText(String.format(Locale.ENGLISH, "%d", score));
            Toast toast = Toast.makeText(this, "You did it!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = makeText(this, "Lol no", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
