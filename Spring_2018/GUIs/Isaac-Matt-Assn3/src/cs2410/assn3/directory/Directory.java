package cs2410.assn3.directory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Directory class stores ArrayList of movie entries (see Entry class within Directory class)
 *
 * @author Matt Isaac
 * @version 1
 */

public class Directory {

    /**
     * Default constructor
     */
    public Directory(){
        movieList = new ArrayList<Entry>(1);
    }

    /**
     * Calculates average rating of all movies in movieList
     * @return float of average rating of movies
     */
    public static float averageRating(){
        Scanner fileInput = null;

        try {
            fileInput = new Scanner(new FileReader(Directory.fileName));
        } catch(FileNotFoundException error) {
            error.printStackTrace();
        }

        String line;
        String[] wordArray;
        List<Integer> averages = new ArrayList<Integer>();

        StringBuilder list = new StringBuilder();
        while (fileInput.hasNext()){
            line = fileInput.nextLine();
            wordArray = line.split(" +",10 );
            int indexOfStars = Arrays.asList(wordArray).indexOf("stars");
            int index = Arrays.asList(wordArray).indexOf("stars") - 1;
            averages.add(Integer.parseInt(wordArray[Arrays.asList(wordArray).indexOf("stars") - 1]));
        }

        int sum = 0;
        for(int i = 0; i < averages.size(); i++){
            sum = sum + averages.get(i);
        }
        return (float)sum/(float)averages.size();

    }

    /**
     * Array of Entries (see Entry class). Used to quickly access information
     * about movies.
     */
    public static ArrayList<Entry> movieList;

    /**
     * Location of file to store directory data
     */
    public static final String fileName = "data/cs2410-directory.data";


    /**
     * Entry class contains identifying information on each movie in directory.
     * See variables for information stored.
     */
    public static class Entry {

        /**
         * Default constructor.
         * Initializes title, year, rating, stars, and date variables to 0 or null
         */
        public Entry(){
            title = null;
            year = 0;
            rating = null;
            stars = 0;
            dateYear = 0;
            dateDay = 0;
            dateMonth = 0;
        }

        /**
         * Writes a movie entry to directory data file.
         */
        public void writeToFile(){
            PrintWriter fileOutput = null;

            try{
                fileOutput = new PrintWriter(new FileOutputStream(fileName, true));

            } catch (FileNotFoundException error) {
                error.printStackTrace();
            }

            fileOutput.printf("%-20s %-10s %-4d %d stars %d/%d/%d\n",
                    getTitle(), getRating(), getYear(), getStars(), getDateMonth(), getDateDay(), getDateYear());
            fileOutput.close();
        }

        // Getters and setters - self explanatory.

        public void setTitle(String inputTitle) {
            title = inputTitle;
        }

        public void setYear(int inputYear) {
            year = inputYear;
        }

        public void setRating(String inputRating) {
            rating = inputRating;
        }

        public void setStars(int inputStars) {
            stars = inputStars;
        }

        public void setDateYear(int inputDateYear){
            dateYear = inputDateYear;
        }

        public void setDateMonth(int inputDateMonth){
            dateMonth = inputDateMonth;
        }

        public void setDateDay(int inputDateDay){
            dateDay = inputDateDay;
        }

        public String getTitle() {
            return title;
        }

        public int getYear() {
            return year;
        }

        public String getRating() {
            return rating;
        }

        public int getStars() {
            return stars;
        }

        public int getDateYear(){
            return dateYear;
        }

        public int getDateMonth(){
            return dateMonth;
        }

        public int getDateDay(){
            return dateDay;
        }


        // Variables

        /**
         * Movie title
         */
        private String title;

        /**
         * Year of release
         */
        private int year;

        /**
         * Rating of movie
         */
        private String rating;

        /**
         * Review (number of stars)
         */
        private int stars;

        /**
         * Year portion of date last watched (eg. 2005)
         */
        private int dateYear;

        /**
         * Month portion of date last watched (1 - 12)
         */
        private int dateMonth;

        /**
         * Day portion of date last watched (eg. 25)
         */
        private int dateDay;
    }
}
