package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    TextView tv_operation, tv_result;
    String strOperation;

    final static String ERR = "err";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_operation = (TextView) findViewById(R.id.operation);
        tv_result = (TextView) findViewById(R.id.result);

        strOperation = "";
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("tv_operation",  tv_operation.getText().toString());
        outState.putString("tv_result",     tv_result.getText().toString());
        outState.putString("strOperation",  strOperation);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        tv_operation.setText(savedInstanceState.getString("tv_operation"));
        tv_result.setText(savedInstanceState.getString("tv_result"));
        strOperation = savedInstanceState.getString("strOperation");
    }

    // actions caculator
    private void updateOperation(String character) {
        strOperation += character;
        tv_operation.setText(strOperation);
        tv_result.setText(operate(strOperation));
    }
    private String operate(String operation) {

        Expression expreOpera = new Expression(operation);
        Boolean isCorrect = expreOpera.checkLexSyntax();
        String strResult = "";

        if (isCorrect) {
            String auxResult = String.valueOf(expreOpera.calculate());

            if (auxResult.equals("NaN")) {
                strResult = ERR;
            } else {
                strResult = auxResult;
            }

        } else {
            strResult = ERR;
        }

        return (operation.equals("")) ? "" : strResult;
    }

    // buttons actions
    public void dropCharacter(View view) {
        try {
            strOperation = strOperation.substring(0, strOperation.length()-1);
            String resultUpdated = operate( strOperation );

            tv_operation.setText(strOperation);
            tv_result.setText(resultUpdated);
        }
        catch (Exception e) { }
    }
    public void signEqual(View view) {

        String resultUpdated = operate(strOperation);
        strOperation = resultUpdated;

        tv_operation.setText(resultUpdated);
        tv_result.setText("");
    }
    public void dropAll(View view) {
        try {
            strOperation = "";
            String resultUpdated = operate( strOperation );

            tv_operation.setText(strOperation);
            tv_result.setText(resultUpdated);
        }
        catch (Exception e) { }
    }

    // buttons numbers
    public void num1(View view) {
        updateOperation("1");
    }
    public void num2(View view) {
        updateOperation("2");
    }
    public void num3(View view) {
        updateOperation("3");
    }
    public void num4(View view) {
        updateOperation("4");
    }
    public void num5(View view) {
        updateOperation("5");
    }
    public void num6(View view) {
        updateOperation("6");
    }
    public void num7(View view) {
        updateOperation("7");
    }
    public void num8(View view) {
        updateOperation("8");
    }
    public void num9(View view) {
        updateOperation("9");
    }
    public void num0(View view) {
        updateOperation("0");
    }

    // buttons basics operations
    public void signSum(View view) {
        updateOperation("+");
    }
    public void signSubtraction(View view) {
        updateOperation("-");
    }
    public void signMulti(View view) {
        updateOperation("*");
    }
    public void signDivide(View view) {
        updateOperation("/");
    }
    public void signPoint(View view){ updateOperation(".");}

    // buttons avanced operations
    public void signPorcent(View view){ updateOperation("%");}
    public void signParenLeft(View view) {
        updateOperation("(");
    }
    public void signParenRight(View view) {
        updateOperation(")");
    }
    public void signFraction(View view){ updateOperation("1/");}
    public void signFactorial(View view){ updateOperation("!");}
    public void signRaiz(View view){ updateOperation("sqrt(");}
    public void signPi(View view){ updateOperation("pi");}
    public void signLn(View view){ updateOperation("ln(");}
    public void signLog(View view){ updateOperation("log10(");}
    public void signE(View view){ updateOperation("e");}
    public void signSin(View view){ updateOperation("sin(");}
    public void signASin(View view){ updateOperation("asin(");}
    public void signCos(View view){ updateOperation("cos(");}
    public void signACos(View view){ updateOperation("acos(");}
    public void signTan(View view){ updateOperation("tan(");}
    public void signATan(View view){ updateOperation("atan(");}
}