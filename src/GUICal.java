//  GUICal.java
//  Student ID: 260712639
//  Name: Yudi.Xie
//  Created by Yudi.Xie on 2017/10/29.
//  Copyright © 2017 Yudi.Xie. All rights reserved.

import java.awt.*;
import java.awt.event.*;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import acm.gui.TableLayout;
import acm.program.Program;

import static java.lang.System.out;
//Import all needed class

public class GUICal extends Program implements ActionListener,ChangeListener {

    JSlider prec_Slider;
    //precision slider

    private final String[] key_thing = {"%","1/x","^","(",")","/","7","8","9","x","4","5","6","-","1",
            "2","3","+","+/-","0", ".","="};
    private final String[] command_thing = {"C","->"};
    private JButton key[]=new JButton[key_thing.length];
    private JButton command[]=new JButton[command_thing.length];
    //buttons for digits,operators and two commands

    private JTextField resultText = new JTextField("");
    JTextField read_in = new JTextField("");
    JTextField prec_tf = new JTextField("");
    //textfields for input, output and precision

    final JDialog error = new JDialog();
    final JDialog intro = new JDialog();
    //two dialog window for extra information




    public void init(){



        JLabel error1 = new JLabel("The divider cannot be 0!");
        JLabel self = new JLabel("Hello! This is a simple GUI Calculator by YuDi.Xie, Enjoy!");
        JLabel tip1 = new JLabel("One Tip: Recommanded add parenthesis to negative number.");
        //texts on the dialog window

        error1.setHorizontalAlignment(SwingConstants.CENTER);
        self.setHorizontalAlignment(SwingConstants.CENTER);
        error1.setForeground(Color.RED);
        error1.setFont(new Font("Arial", Font.BOLD,32));
        error.setResizable(false);
        intro.setResizable(false);
        error.setAlwaysOnTop(true);
        intro.setAlwaysOnTop(true);
        //settings of the dialog

        intro.setLayout(new TableLayout(3,1));
        intro.add(self);
        intro.add(tip1);
        //add the dialog

        intro.setSize(new Dimension(400,200));
        error.setSize(new Dimension(400,200));
        error.add(error1);
        //set the dialog size

        error.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                resultText.setText("");
                read_in.setText("0");
            }
        });

        intro.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                read_in.setText("0");
                resultText.setText("");
            }
        });

        //override method for the windowlistener, when the
        //dialog window closed, reset the textfield

        intro.setVisible(true);
        error.setVisible(false);
        error.setTitle("ERROR");
        error.setLocationRelativeTo(null);
        intro.setLocationRelativeTo(null);

        //decide whether to show the dialog

        this.setTitle("GUICalculator");


        this.setLocation(500,500);
        this.setSize(520,680);
        this.setLayout(new TableLayout(10,4));
        //Modify the Size and layout of the applet

        resultText.setHorizontalAlignment(JTextField.RIGHT);
        resultText.setEditable(false);
        resultText.setBackground(Color.white);
        //Modify output textfield, disable its editing

        read_in.setHorizontalAlignment((JTextField.RIGHT));
        read_in.setFont(new Font("TimesRoman", Font.ITALIC,27));
        //Modify the font of the input textfield

        JLabel title = new JLabel("A Simple Calculator");
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title,"gridwidth = 4 height = 45");
        title.setFont(new Font("Courier", Font.BOLD,30));
        title.setForeground(Color.darkGray);
        //Create a title

        resultText.setFont(new Font("TimesRoman", Font.PLAIN,35));
        //modify the output textfield

        add(resultText,"gridwidth = 4 height = 77");
        add(read_in,"gridwidth = 4 height = 65");
        String BUTTON_SIZE2 = "120";
        String BUTTON_SIZE = "70";
        String constraint1 = "width=" + BUTTON_SIZE2 + " height=" + BUTTON_SIZE;
        //add the input and output textfield, and set it with a specific size

        for (int i=0;i<command_thing.length;i++){
            command[i] = new JButton(command_thing[i]);
            command[i].setFont(new Font("Arial", Font.PLAIN, 20));
            command[i].setForeground(Color.red);
            command[i].addActionListener(this);
            add(command[i],constraint1);
        }

        for (int i=0;i<key_thing.length;i++){
            key[i] = new JButton(key_thing[i]);
            key[i].setFont(new Font("Arial", Font.PLAIN, 20));
            key[i].setForeground(Color.blue);
            key[i].addActionListener(this);
            add(key[i],"width=120 height=70");
        }
        //add all the buttons,and also add actionlistener

        key[21].addKeyListener(this);

        add(new JLabel("Precision"));

        int defult_val = 4;
        String defult_val_str = defult_val + "";
        prec_Slider = new JSlider(0,10,defult_val);
        prec_Slider.addChangeListener(this);
        add(prec_Slider,"gridwidth =2");
        //add the precision slider to the applet, and modify it.

        prec_tf.setText(defult_val_str);
        prec_tf.setEditable(false);
        prec_tf.setBackground(Color.white);
        add(prec_tf);
        //add the precision textfield, which displays the number of decimals
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();
        if (e.getActionCommand()=="->"){
            handle_backspace();
        }
        //actions when backspace button is hit

        else if(e.getActionCommand()=="C"){
            prec_Slider.setValue(4);
            prec_Slider.setValueIsAdjusting(true);
            read_in.setText("0");
            resultText.setText("");
        }
        //actions when clean button is hit
        //which default all settings

        else if(e.getActionCommand()=="=")
        {
            handle_equal();
        }
        //actions when equal button is hit

        else if(e.getActionCommand()=="1/x")
        {
            if(read_in.getText().equals("0"))
            {
                read_in.setText("");
                read_in.setText(read_in.getText()+"1/");
            }
            else read_in.setText(read_in.getText()+"1/");
        }
        //actions when the reciprocal button is hit

        else if(e.getActionCommand()=="+/-")
        {
            read_in.setText("-"+read_in.getText());
        }
        //when +/- is hit, add a negative sign to the front of the expression

        else
        {
            num_and_operators(label);
        }
        //typing the numbers or operators
    }

    public void handle_backspace(){
        read_in.setText(read_in.getText().substring
                (0,read_in.getText().length()-1));
        if(read_in.getText().equals("")) read_in.setText("0");
    }
    //use a substring method, to reduce the length of string.
    //and reset the input field when it is the last digit.

    public void handle_equal()
    {
        String true_result = compute(read_in.getText()).replaceAll(",",".");

        resultText.setText(true_result);
    }
    //Call the computation method, and the display the returned result

    public void num_and_operators(String key)
    {
        if(read_in.getText().equals("0"))
            read_in.setText(key);
        else read_in.setText(read_in.getText() + key);
    }
    //when digit or operator button hit, put it on to the input field.

    public void stateChanged(ChangeEvent e) {
        int prec_slider_value = prec_Slider.getValue();
        prec_tf.setText(prec_slider_value+"");
        resultText.setText(compute(read_in.getText()).replaceAll(",","."));
    }
    //Enables the changing of precision,
    //when the number of precision changes, display a new result on output field.

    public String compute(String s)
    {
        Stack<String> Operators = new Stack<String>();  //Create new stack to hold operators when transfering to post-fix
        Queue<String> input = new Queue<String>();      //Create new queue to hold input data
        Queue<String> output = new Queue<String>();		//Create new queue to hold output data
        Stack<String> calculation = new Stack<String>(); //Create new Stack to hold operators when doing calculations
        DecimalFormat df=new DecimalFormat("#0.0000000000"); //use decimalformat to set the display of the result
        df.setMaximumFractionDigits(prec_Slider.getValue()); //set the max decimal to the value of Slider

        String infix = s.replaceAll(" +", "").replaceAll("x","*").replaceAll("%","*0.01");
        //elminate spaces inbetween expression
        //replace x with a * which the computer can read
        //repalce the percentage sign with a mutiplication of 0.01

        StringTokenizer st = new StringTokenizer(infix,"()+-*/^",true);     //Use a Stringtokenizer to split
        while(st.hasMoreTokens())                                          //the readin string by operators
        {                                                                  //"()+_*/",and use a while loop
            input.enqueue(st.nextToken());                                 //to let all data enqueue the
        }

        while(!input.isEmpty())         //Start the ShuntingYard algorithm
        {                               //by first determine if the input queue is empty
            String token = input.dequeue();        //assign a string variable every dequeued element
            if(isNumeric(token)) output.enqueue(token);//Determine if a token is a numerical value
                //if true, then enqueue it to the output queue
            else if (token.equals("*")||token.equals("/")||token.equals("+")||                     //Determine if token is
                    (token.equals("-")) ||token.equals("(")||token.equals(")")||token.equals("^"))   //one of six operators
            {
                String operator = new String(token);            //Assign a new variable String to the
                String top = Operators.getTop();                //operators, a varible to top of stack
                if((token.equals("+")&&(input.getfront().equals("-")))||(token.equals("-")&&(input.getfront().equals("-")))||
                        (token.equals("*")&&(input.getfront().equals("-")))||(token.equals("^")&&(input.getfront().equals("-")))||
                        (token.equals("/")&&(input.getfront().equals("-")))) output.enqueue("0");
                //Add a 0 into the output queue,make it into a "0-x" form

                if(token.equals("-")&&(output.isEmpty())&&(Operators.isEmpty())) output.enqueue("0");
                //Add a 0 into the output queue,when the unary minus is at the mostfront of the input queue
                if(token.equals(")"))
                {
                    while((Operators.getTop()!=null)&&(!(Operators.getTop().equals("("))))
                    {                                           //if the token is a right parethesis, and
                        output.enqueue((Operators.getTop()));   //top of the stack is not left parethesis
                        Operators.pop();                        //then move the top_operator to the output
                    }                                           //queue.
                    Operators.pop();                            //also pop off the parethesis
                }
                else {
                    if (token.equals("(")) {                     //If the token is a left parethesis, then
                                                                    //directly push it into the stack
                        if(input.getfront().equals("-")) output.enqueue("0");
                        Operators.push(operator);

                    } else {
                        while ((Operators.getTop() != null) && (precedence(top) >= precedence(operator)))
                        {                                           //For other operators, first determine the
                            output.enqueue(Operators.getTop());     //precedence by comparing the precedence of
                            Operators.pop();                        //the top_operator and the incoming operator
                            top = Operators.getTop();               //reset the top_operator
                        }
                        Operators.push(operator);                   //also push the incoming operator into the stack
                    }
                }
            }
            else {
                out.println("Error");
            }
        }
        while(!Operators.isEmpty())                             //move all remaining operators to the output queue
        {                                                       //enqueue first, then pop off
            String operator=Operators.getTop();
            output.enqueue(operator);
            Operators.pop();
        }

        double op1=0, op2=0;								//define two double variable to do the calculation
        while(!output.isEmpty())
        {
            double temp = 0;								//temp is used to hold the sum of two operand
            String place = output.dequeue();				//Assign a new string variable for dequeued operands

            if (isNumeric(place)) calculation.push(place);	//if the string is a number, push it into the calculation stack
            else if((place.equals("*")||place.equals("/")||place.equals("+")||  //Determine if token is one of the binary operators
                    place.equals("-")||place.equals("^")))
            {
                op1 = Double.parseDouble(calculation.pop());	//Get the value of the string,and make it to string
                op2 = Double.parseDouble(calculation.pop());

                switch(place)									//Use a switch statement for different operators
                {
                    case "+": temp = op2+op1;
                        break;
                    case "-": temp = op2-op1;
                        break;
                    case "*": temp = op2*op1;
                        break;
                    case "/": temp = op2/op1;
                        break;
                    case "^": temp = Math.pow(op2,op1);
                }


                String final_value = String.valueOf(temp);
                calculation.push(final_value);

                if (String.valueOf(temp).equals("Infinity")) //if the denominator is 0, then give a error msg
                {
                    error.setVisible(true);
                    return "Error!";
                }
            }
        }

    return df.format(Double.parseDouble(calculation.getTop())); //return the result, in a format that been defiened before
    }

    public static int precedence(String operator){              //method to determine the precedence of tokens
        if(operator.equals("("))
            return 1;
        else if (operator.equals("+")|| operator.equals("-"))
            return 2;
        else if (operator.equals("*")|| operator.equals("/")||operator.equals("^"))
            return 3;
        else return 0;
    }

    public static boolean isNumeric(String infix){              //method to determine whether it is a number
        String reg = "^[0-9]+(.[0-9]+)?$";
        return infix.matches(reg);
    }
}
