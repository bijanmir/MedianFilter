import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class MedianFilter {
    private BufferedImage filteredImage;
    private BufferedImage[] images;

    public MedianFilter(String[] imageInputFileNames){
        for(String image : imageInputFileNames)
            System.out.println(image);
    }

    public BufferedImage readImage(File file){

    }

    public int getMedianValue(ArrayList<Integer> pixels){
        return -1;
    }

    public int writeImage(String outputFileName){
        return -1;
    }

    // public int getHeight() --> return height(Y-DIMENSION) of filteredImage
    // public int getWidth()  --> returns width(X-DIMENSION) of filteredImage
}
