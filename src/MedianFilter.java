import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MedianFilter {
    private BufferedImage filteredImage;
    private BufferedImage[] images;
    private File[] files;


    public MedianFilter(String[] fileNames) {

        int length = fileNames.length;


        files = new File[length];
        images = new BufferedImage[length];

        for (int i = 0; i < length; i++) {
            files[i] = new File(fileNames[i]);
            try{
                images[i] = readImage(files[i]);
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        filteredImage = new BufferedImage(images[0].getWidth(), images[0].getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public void writeImage(String str) throws IOException {
        try {
            File outputfile = new File(str);
            ImageIO.write(filteredImage, "jpg", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printBufferedImages() {
        for (BufferedImage img : images) {
            System.out.println(String.format("%s", img.toString()));
        }
    }

    public BufferedImage readImage(File pic) throws IOException{
        BufferedImage img = null;
        try {
            if (!pic.exists()) {
                throw new IOException();
            }
            if (pic.canRead()) {
                img = ImageIO.read(pic);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    public BufferedImage removeNoise() {
        for (int x = 0; x < filteredImage.getWidth(); x++) {
            for (int y = 0; y < filteredImage.getHeight(); y++) {
                ArrayList<Integer> pixels = getPixels(x, y);
                int color = getMedianValue(pixels);
                filteredImage.setRGB(x, y, color);
            }
        }
        return filteredImage;
    }


    public ArrayList<Integer> getPixels(int x, int y) {

        ArrayList<Integer> pixels = new ArrayList<Integer>();
        for (BufferedImage image : images) {
            pixels.add(image.getRGB(x, y));
        }

        return pixels;
    }

    public int getMedianValue(ArrayList<Integer> pixels){
        int rgb = 0;
        Collections.sort(pixels);
        rgb = pixels.get(pixels.size() / 2);
        return rgb;
    }

    public int getHeight() {
        if (filteredImage == null)
            return -1;

        return filteredImage.getHeight();
    }

    public int getWidth() {
        if (filteredImage == null)
            return -1;

        return filteredImage.getWidth();
    }


}
