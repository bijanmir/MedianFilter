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


    public MedianFilter(String[] fileNames) {

        int length = fileNames.length;


        files = new File[length];
        images = new BufferedImage[length];

        for (int i = 0; i < length; i++) {
            files[i] = new File(fileNames[i]);
            images[i] = readImage(files[i]);
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

    public BufferedImage readImage(File pic) {
        BufferedImage img = null;
        if (!pic.exists()) {
            return null;
        }
        try {
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
                int color = getMedianValue(x, y);
                filteredImage.setRGB(x, y, color);
            }
        }
        return filteredImage;
    }


    public int getMedianValue(int x, int y) {
        int rgb = 0;

        ArrayList<Integer> pixel = new ArrayList<Integer>();
        for (BufferedImage image : images) {
            pixel.add(image.getRGB(x, y));
        }
        Collections.sort(pixel);
        rgb = pixel.get(pixel.size() / 2);
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
