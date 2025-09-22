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

        int[][] xfilter = {{-1, 0, 1},
                           {-2, 0, 2},
                           {-1, 0, 1}}
        int[][] yfilter = {{1, 2, 1}, 
                           {0, 0, 0},
                           {-1, -2, -1}}
        
        for (int x = 1; x<img.getWidth(); x++) {
            for (int y = 1; y<img.getHeight(); y++) {
                int[][] smallGrid = new int[3][3];
                smallGrid[0][0] = new Color(img.getRGB(x-1, y-1));
                smallGrid[0][1] = new Color(img.getRGB(x, y-1));
                smallGrid[0][2] = new Color(img.getRGB(x+1, y-1));
                smallGrid[1][0] = new Color(img.getRGB(x-1, y));
                smallGrid[1][1] = new Color(img.getRGB(x, y));
                smallGrid[1][2] = new Color(img.getRGB(x+1, y));
                smallGrid[2][0] = new Color(img.getRGB(x-1, y+1));
                smallGrid[2][1] = new Color(img.getRGB(x1, y+1));
                smallGrid[2][2] = new Color(img.getRGB(x+1, y+1));

                int r = 0;
                int g = 0;
                int b = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        r += smallGrid[i][j].getRed() * xfilter[i][j];
                        g += smallGrid[i][j].getGreen() * xfilter[i][j];
                        b += smallGrid[i][j].getBlue() * xfilter[i][j];
                        
                        }
                    }
                }
            }
        }
    }

