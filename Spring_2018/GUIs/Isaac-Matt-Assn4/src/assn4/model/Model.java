package assn4.model;

import javafx.scene.image.Image;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {

    /*public static void main(String[] args) {
        Model mymodel = new Model();
        mymodel.readFile();
    }*/

    /**
     * Basic object - consists of a title and URL
     */
    public static class ImageObject{
        private String title;
        private String url;
        private Image img;

        /**
         * Constructor for ImageObject
         * @param newTitle Image title
         * @param newUrl Image URL
         */
        public ImageObject(String newTitle, String newUrl){
            title = newTitle;
            url = newUrl;
            img = null;

        }

        /**
         * Default Constructor
         */
        public ImageObject(){
            title = null;
            url = null;
            img = null;
        }

        /**
         * Set title of Image
         * @param inputTitle title of image
         */
        public void setTitle(String inputTitle){
            title = inputTitle;
        }

        /**
         * Set URL of Image
         * @param inputUrl url for accessing image
         */
        public void setUrl(String inputUrl){
            url = inputUrl;
        }

        /**
         * Image Object itself.
         * @param inputImg image object
         */
        public void setImg(Image inputImg){
            img = inputImg;
        }

        /**
         * Getter for image title
         * @return title
         */
        public String getTitle(){
            return title;
        }

        /**
         * Getter for image URL
         * @return String url
         */
        public String getUrl(){
            return url;
        }

        /**
         * Getter for Image object
         * @return Image object
         */
        public Image getImg(){
            return img;
        }

    }

    /**
     * Contstructor for Model.
     * Initializes currentImage index to 0
     * Createds empty ArrayList of ImageObjects
     */
    public Model(){
        currentImage = 0;
        imageList = new ArrayList<>();
    }

    //----------------
    //Member Variables
    //----------------

    /**
     * index to keep track of which image is currently showing
     */
    private int currentImage;

    /**
     * imageList keeps a list of ImageObjects.
     */
    private ArrayList<ImageObject> imageList;

    //----------------
    // Member Methods
    //----------------

    /**
     * getter for imageList
     * @return imageList
     */
    public ArrayList<ImageObject> getImageList(){
        return imageList;
    }

    /**
     * getter for current image index
     * @return currentImage
     */
    public int getCurrentImage(){
        return currentImage;
    }

    /**
     * Used by prevImage() and nextImage() in Control to set the new
     * index.
     * @param inputNum new index.
     */
    public void setCurrentImage(int inputNum){
        currentImage = inputNum;
    }

    /**
     * reads in URLs that exist in data file and stores them in
     * imageList
     */
    public void readFile(){
        Scanner fileInput = null;
        try{
            fileInput = new Scanner(new FileReader("data/images.data"));
        } catch (FileNotFoundException error) {
            error.printStackTrace();
            System.out.println("File not found.");
        }

        String tempString;
        String[] tempArray;
        ImageObject imgObj;
        while(fileInput.hasNext()){
            imgObj = new ImageObject();
            tempString = fileInput.nextLine();
            tempArray = tempString.split(" ");
            imgObj.setUrl(tempArray[0]);
            imgObj.setTitle(tempArray[1]);
            imageList.add(imgObj);
        }
    }

    /**
     * Writes contents of imageList to data file
     */
    public void writeFile(ArrayList<ImageObject> imgLst){
        PrintWriter fileOutput = null;

        try{
            //working here... neet to get it to write to file correctly (new line, not repeat ones already in there).
            fileOutput = new PrintWriter(new FileOutputStream("data/images.data", false));
        } catch(FileNotFoundException error) {
            error.printStackTrace();
        }
        for(ImageObject obj : (imgLst)){
            fileOutput.printf("%s %s\n", obj.getUrl(), obj.getTitle());
        }
        fileOutput.close();
    }
}
