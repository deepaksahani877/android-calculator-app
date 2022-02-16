package com.app.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private TextView ac;
    private TextView clear;
    private TextView percentage;
    private TextView division;
    private TextView multiplication;
    private TextView subtraction;
    private TextView addition;
    private TextView equal;
    private TextView zero,one,two,three,four,five,six,seven,eight,nine;
    private TextView point;

    TextView textViewExpression,textViewResult;
    String result="";
    String expression="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ac = findViewById(R.id.text_ac);
        clear = findViewById(R.id.text_x);
        percentage = findViewById(R.id.text_percentage);
        division = findViewById(R.id.text_division);
        multiplication  = findViewById(R.id.text_multiplication);
        subtraction = findViewById(R.id.text_subtraction);
        addition = findViewById(R.id.text_addition);
        equal = findViewById(R.id.text_equal);
        zero = findViewById(R.id.text_0);
        one = findViewById(R.id.text_1);
        two = findViewById(R.id.text_2);
        three = findViewById(R.id.text_3);
        four = findViewById(R.id.text_4);
        five = findViewById(R.id.text_5);
        six = findViewById(R.id.text_6);
        seven = findViewById(R.id.text_7);
        eight = findViewById(R.id.text_8);
        nine = findViewById(R.id.text_9);
        point = findViewById(R.id.text_point);
        textViewExpression = findViewById(R.id.textView_expression);
        textViewResult = findViewById(R.id.textView_result);

        textViewResult.setText("0");
        textViewExpression.setText("");


        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression="";
                result = "0";
                textViewExpression.setText(expression);
                textViewResult.setText(result);


            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!expression.equals("")) {
                    expression = expression.substring(0,expression.length()-1);
                    textViewExpression.setText(expression);
                }
                equal.callOnClick();

            }
        });

        percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!expression.equals("")&&!(expression.contains("+")||expression.contains("-")||expression.contains("*")||expression.contains("/")||expression.contains("%"))) {

                     if(expression.charAt(expression.length()-1)=='+'||expression.charAt(expression.length()-1)=='-'||expression.charAt(expression.length()-1)=='*'||expression.charAt(expression.length()-1)=='/'){
                        expression=expression.substring(0,expression.length()-1)+"%";
                        textViewExpression.setText(expression);

                    }
                   else if(!(expression.charAt(expression.length()-1)=='%')) {
                        expression += "%";
                        textViewExpression.setText(expression);
                    }
                }
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!expression.equals(""))
                {
                    if(!expression.substring(0,expression.length()-1).contains("%"))
                    {
                        if (expression.charAt(expression.length() - 1) == '+' || expression.charAt(expression.length() - 1) == '-' || expression.charAt(expression.length() - 1) == '*' || expression.charAt(expression.length() - 1) == '%') {
                            expression = expression.substring(0, expression.length() - 1) + "/";
                            textViewExpression.setText(expression);

                        } else if (!(expression.charAt(expression.length() - 1) == '/')) {
                            expression += "/";
                            textViewExpression.setText(expression);
                        }
                    }
                }
                else {
                    expression+="0/";
                    textViewExpression.setText(expression);
                }

            }
        });

        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!expression.equals(""))
                {
                    if(!expression.substring(0,expression.length()-1).contains("%")){
                        if(expression.charAt(expression.length()-1)=='+'||expression.charAt(expression.length()-1)=='-'||expression.charAt(expression.length()-1)=='%'||expression.charAt(expression.length()-1)=='/'){
                            expression=expression.substring(0,expression.length()-1)+"*";
                            textViewExpression.setText(expression);

                        }
                        else
                        if(!(expression.charAt(expression.length()-1)=='*')) {
                            expression += "*";
                            textViewExpression.setText(expression);
                        }
                    }
                }
                else {
                    expression+="0*";
                    textViewExpression.setText(expression);
                }
            }
        });

        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!expression.equals(""))
                {
                    if(!expression.substring(0,expression.length()-1).contains("%")){
                        if(expression.charAt(expression.length()-1)=='+'||expression.charAt(expression.length()-1)=='%'||expression.charAt(expression.length()-1)=='*'||expression.charAt(expression.length()-1)=='/'){
                            expression=expression.substring(0,expression.length()-1)+"-";
                            textViewExpression.setText(expression);

                        }

                        else
                        if(!(expression.charAt(expression.length()-1)=='-')) {
                            expression += "-";
                            textViewExpression.setText(expression);
                        }
                    }
                }
                else {
                    expression+="0-";
                    textViewExpression.setText(expression);
                }

            }
        });
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!expression.equals(""))
                {
                    if(!expression.substring(0,expression.length()-1).contains("%")){
                        if(expression.charAt(expression.length()-1)=='%'||expression.charAt(expression.length()-1)=='-'||expression.charAt(expression.length()-1)=='*'||expression.charAt(expression.length()-1)=='/'){
                            expression=expression.substring(0,expression.length()-1)+"+";
                            textViewExpression.setText(expression);

                        }
                        else
                        if(!(expression.charAt(expression.length()-1)=='+')) {
                            expression += "+";
                            textViewExpression.setText(expression);
                        }
                    }
                }
                else {
                    expression+="0+";
                    textViewExpression.setText(expression);
                }
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(expression.endsWith("+")||expression.endsWith("-")||expression.endsWith("*")||expression.endsWith("/")){
                    textViewResult.setText("error");

                }
                else if(expression.equals("")){
                    textViewResult.setText("0");
                }
                else if(!expression.contains("%")) {
                    result = evaluateExpression(expression);
                    textViewResult.setText(result);
                }

                else{
                    String[] s= expression.split("%");
                    //Toast.makeText(getApplicationContext(),s.length+"",Toast.LENGTH_SHORT).show();

                    if(s.length==1){
                        result = Float.toString(Float.parseFloat(s[0])/100);
                        textViewResult.setText(result);

                    }
                    else{
                        result= Float.toString(Float.parseFloat(s[0])*Float.parseFloat(s[1])/100);
                        textViewResult.setText("="+result);
                    }

                }
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(expression.equals("")){
                    result = "0";
                    textViewResult.setText(result);
                }
                else {
                    expression+="0";
                    textViewExpression.setText(expression);
                }
                equal.callOnClick();

            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression +="1";
                textViewExpression.setText(expression);
                equal.callOnClick();
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expression +="2";
                textViewExpression.setText(expression);
                equal.callOnClick();
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expression +="3";
                textViewExpression.setText(expression);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expression +="4";
                textViewExpression.setText(expression);
                equal.callOnClick();
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expression +="5";
                textViewExpression.setText(expression);
                equal.callOnClick();
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expression +="6";
                textViewExpression.setText(expression);
                equal.callOnClick();
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expression +="7";
                textViewExpression.setText(expression);
                equal.callOnClick();
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression +="8";
                textViewExpression.setText(expression);
                equal.callOnClick();
            }
        });


        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression +="9";
                textViewExpression.setText(expression);
                equal.callOnClick();
            }
        });

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!expression.contains(".") && !expression.equals("")){
                    expression +=".";
                    textViewExpression.setText(expression);
                }
                if(expression.equals("")){
                    expression = "0.";
                    textViewExpression.setText(expression);
                }
            }
        });

    }

    String evaluateExpression(String expression){
        Stack<Integer> op  = new Stack<Integer>();
        Stack<Double> val = new Stack<Double>();
        /* Create temporary stacks for operators and operands */
        Stack<Integer> optmp  = new Stack<Integer>();
        Stack<Double> valtmp = new Stack<Double>();
//        /* Accept expression */
//        System.out.println("Evaluation Of Arithmetic Expression Using Stacks Test\n");
//        System.out.println("Enter expression\n");
//        String input = scan.next();
        expression = "0" + expression;
        expression = expression.replaceAll("-","+-");
        /* Store operands and operators in respective stacks */
        String temp = "";
        for (int i = 0;i < expression.length();i++)
        {
            char ch = expression.charAt(i);
            if (ch == '-')
                temp = "-" + temp;
            else if (ch != '+' &&  ch != '*' && ch != '/')
                temp = temp + ch;
            else
            {
                val.push(Double.parseDouble(temp));
                op.push((int)ch);
                temp = "";
            }
        }
        val.push(Double.parseDouble(temp));
        /* Create char array of operators as per precedence */
        /* -ve sign is already taken care of
        +while storing */
        char operators[] = {'/','*','+'};
        /* Evaluation of expression */
        for (int i = 0; i < 3; i++)
        {
            boolean it = false;
            while (!op.isEmpty())
            {
                int optr = op.pop();
                double v1 = val.pop();
                double v2 = val.pop();
                if (optr == operators[i])
                {
                    /* if operator matches evaluate and store in temporary stack */
                    if (i == 0)
                    {
                        valtmp.push(v2 / v1);
                        it = true;
                        break;
                    }
                    else if (i == 1)
                    {
                        valtmp.push(v2 * v1);
                        it = true;
                        break;
                    }
                    else if (i == 2)
                    {
                        valtmp.push(v2 + v1);
                        it = true;
                        break;
                    }
                }
                else
                {
                    valtmp.push(v1);
                    val.push(v2);
                    optmp.push(optr);
                }
            }
            /* Push back all elements from temporary stacks to main stacks */
            while (!valtmp.isEmpty())
                val.push(valtmp.pop());
            while (!optmp.isEmpty())
                op.push(optmp.pop());
            /* Iterate again for same operator */
            if (it)
                i--;
        }

        return "="+Double.toString(val.pop());
    }

}