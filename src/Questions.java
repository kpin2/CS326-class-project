import java.util.Random;
import java.util.Scanner;

public class Questions {

    static char grade;
    private int a,b;
    static char operation;
    static int answer;
    private Random ran;
    static char [] ops = {'+','-','*','/'};
    private char actualOp;

    public Questions(char grade)
    {
        ran = new Random();
        if(grade=='k')
        {
            a = ran.nextInt(21);
            b = ran.nextInt(21);
            operation = ops[0];
        }

        else if(grade == '1' || grade=='2')
        {
            a= ran.nextInt(101);
            b= ran.nextInt(101);
            operation = ops[ran.nextInt(2)];
        }

        else if(grade=='3')
        {
            a=ran.nextInt(5001);
            b=ran.nextInt(5001);
            operation = ops[ran.nextInt(3)];
        }

        else if(grade=='4')
        {
            a=ran.nextInt(100001);
            b=ran.nextInt(100001);
            operation = ops[ran.nextInt(4)];
        }


        actualOp= operation;
        if(actualOp == '+') answer = a + b;
        if(actualOp == '-') answer = a - b;
        if(actualOp == '*') answer = a * b;

        if(actualOp == '/')
        {
            if(b>a)
                b=ran.nextInt(a);//this makes b smaller than a

            //TODO: We need integer values for division

            answer = a / b;
        }

    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("What grade are you in?");
        grade = sc.next().charAt(0);
        Questions questions = new Questions(grade);
        System.out.println(questions.getQuestion());
        int userAnswer = sc.nextInt();

        if(userAnswer == questions.getAnswer())
            System.out.println("correct!");
        else
            System.out.println("incorrect");













  /*


     System.out.println("What is " + a + operation + b + " equal to?\n");

     int userAnswer = sc.nextInt();






     if( userAnswer == answer) System.out.println("You got this right");
     else System.out.println("incorrect!");





      System.out.println("The answer is " + answer);
     */

    }


    public String getQuestion()
    {
        return "What is " + a + operation + b + " equal to?\n";
    }
    public String getTFQuestion()
    {
        return " " + a + operation + b + " is equal to  ";
    }

    public int getAnswer()
    {
        return answer;
    }

}

