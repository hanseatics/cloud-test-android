package com.hanseatics.cloudtestandroid;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView mSolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText number1 = (EditText) findViewById(R.id.number1);
        EditText operator = (EditText) findViewById(R.id.operator);
        EditText number2 = (EditText) findViewById(R.id.number2);

        Button solve = (Button) findViewById(R.id.solve);
        if (solve != null) {
            solve.setOnClickListener(new SolutionFinder(number1, operator, number2));
        }

        mSolution = (TextView) findViewById(R.id.solution);
    }

    private class SolutionFinder implements View.OnClickListener {

        private final EditText mNumber1;
        private final EditText mOperator;
        private final EditText mNumber2;

        public SolutionFinder(EditText number1, EditText operator, EditText number2) {
            mNumber1 = number1;
            mOperator = operator;
            mNumber2 = number2;
        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            if (mNumber1 == null || TextUtils.isEmpty(mNumber1.getText())) {
                showSolution(context.getString(R.string.input_error));
                return;
            }
            if (mOperator == null || TextUtils.isEmpty(mOperator.getText())) {
                showSolution(context.getString(R.string.input_error));
                return;
            }
            if (mNumber2 == null || TextUtils.isEmpty(mNumber2.getText())) {
                showSolution(context.getString(R.string.input_error));
                return;
            }

            try {
                float number1 = Float.valueOf(mNumber1.getText().toString());
                float number2 = Float.valueOf(mNumber2.getText().toString());
                String operator = mOperator.getText().toString();

                float solution = 0;
                if ("+".equals(operator)) {
                    solution = number1 + number2;
                } else if ("-".equals(operator)) {
                    solution = number1 - number2;
                } else if ("*".equals(operator)) {
                    solution = number1 * number2;
                } else if ("/".equals(operator)) {
                    if (number2 == 0) {
                        showSolution(context.getString(R.string.division_by_zero));
                        return;
                    }
                    solution = number1 / number2;
                } else {
                    showSolution(context.getString(R.string.unknown_operator));
                    return;
                }
                showSolution(String.valueOf(solution));
            } catch (NumberFormatException ex) {
                showSolution(context.getString(R.string.input_error));
            }
        }

        private void showSolution(String solution) {
            if (mSolution != null) {
                mSolution.setText(solution);
            }
        }
    }
}
