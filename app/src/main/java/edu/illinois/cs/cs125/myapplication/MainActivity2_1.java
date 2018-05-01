package edu.illinois.cs.cs125.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.view.KeyEvent;


public class MainActivity2_1 extends AppCompatActivity {
    double principal;
    double rate;
    double tim;
    double amount;
    private Button button;
    private Button compute;
    private Switch s;
    EditText principal_edit;
    EditText rate_edit;
    EditText time_edit;
    TextView amount_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        principal_edit=(EditText) findViewById(R.id.Principal_input);
        rate_edit=(EditText) findViewById(R.id.Rate_input);
        time_edit=(EditText) findViewById(R.id.Time_input);
        amount_result=(TextView) findViewById(R.id.Amount_output);

        s=(Switch) findViewById(R.id.switch1);
        button = (Button) findViewById(R.id.back_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBack();
            }
        });

        compute = (Button) findViewById(R.id.compute_button);
        compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean handled =true;


                if(!(principal_edit.getText().toString().equals("") || rate_edit.getText().toString().equals("") || time_edit.getText().toString().equals("")))
                {
                    principal = Double.parseDouble(principal_edit.getText().toString());
                    rate = Double.parseDouble(rate_edit.getText().toString());
                    tim = Double.parseDouble(time_edit.getText().toString());

                    if(s.isChecked()==false)
                    {
                        amount=principal*(1+(rate/100*tim));
                    }
                    else
                    {
                        amount= principal*Math.pow((1+(rate/100)),tim);
                    }
                    amount_result.setText(Double.toString(amount));
                }
                else
                {
                    amount_result.setText("N/A");
                }



            }
        });


        final TextView myAwesomeTextView = (TextView)findViewById(R.id.Amount_output);














    }
    public void getBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
