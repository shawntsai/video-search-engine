package ui.controller;

import java.awt.image.BufferedImage;
import java.io.*;

public class ImageParser {
    private final static int WIDTH = 352;
    private final static int HEIGHT = 288;
    private final static String PATH = "query/first/first001.rgb";
    private final static String FOLDER = "query/first/";
    private final static String filePrefix = "first";
    private final static String filePostfix = ".rgb";
    private BufferedImage[] frames;

    public ImageParser(int framsNum) {
        frames = new BufferedImage[framsNum + 1];
    }

    public BufferedImage parseImg(String fullName) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        try {
//            String fullName = PATH;
            File file = new File(fullName);
            InputStream is = new FileInputStream(file);

            long len = file.length();
            byte[] bytes = new byte[(int)len];
            int offset = 0;
            int numRead;
            while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
            System.out.println("Start loading frame: " + fullName);
            int index = 0;

            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    byte r = bytes[index];
                    byte g = bytes[index+HEIGHT*WIDTH];
                    byte b = bytes[index+HEIGHT*WIDTH*2];
                    int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
                    image.setRGB(x,y,pix);
                    index++;
                }
            }
//            images.add(image);
            is.close();
            System.out.println("End loading frame: " + fullName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public BufferedImage[] parseAllImg() {
        for(int i = 1; i < frames.length; i++) {
            StringBuilder file = new StringBuilder(FOLDER);
            file.append(filePrefix);
            if( i > 9 && i < 100)
                file.append("0");
            else if(i < 10)
                file.append("00");
            file.append(i);
            file.append(filePostfix);
            frames[i] = parseImg(new String(file));
        }
        return frames;
    }


}
