package cs2410.assn1;

/**
 * This class prints out my name and a brief message.
 *
 * @author Matt Isaac
 * @version 1
 */

public class Main {

    /**
     * This is the main start method.
     * @param args
     */
    public static void main(String[] args) {
        printName();
    }

    /**
     *  This method prints my first name, last name, and a brief message.
     */
    private static void printName(){

        /**
         * fName: First Name
         * lName: Last Name
         */

        String fName = "Matt";
        String lName = "Isaac";

        System.out.println(fName + " " + lName + " was here.");
    }
}