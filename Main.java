
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
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
        DecimalFormat formatter = new DecimalFormat("#.##############");

        do{
            int resultCount = 0;
            System.out.println("Please enter an integer value for n greater than 2, but less than 12");
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