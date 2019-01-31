/*package cs2410.demo;

import javafx.scene.control.Button;
//import javafx.scene.media.*;
import java.sql.Array;
// import java.lang.reflect.Array; // you can't tell which Array class is being used as the developer.

*//**
 * top level public class. only one of these.
 * Top-level class must be public. If it was private, no one can use it.
 * filename and top-level public class name must match.
 *//*
public class demo {
    public class Demo{
        public Demo(){
            Button btn = new Button();
            int x = Math.abs(-5);
        }
    }

    private class innerClass{
    }
}*/

package cs2410.demo;

import java.util.Scanner;

public class demo{

    private Scanner scan = new Scanner(System.in); // Same result as line 55

    public demo(){
/* - 1/19

        String str1 = "Hello";
        String str2 = new String("Hi there");
        String str3 = ""; //null string
        String str4; // null object. str4 == null -> true

        System.out.println(str1.length()); //
        // System.out.println(str1.charAt(2));

        String str5 = "5";
        String str6 = "6";
        System.out.println("Sum: " + (Integer.parseInt(str5) + 10));
        System.out.println("Compare: " + str1.compareTo(str6)); // Compares CONTENTS of string
        System.out.println("Equals: " + str1.equals(str2)); // Compares CONTENTS of the string
        System.out.println("== : " + (str1 == str2)); // Compares values (ADDRESS - not contents)

*/


        System.out.print("Enter a word: ");
        //scan = new Scanner(System.in); // Same result as line 31.
        String tmpWord = scan.next();
        System.out.println("You entered: " + tmpWord);

        System.out.print("Enter a number: ");
        int tmpNum = scan.nextInt();
        System.out.println("You entered: " + tmpNum);


    }

    public static void main(String[] args) {
        new demo();
    }

}