import jdk.jshell.execution.FailOverExecutionControlProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageFilter;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MedianFilter {
    private BufferedImage filteredImage;
    private BufferedImage[] images;
    private File[] files;


    public MedianFilter(String[] fileNames){

        int length = fileNames.length;

        files = new File[length];
        images = new BufferedImage[length];

        for(int i = 0; i < length; i++){
            files[i] = new File(fileNames[i]);
            images[i] = readImage(files[i]);
        }
    }

    public void printBufferedImages(){
        for(BufferedImage img : images){
            System.out.println(String.format("%s", img.toString()));
        }
    }

    public BufferedImage readImage(File pic){
        BufferedImage img = null;
        if(!pic.exists()){
            return null;
        }
        try{
            if(pic.canRead()){
                img = ImageIO.read(pic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    public BufferedImage filterImage(){
        BufferedImage img = new BufferedImage(images[0].getWidth(), images[0].getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                int color = getMedian(x, y);
                img.setRGB(x, y, color);
            }
        }

        return img;
    }

    public int getMedian(int x, int y){
        int rgb = 0;

        ArrayList<Integer> pixel = new ArrayList<Integer>();
        for(BufferedImage image : images){
            pixel.add(image.getRGB(x, y));
        }
        Collections.sort(pixel);
        rgb = pixel.get(pixel.size() / 2);
        return rgb;
    }


}
