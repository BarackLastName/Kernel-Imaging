import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageProcess {
    public static void main(String args[]) {

    }

    public void imageEdger() {
        BufferedImage img = null, edges = null;
        try {
            img = ImageIO.read(new File("Chinese picture.png"));
        } catch (IOException e) {
            System.out.println("Read in image error");
        }

        try {
            FIle outputfile = new File("saved1.png");
            ImageIO.write(edges, "png", outputfile);            
        } catch (IOException e) {
            // handle exception
            System.out.println("Exception in saving...");
        }
        
        for (int x = 1; x<img.getWidth(); x++) {
            for (int y = 1; y<img.getHeight(); y++) {
                for (int i=-1; i<2; i++) {
                    int[][] smallGrid = new int[3][3];
                    smallGrid[0][0] = new Color(img.getRed(x-1, y-1));
                    smallGrid[0][1] = new Color(img.getRed(x, y-1));
                    smallGrid[0][2] = new Color(img.getRed(x+1, y-1));
                    smallGrid[1][0] = new Color(img.getRed(x-1, y));
                    smallGrid[1][1] = new Color(img.getRed(x, y));
                    smallGrid[1][2] = new Color(img.getRed(x+1, y));
                    smallGrid[2][0] = new Color(img.getRed(x-1, y+1));
                    smallGrid[2][1] = new Color(img.getRed(x1, y+1));
                    smallGrid[2][2] = new Color(img.getRed(x+1, y+1));
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            int r = getRed(smallGrid[i][j]);
                        }
                    }
                }
            }
        }

    }
}