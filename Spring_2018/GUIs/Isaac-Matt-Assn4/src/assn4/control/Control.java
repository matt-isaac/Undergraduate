package assn4.control;

import assn4.model.Model;
import javafx.scene.image.Image;

/**
 * Control class - Contains logic for interacting with data
 */
public class Control {

    /*public static void main(String[] args){
        Control control = new Control();
        Model.ImageObject nextImg = control.nextImage(control.model);
        nextImg = control.nextImage(control.model);
        nextImg = control.nextImage(control.model);
    }*/

    /**
     * Instance of model that is created when control is created
     */
    private Model model;
    /**
     * noImg is a ImageObject that is displayed when no images are available
     */
    private Model.ImageObject noImg = new Model.ImageObject(null, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/600px-No_image_available.svg.png");

    /**
     * Constructor for control
     */
    public Control(){
        model = new Model();
        model.readFile();
        Image tmpImg = new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/600px-No_image_available.svg.png");
        noImg.setImg(tmpImg);
    }

    /**
     * Advance to next image
     * @param imgs Model object
     * @return returns next image to view
     */
    public Model.ImageObject nextImage(Model imgs){
        int listSize = imgs.getImageList().size();
        int newIndex;
        if(imgs.getCurrentImage() == listSize - 1){
            newIndex = 0;
        }
        else{
            newIndex = imgs.getCurrentImage() + 1;
        }
        imgs.setCurrentImage(newIndex);
        return imgs.getImageList().get(newIndex);
    }

    /**
     * Return to previous image
     * @param imgs Model object
     * @return returns ImageObject to view
     */
    public Model.ImageObject prevImage(Model imgs){
        int currentIndex = imgs.getCurrentImage();
        int listSize = imgs.getImageList().size();
        int newIndex;
        if(currentIndex == 0){
            newIndex = listSize - 1;
        } else {
            newIndex = currentIndex - 1;
        }
        imgs.setCurrentImage(newIndex);
        return imgs.getImageList().get(newIndex);
    }

    public Model getModel(){
        return model;
    }

    /**
     * Add image to ArrayList
     * @param title user input title
     * @param url user input url
     * @return returns ImageObject to view
     */
    public Model.ImageObject addImage(String title, String url){
        Model.ImageObject newImg = new Model.ImageObject(title, url);
        int newIndex;
        if(model.getCurrentImage() == model.getImageList().size() - 1){
            newIndex = 0;
        } else{
            newIndex = model.getCurrentImage() + 1;
        }
        model.getImageList().add(newIndex, newImg);
        return newImg;
    }

    /**
     * Delete current image
     * @return returns next available image
     */
    public Model.ImageObject delImage() {
        int newIndex;
        if(model.getImageList().size() == 1){
            model.getImageList().remove(model.getCurrentImage());
            return noImg;
        }
        if (model.getCurrentImage() == model.getImageList().size() - 1) {
            newIndex = 0;
        } else{
            newIndex = model.getCurrentImage();
        }
        model.getImageList().remove(model.getCurrentImage());
        model.setCurrentImage(newIndex);
        return model.getImageList().get(newIndex);
    }

    /**
     * Save to file and quit program
     */
    public void quit(){
        model.writeFile(model.getImageList());
        System.exit(1);
    }

}
