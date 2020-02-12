
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
Near Miss Calculator
Main.java
No external files
Creates no external files
cwdh4@mail.umsl.edu, bjdq88@mail.umsl.edu, cpfgv@mail.umsl.edu
CMPSCI4500-002
Charles Wyatt, Benjamin Donahue, Chaitali Patel
02/12/2020
This program takes input from the user (n for the power to raise x, y, z, and k for the number of iterations the program runs)
and calculates the near misses based on Fermat's last theorem. It then displays the results and how close the answer is to the
formula x^n + y^n = z^n in percentages.
No outside resources were used in the making of this program (for better or worse).
**/

public class Main {

//This is the main function
//First we initialize out variables and introduce our calculator
    public static void main(String[] args){
        System.out.println("Welcome to near miss calculator");
        double n,k;
        double z;
        double sum;
        double miss = 0;
        double relativeMiss=0;
        String Exit;
        Map valueMap= new HashMap();
        Scanner scan;
		//In order for our outputs to look orderly, we need to format the decimal output
        DecimalFormat formatter = new DecimalFormat("#.##############");

        do{
            int resultCount = 0;
			//Here we ask our user to enter the power by which we raise out variables x and y
            System.out.println("Please enter an integer value for n greater than 2, but less than 12");
		//However, we need to sanitize our inputs so the user can't enter number out of range
		//Nor do we want them entering characters or strings...
		//So we ask them to enter a valid input until they finally do
            while(true){
                try{
                    scan = new Scanner(System.in);
                    n = scan.nextInt();
                    if(n <= 2){
                        System.out.println("The input was invalid.Please enter a integer greater than 2 ");
                    }else if(n > 11){
                        System.out.println("The input was invalid.Please enter a integer less than than 12 ");
                    }else{
                        break;
                    }
                }catch(Exception e){
                    System.out.println("The input was invalid.Please enter a integer greater than 2 ");
                }
            }
			
		//Here we ask the user for the upper limit for how many times the algorithm is run
		//And of course make sure the inputs are valid
            System.out.println("Please enter an integer value for k greater than 10, but less than 1000");
            while(true){
                try{
                    scan = new Scanner(System.in);
                    k = scan.nextInt();
                    if(k <= 10 || k > 1000 ){
                        System.out.println("The input was invalid.Please enter a integer such that 10<k<1000 ");
                    }else{
                        break;
                    }
                }catch(Exception e){
                    System.out.println("The input was invalid.Please enter a integer such that 10<k<1000 ");
                }
            }
            for(int i=10; i <=k; i++){
                valueMap.put(i,Math.pow(i, n));
            }
            double inverseN;
            for(int x=10; x<=k ; x++){
                double localRelativeMiss;
                for(int y=10; y<=k ; y++){
                    sum = Math.pow(x, n) + Math.pow(y, n);
                    inverseN = 1.0/n;
                    z =  Math.floor(Math.pow(sum, inverseN));
                    if(sum - Math.pow(z, n) > 0){
                        miss = sum - Math.pow(z, n);
                    }else{
                        miss = Math.pow(z+1, n) -  sum;
                    }
                    //System.out.print("%n" + sum);
                    localRelativeMiss = miss/sum;
                    if(localRelativeMiss<relativeMiss || relativeMiss==0){
                        relativeMiss = localRelativeMiss;
                        System.out.print("x="+(int)x);
                        System.out.print(", ");
                        System.out.print("y="+(int)y);
                        System.out.print(", ");
                        System.out.print("z="+(int)z);
                        System.out.print(", ");
                        System.out.print("Actual Miss="+(int)miss);
                        System.out.print(", ");
                        System.out.println("Smallest Relative Miss="+ formatter.format(localRelativeMiss*100) + "%");//localRelativeMiss*100 + "%"); //
                        resultCount++;
                    }
                    
                }
            }
            if(resultCount == 0){
                System.out.println("No new results.");
            }else{
                System.out.println("Review the results!");
            }
            System.out.println("Would you like to try another set? y/n");
            scan = new Scanner(System.in);
            Exit = scan.nextLine();
            Exit = Exit.toLowerCase();
            do{
                if("n".equals(Exit) || "y".equals(Exit)){
                    break;
                }else{
                    System.out.println("The input was invalid.Please enter character either 'y' or 'n'.");
                    Exit = scan.nextLine();
                }
            }while(true);
            if("n".equals(Exit) || "N".equals(Exit)){
                break;
            }
        }while(true);
    }
}