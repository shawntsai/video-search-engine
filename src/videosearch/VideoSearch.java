package videosearch;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class VideoSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			String audioFileName = "database_videos/flowers/flowers.wav";
			FileInputStream audioStream = new FileInputStream(audioFileName);
//			PlaySound playSound = new PlaySound(audioStream);
//			playSound.play();
			
			SoundExtractor extractor = new SoundExtractor(new File(audioFileName), 2048, 0);
			System.out.println("hi");
		
		} catch (FileNotFoundException e) {
			System.out.println("can not find file");
		}	
//		catch (PlayWaveException e) {
//			System.out.println("can not play");
//		}
		
	}

}
