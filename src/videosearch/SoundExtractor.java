package videosearch;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.AudioProcessor; 
import be.tarsos.dsp.SilenceDetector; 



public class SoundExtractor {
//    private final static int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb
//    InputStream waveStream;
    
//	public SoundExtractor(InputStream waveStream) {
//		this.waveStream = waveStream;
//		
//	}
	List<Feature> features = new ArrayList<>();
	
	public SoundExtractor(File audioFile, int audioBufferSize, int bufferOverlap) {
		try {
			AudioDispatcher dispatcher = AudioDispatcherFactory.fromFile(audioFile, audioBufferSize, bufferOverlap);
			RootMeanSquareExtractor rmsExtractor = new RootMeanSquareExtractor();
			SoundPressureLevelExtractor spe = new SoundPressureLevelExtractor();			
			List<Double> rms = rmsExtractor.run(dispatcher);
//			for (Double x: rms) System.out.println(x);
			features.add(new Feature(rmsExtractor.name, rms));
			dispatcher = AudioDispatcherFactory.fromFile(audioFile, audioBufferSize, bufferOverlap);
			List<Double> dbspl = spe.run(dispatcher);
			features.add(new Feature(spe.name, dbspl));
//			for (Double x: dbspl) System.out.println(x);
			
		} catch (UnsupportedAudioFileException exception) {
			System.out.println("not support this file format");
		} catch (IOException exception) {
			System.out.println("file not correnct");
		}		
	}
	
	private class SoundPressureLevelExtractor {
		String name = "sound pressure level";
		public List<Double> run(AudioDispatcher dispatcher) {
			
			
			List<Double> dbspl = new ArrayList<>();
			SilenceDetector sd = new SilenceDetector();
			dispatcher.addAudioProcessor(sd);
			dispatcher.addAudioProcessor(new AudioProcessor() {

				@Override
				public boolean process(AudioEvent audioEvent) {
					dbspl.add(sd.currentSPL());					
					return false;
				}

				@Override
				public void processingFinished() {					
				}
				
			});
			dispatcher.run();
			System.out.println(dbspl.size());
			return dbspl;
		}
	}
	
	/**
	 * calculate the root mean square of an audio signal for each block of 2048 samples
	 * @author shawn
	 * 
	 */
	private class RootMeanSquareExtractor {
		String name = "root mean square";
		public List<Double> run(AudioDispatcher dispatcher) {
			List<Double> rms = new ArrayList<>();
			dispatcher.addAudioProcessor(new AudioProcessor() {
				@Override
				public void processingFinished() {}

				@Override
				public boolean process(AudioEvent audioEvent) {
					// TODO Auto-generated method stub
					rms.add(audioEvent.getRMS());
//					System.out.println(audioEvent.getRMS());
					return true;
				}
				
			});
			dispatcher.run(); 
			System.out.println(rms.size());
			return rms;
		}
	}
	
//	void getByteStream() throws PlayWaveException {
//		byte[] audioBuffer = new byte[EXTERNAL_BUFFER_SIZE];
//		
//		try {
//			InputStream bufferedIn = new BufferedInputStream(this.waveStream);
//			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
//		    int readBytes = 0;
//		    int numFramesRead = 0;
//		    int totalFrames = 0;
//			while ((readBytes = audioInputStream.read(audioBuffer, 0,
//					audioBuffer.length)) != -1) {
//			      numFramesRead = readBytes / EXTERNAL_BUFFER_SIZE;
//			      totalFrames += numFramesRead;
//			      
//				
//				// TO DO 
//				// Should implement pcm with audio buffer here								
//			}
//			System.out.println(totalFrames);
//			
//		} catch (UnsupportedAudioFileException e1) {
//		    throw new PlayWaveException(e1);
//		} catch (IOException e1) {
//		    throw new PlayWaveException(e1);
//		}
//		
//	}
	
	
	
}
