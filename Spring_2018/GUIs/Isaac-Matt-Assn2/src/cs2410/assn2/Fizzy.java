package cs2410.assn2;

/**
 * This class implements the popular "FizzBuzz" coding exercise.
 * Numbers 1 through 100 are printed. If the number is a multipe of 3, "Fizz" is printed instead.
 * If the number is a multiple of 5, "Buzz" is printed instead.
 *
 * @author Matt Isaac
 * @version 2
 */
public class Fizzy{
    /**
     * Begins at 0, is used to count up in for loop.
     */
    private static int counter;

    /**
     * Main start method
     * @param args
     */
    public static void main(String[] args){
        for(counter = 1; counter <= 100; counter++){
            if(isFizz(counter)){
                System.out.println("Fizz");
            }
            else if(isBuzz(counter)){
                System.out.println("Buzz");
            }
            else{
                System.out.println(counter);
            }
        }
    }

    /**
     * Returns true if value is a multiple of 3 and false otherwise
     * @param val integer to be checked
     * @return true if value is a multiple of 3 and false otherwise
     */
    private static boolean isFizz (int val){
        if(val % 3 == 0){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Returns true if value is a multiple of 5 and false otherwise
     * @param val integer to be checked
     * @return true if value is a multiple of 5 and false otherwise
     */
    private static boolean isBuzz (int val){
        if(val % 5 == 0){
            return true;
        }
        else{
            return false;
        }
    }
}

