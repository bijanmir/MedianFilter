import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App {


    public static void main(String[] args) {
        String[] files = {
                "veg1.jpg",
                "veg2.jpg",
                "veg3.jpg",
                "veg4.jpg",
                "veg5.jpg",
                "veg6.jpg",
                "veg7.jpg",
                "veg8.jpg",
        };

        MedianFilter mf = new MedianFilter(files);

        mf.printBufferedImages();



        //BufferedImage bi= mf.filterImage();


//        try {
//            File outputfile = new File("FilteredImage.jpg");
//            ImageIO.write(bi, "jpg",  outputfile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }



}
