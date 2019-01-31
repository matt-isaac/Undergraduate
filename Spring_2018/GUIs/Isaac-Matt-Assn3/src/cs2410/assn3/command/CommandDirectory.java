package cs2410.assn3.command;

import cs2410.assn3.directory.Directory;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;


/**
 * Command Directory class uses Directory class (and nested Entry class).
 * Provides user with a way to interact with the directory through the
 * command line.
 *
 * @author Matt Isaac
 * @version 1
 */
public class CommandDirectory
{
    /**
     * Main start method. Calls CommandDirectory constructor.
     * @param args Default arguments
     */
    public static void main(String[] args)
    {
        new CommandDirectory();
    }

    /**
     * This is where most of the "work" happens.
     * Creates instance of Directory.
     */
    private CommandDirectory()
    {
        Directory movieDirectory = new Directory();

        int choice = 0;

        while(choice != 4){
            System.out.println("");
            System.out.println("1. List directory contents");
            System.out.println("2. Add movie to directory");
            System.out.println("3. Display average rating");
            System.out.println("4. Quit program");

            Scanner input;
            input = new Scanner(System.in);
            choice = input.nextInt();

            if(choice == 1){
                listContents();
            } else if (choice == 2) {
                addMovie();
                movieAdded(Directory.movieList.get(0));
            } else if (choice == 3) {
                System.out.printf("The average rating is %f stars", Directory.averageRating());
            }
        }
    }

    /**
     * This method is called when option 2 is selected by the user.
     * A new instance of Entry is created and added to the Directory. User
     * is able to specify movie title, rating, review, etc.
     */
    private void addMovie(){
        String inputTitle;
        int inputYear;
        String inputRating;
        int inputStars;
        int inputDateYear;
        int inputDateMonth;
        int inputDateDay;

        Directory.Entry entry = new Directory.Entry();

        Scanner input = new Scanner(System.in);

        System.out.print("Enter movie title: ");
        inputTitle = input.nextLine();

        System.out.print("Enter the release year: ");
        inputYear = input.nextInt();

        System.out.print("Enter the rating: ");
        inputRating = input.next();

        System.out.print("Enter the number of stars: ");
        inputStars = input.nextInt();

        System.out.print("Enter the year of date last watched: ");
        inputDateYear = input.nextInt();

        System.out.print("Enter the month of date last watched: ");
        inputDateMonth = input.nextInt();

        System.out.print("Enter the day of the date last watched: ");
        inputDateDay = input.nextInt();

        entry.setTitle(inputTitle);
        entry.setYear(inputYear);
        entry.setRating(inputRating);
        entry.setStars(inputStars);
        entry.setDateYear(inputDateYear);
        entry.setDateMonth(inputDateMonth);
        entry.setDateDay(inputDateDay);

        Directory.movieList.add(0,entry);
        entry.writeToFile();
    }

    /**
     * Message printed when user adds movie to directory.
     * @param e Movie Entry
     */
    private void movieAdded(Directory.Entry e){
        System.out.printf("The following movie has been entered: \n %s \n %s %d %d %d/%d/%d",
                e.getTitle(), e.getRating(), e.getYear(), e.getStars(), e.getDateMonth(), e.getDateDay(), e.getDateYear());
    }

    /**
     * Prints contents of directory when user selects option 1.
     */
    private void listContents(){
        Scanner fileInput = null;

        try {
            fileInput = new Scanner(new FileReader(Directory.fileName));
        } catch(FileNotFoundException error) {
            error.printStackTrace();
        }
        while (fileInput.hasNext()){
            System.out.println(fileInput.nextLine());
        }
    }

}