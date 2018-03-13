package com.soa.jesteban.calculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {



    /**/
    String value_1 = "";

    /**/
    String value_2 = "";

    /**/
    String operator_selection = "";

    /** Results and operations     * */
    TextView resutl_text = null;
    TextView operation_text = null;

    /** Number Buttons */
    Button button_number_0 = null;
    Button button_number_1 = null;
    Button button_number_2 = null;
    Button button_number_3 = null;
    Button button_number_4 = null;
    Button button_number_5 = null;
    Button button_number_6 = null;
    Button button_number_7 = null;
    Button button_number_8 = null;
    Button button_number_9 = null;

    /** Operators */
    Button button_operator_addition = null;
    Button button_operator_subtract = null;
    Button button_operator_multiply = null;
    Button button_operator_devide = null;
    Button button_operator_float_point = null;
    Button button_operator_equals = null;
    Button button_operator_c = null;
    Button button_operator_ce = null;


    /**
     * Show a message using a toast alert
     * @param  data the message to show
     */
    public void showMessage (String data){
        Context context = getApplicationContext();
        CharSequence text = data ;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    /**
     * Sets the text that will be used as mathematical operation, the text will appear on
     * the screen as the user set numbers and operators
     * @param  text  new character to append.
     */
    public void setOperationText(CharSequence text){
        //validate if result_text is not null.
        if(this.operation_text != null){
            this.operation_text.append(text);
        }
    }


    /**
     * Sets the operands and verify which will set, the first or the second one. The decision is triggered when
     * the operator is has already set.
     * @param  data  new data (string) to append.
     */
    public void setOperands (String data) {
        //still setting first operand
        if (this.value_1 != null && this.value_2 == "" && this.operator_selection == "") {
            this.value_1 += data;
        }
        //setting second operand, the operator is set using another function.
        else{
            this.value_2 += data;
        }
    }

    /**
     * Tries to evaluate the operation.
     */
    public void evaluate () {
        String resutl = "";
        //check everything is not null
        if(this.value_1 != null && this.value_2 != null && this.operator_selection != null){
            //convert to numbers value 1 and 2
            try {
                //try to convert to number
                Double val1 = Double.parseDouble(this.value_1);
                Double val2 = Double.parseDouble(this.value_2);
                //validate the operator
                Double res = 0.0;
                switch (this.operator_selection){
                    case "+":
                        showMessage("Suma");
                        // make the operation
                        res = (val1 + val2);
                        resutl = res.toString() ;
                        break;
                    case "-":
                        showMessage("Resta");
                        // make the operation
                        res = (val1 - val2);
                        resutl = res.toString() ;
                        break;
                    case "*":
                        showMessage("Multiplicación");
                        // make the operation
                        res = (val1 * val2);
                        resutl = res.toString() ;
                        break;
                    case "/":
                        showMessage("División");
                        //validate a zero division
                        if (val2 != 0.0) {
                            res = val1 / val2;
                            resutl = res.toString();

                        }else {
                            showMessage("Error: División por cero");
                        }
                        break;

                }

            }
            catch (Exception e) {
                showMessage(e.getLocalizedMessage());
            }
        }else {
            showMessage("Expresión incompleta");
        }
        //show the result.
        resutl_text.setText(resutl);
        //clean the sentence
        eraseOperation(operation_text);
        //set the new value as val_1
        this.value_1 = resutl;
        //clean operator selector
        this.operator_selection = "";
        //clean val_2
        this.value_2 = "";
        //show the result as first operand
        this.operation_text.setText(resutl);


    }

    /**
     * Sets the operator.
     * @param data the character.
     * **/
    public void setOperatorSelection (String data){
        //its time to eval
        if (this.operator_selection != "") {
            //try to evaluate
            evaluate();
            //the first operand is already set.
            this.operator_selection = data;
        }
        // math sentence is not ready
        else {
            this.operator_selection = data;
        }
    }

    /**
     * This function deletes the last character on the operation text.
     * @param textview The text view to set the text.
     * **/
    public void deleteLastCharacter (TextView textview){
        //Get the data char sequence of the text view.
        CharSequence text = textview.getText();
        //Set basic string
        CharSequence result = "";
        if(text != null && text.length() > 1 ){
            result = text.subSequence(0,text.length()-1);
        }
        textview.setText(result);
    }

    /**
     *This function deletes the operation view.
     *@param textview The text view to delete
     */
    public void eraseOperation (TextView textview){
        //Clean the Screen
        textview.setText("");
        //Set the operands to empty string
        this.value_1 = "";
        this.value_2 = "";
        //Set the operand to empty string
        this.operator_selection ="";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the text view that refers the result
        this.resutl_text = (TextView) findViewById(R.id.result_text);
        // Get the text view that refers the operation
        this.operation_text = (TextView) findViewById(R.id.result_operation);

        //Get all buttons
        //Numero 0
        this.button_number_0 = (Button) findViewById(R.id.number_0);
        button_number_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //write to the screen
                setOperationText("0");
                //set operand first or second
                setOperands("0");

            }
        });

        //Numero 1
        this.button_number_1 = (Button) findViewById(R.id.number_1);
        this.button_number_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperationText("1");
                //set operand first or second
                setOperands("1");
            }
        });
        //Numero 2
        this.button_number_2 = (Button) findViewById(R.id.number_2);
        this.button_number_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperationText("2");
                //set operand first or second
                setOperands("2");
            }
        });
        //Numero 3
        this.button_number_3 = (Button) findViewById(R.id.number_3);
        this.button_number_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperationText("3");
                //set operand first or second
                setOperands("3");
            }
        });
        //Numero 4
        this.button_number_4 = (Button) findViewById(R.id.number_4);
        this.button_number_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperationText("4");
                //set operand first or second
                setOperands("4");
            }
        });
        //Numero 5
        this.button_number_5 = (Button) findViewById(R.id.number_5);
        this.button_number_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperationText("5");
                //set operand first or second
                setOperands("5");
            }
        });
        //Numero 6
        this.button_number_6 = (Button) findViewById(R.id.number_6);
        this.button_number_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperationText("6");
                //set operand first or second
                setOperands("6");
            }
        });
        //Numero 7
        this.button_number_7 = (Button) findViewById(R.id.number_7);
        this.button_number_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperationText("7");
                //set operand first or second
                setOperands("7");
            }
        });
        //Numero 8
        this.button_number_8 = (Button) findViewById(R.id.number_8);
        this.button_number_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperationText("8");
                //set operand first or second
                setOperands("8");
            }
        });
        //Numero 9
        this.button_number_9 = (Button) findViewById(R.id.number_9);
        this.button_number_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperationText("9");
                //set operand first or second
                setOperands("9");
            }
        });
        //Operator .
        this.button_operator_float_point = (Button) findViewById(R.id.operator_float_point);
        this.button_operator_float_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperationText(".");
                //set operand first or second
                setOperands(".");
            }
        });
        //Operator +
        this.button_operator_addition = (Button) findViewById(R.id.operator_addition);
        this.button_operator_addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperationText("+");
                //set the operator
                setOperatorSelection("+");
            }
        });
        //Operator -
        this.button_operator_subtract = (Button) findViewById(R.id.operator_subtract);
        this.button_operator_subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperationText("-");
                //set the operator
                setOperatorSelection("-");
            }
        });
        //Operator *
        this.button_operator_multiply = (Button) findViewById(R.id.operator_multiply);
        this.button_operator_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperationText("*");
                //set the operator
                setOperatorSelection("*");
            }
        });

        //Operator /
        this.button_operator_devide = (Button) findViewById(R.id.operator_devide);
        this.button_operator_devide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOperationText("/");
                //set the operator
                setOperatorSelection("/");
            }
        });
        //Operator =
        this.button_operator_equals = (Button) findViewById(R.id.operator_equals);
        this.button_operator_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //try to eval
                evaluate();
            }
        });
        //Operator CE
        this.button_operator_ce = (Button) findViewById(R.id.button_ce);
        this.button_operator_ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteLastCharacter(operation_text);
            }
        });
        //Operator C
        this.button_operator_c = (Button) findViewById(R.id.button_c);
        this.button_operator_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eraseOperation(operation_text);
                eraseOperation(resutl_text);
            }
        });

    }
}
