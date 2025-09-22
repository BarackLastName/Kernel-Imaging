import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageProcess {
    public static void main(String args[]) {
        imageEdger();
    }

    public static void imageEdger() {
        BufferedImage img = null;
        
        try {
            img = ImageIO.read(new File("dubs.jpg"));
        } catch (IOException e) {
            System.out.println("Read in image error");
        }

        BufferedImage edges = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

   

        int[][] xfilter = {{-1, 0, 1},
                           {-2, 0, 2},
                           {-1, 0, 1}};
        int[][] yfilter = {{1, 2, 1}, 
                           {0, 0, 0},
                           {-1, -2, -1}};

        for (int x = 1; x < img.getWidth() - 1; x++) {
            for (int y = 1; y < img.getHeight() - 1; y++) {
                Color[][] smallGrid = new Color[3][3];
                smallGrid[0][0] = new Color(img.getRGB(x-1, y-1));
                smallGrid[0][1] = new Color(img.getRGB(x-1, y));
                smallGrid[0][2] = new Color(img.getRGB(x-1, y+1));
                smallGrid[1][0] = new Color(img.getRGB(x, y-1));
                smallGrid[1][1] = new Color(img.getRGB(x, y));
                smallGrid[1][2] = new Color(img.getRGB(x, y+1));
                smallGrid[2][0] = new Color(img.getRGB(x+1, y-1));
                smallGrid[2][1] = new Color(img.getRGB(x+1, y));
                smallGrid[2][2] = new Color(img.getRGB(x+1, y+1));

                int rSumx = 0;
                int gSumx = 0;
                int bSumx = 0;

                int rSumy = 0;
                int gSumy = 0;
                int bSumy = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        rSumx += smallGrid[i][j].getRed() * xfilter[i][j];
                        gSumx += smallGrid[i][j].getGreen() * xfilter[i][j];
                        bSumx += smallGrid[i][j].getBlue() * xfilter[i][j];
                        rSumy += smallGrid[i][j].getRed() * yfilter[i][j];
                        gSumy += smallGrid[i][j].getGreen() * yfilter[i][j];
                        bSumy += smallGrid[i][j].getBlue() * yfilter[i][j];
                        }
                    }
                
                int gX = (rSumx + gSumx + bSumx) / 3;
                int gY = (rSumy + gSumy + bSumy) / 3;
                int g = (int) Math.sqrt(Math.pow(gX, 2) + Math.pow(gY, 2));
                int rgb = ((g&0x0ff)<<16)|((g&0x0ff)<<8)|(g&0x0ff);
                edges.setRGB(x, y, rgb);
                }
            }
            try {
                File outputfile = new File("dubsEdged.png");
                ImageIO.write(edges, "png", outputfile);            
            } catch (IOException e) {
                // handle exception
                System.out.println("Exception in saving...");
            }
        }
    }

