package videosearch;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImageFeatureExtractor {
	final static int height = 288;
	final static int width = 352;
	final static int MaxPixelTreshold = 50;
	List<Feature> features = new ArrayList<>();

	public ImageFeatureExtractor(File folder) {
		
		File[] rgbFiles = folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("rgb");
			}
		});
		
		ImageMotionExtractor ext = new ImageMotionExtractor();
		Feature f = new Feature(ext.name, ext.run(rgbFiles));
		features.add(f);		
				
		ImageColorExtractor extColor = new ImageColorExtractor();
		List<int[]> l = extColor.run(rgbFiles);
		
		features.add(new ColorFeature(extColor.name, l));		
		
	}
	
	
	private class ImageMotionExtractor {
		String name = "rgb image motion";
		public double[] run(File[] rgbFiles) {
			
			double[] result = new double[rgbFiles.length];
			if (rgbFiles.length < 1) return result;
			
			int[] prevPixels = readPixels(rgbFiles[0].getPath());
			for (int i = 1; i < rgbFiles.length; i++) {
				File f = rgbFiles[i];
				if (f.isFile()) {
					int[] currentPixels = readPixels(f.getPath());
					int numMoves = 0;
					for (int j = 0; j < currentPixels.length; j++) {
						if (Math.abs(currentPixels[i] - prevPixels[i]) >= MaxPixelTreshold) {
							numMoves ++;
						}
					}
					result[i] = numMoves;
				}				
			}
			assert(result.length != 0);
//			System.out.println("motion result");
//			System.out.println(result.length);
			return result;
			
		}		
	}
	
	private class ImageColorExtractor {
		String name = "rgb color";
		final int numColors = 5; 
		public List<int[]> run(File[] rgbFiles) {
			List<int[]> r = new ArrayList<>();
			for (File f: rgbFiles) {
				int[] huesCount = readhues(f.getPath(), numColors);
				r.add(huesCount);
			}
			return r;
		}		
	}

	private int[] readhues(String filePath, int numColors) {
		int[] hues = new int[numColors];
		try {
			File file = new File(filePath);
			InputStream is = new FileInputStream(file);
			
			long len = file.length();
			byte[] bytes = new byte[(int) len];
			
			int offset = 0;
			int numRead = 0;
			
			while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
				offset += numRead;
			}
			int ind = 0;
			for(int y = 0; y < height; y++){
				for(int x = 0; x < width; x++){

					byte r = bytes[ind];
					byte g = bytes[ind+height*width];
					byte b = bytes[ind+height*width*2]; 
					// Hue is the actual color
					
					float hue = Color.RGBtoHSB(r, g, b, null)[0];
					if (hue >= 0 && hue < 1./6) hues[0] += 1;
					else if (hue < 2./6) hues[1] += 1;
					else if (hue < 3./6) hues[2] += 1;
					else if (hue < 4./6) hues[3] += 1;
					if (hue < 5./6) hues[4] += 1;
					ind ++;
				}
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return hues;
	}
	
	public int[] readPixels(String filePath) {
		int[] pixels = new int[height * width];
		try {
			File file = new File(filePath);
			InputStream is = new FileInputStream(file);
			
			long len = file.length();
			byte[] bytes = new byte[(int) len];
			
			int offset = 0;
			int numRead = 0;
			
			while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
				offset += numRead;
			}
						
			int ind = 0;
			
			for(int y = 0; y < height; y++){
				for(int x = 0; x < width; x++){

					byte r = bytes[ind];
					byte g = bytes[ind+height*width];
					byte b = bytes[ind+height*width*2]; 
					
//					imageR[y][x] = r < 0 ? r + 255 : r;
//					imageG[y][x] = g < 0 ? g + 255 : g;
//					imageB[y][x] = b < 0 ? b + 255 : b;
										
					int pix = 0xff000000 | ((r & 0xff) << 16) | ((g & 0xff) << 8) | (b & 0xff);
					//int pix = ((a << 24) + (r << 16) + (g << 8) + b);
//					original.setRGB(x, y, pix);
					// Hue is the actual color					
					pixels[ind] = pix;
					ind++;	
				}
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return pixels;
	}
	
	
	
}
