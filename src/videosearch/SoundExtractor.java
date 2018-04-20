package videosearch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.UnsupportedAudioFileException;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.SilenceDetector;
import be.tarsos.dsp.SpectralPeakProcessor;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchDetector;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;



public class SoundExtractor {
	List<Feature> features = new ArrayList<>();
	int audioBufferSize;
	int bufferOverlap;
	File audioFile;
	
	private interface Extractor {
		List<Double> run() throws UnsupportedAudioFileException, IOException;
		String name();
	}

	public SoundExtractor(File audioFile, int audioBufferSize, int bufferOverlap) {
		this.audioBufferSize = audioBufferSize;
		this.bufferOverlap = bufferOverlap;
		this.audioFile = audioFile;
		try {
			
			List<Extractor> extractors = new ArrayList<Extractor>();
			extractors.add(new RootMeanSquareExtractor());
			extractors.add(new SoundBaseFrequencyExtractor());
			extractors.add(new SoundDominantFrequencyExtractor());
			extractors.add(new SoundPressureLevelExtractor());
			for (Extractor extractor: extractors) {
				List<Double> scores = extractor.run();
				features.add(new Feature(extractor.name(), scores));
			}
			
			
		} catch (UnsupportedAudioFileException exception) {
			System.out.println("not support this file format");
		} catch (IOException exception) {
			System.out.println("file not correnct");
		}		
	}
	
	private class SoundBaseFrequencyExtractor implements Extractor{
		String name = "base frequency";
		
		public List<Double> run() throws UnsupportedAudioFileException, IOException {
			AudioDispatcher dispatcher = AudioDispatcherFactory.fromFile(audioFile, audioBufferSize, bufferOverlap);

			List<Double> frequencies = new ArrayList<>();
			PitchEstimationAlgorithm algo = PitchEstimationAlgorithm.FFT_YIN; 
			PitchDetector detector = algo.getDetector(44100, audioBufferSize);			
			dispatcher.addAudioProcessor(new AudioProcessor() {

				@Override
				public boolean process(AudioEvent audioEvent) {
					// TODO Auto-generated method stub
					float[] audioFloatBuffer = audioEvent.getFloatBuffer(); 
					PitchDetectionResult result = detector.getPitch(audioFloatBuffer);
//					result.getPitch();
//					result.getProbability();
					frequencies.add((double)result.getPitch());
					return true;
				}
				@Override
				public void processingFinished() {}				
			});			
			dispatcher.run();
			return frequencies;
		}

		@Override
		public String name() {			
			return name;
		}
	}
	
	private class SoundDominantFrequencyExtractor implements Extractor{
		String name = "dominant frequency";
		@Override
		public String name() {			
			return name;
		}
		public List<Double> run() throws UnsupportedAudioFileException, IOException {
			AudioDispatcher dispatcher = AudioDispatcherFactory.fromFile(audioFile, audioBufferSize, bufferOverlap);

			List<Double> frequencies = new ArrayList<>();
//			FFT fft = new FFT(audioBufferSize);
//			float[] amplitudes = new float[audioBufferSize / 2];
			SpectralPeakProcessor sp = new SpectralPeakProcessor(audioBufferSize, 0,44100);
			dispatcher.addAudioProcessor(sp);
			dispatcher.addAudioProcessor(new AudioProcessor() {
				@Override
				public boolean process(AudioEvent audioEvent) {
					frequencies.add(findPeak(sp.getMagnitudes(), sp.getFrequencyEstimates()));

//					float[] audioFloatBuffer = audioEvent.getFloatBuffer();
//					float[] transformBuffer = new float[audioBufferSize*2];
//					System.arraycopy(audioFloatBuffer, 0, transformBuffer, 0, audioFloatBuffer.length);
//					fft.forwardTransform(transformBuffer);
//					fft.modulus(transformBuffer, amplitudes);					
					return true;
				}

				@Override
				public void processingFinished() {
					// TODO Auto-generated method stub					
				}
			
			});
			dispatcher.run();
			return frequencies;
		}
		private double findPeak(float[] fs, float[] fs2) {
			assert(fs.length == fs2.length);
			double max = Double.MIN_VALUE;
			double dominantFrequency = 0;
			for (int i = 0; i < fs.length; i++) {
				if (fs[i] > max) {
					max = fs[i];
					dominantFrequency = fs2[i];
				}
			}
			return dominantFrequency;
		}
	}
	
	
	private class SoundPressureLevelExtractor implements Extractor {
		String name = "sound pressure level";
		@Override
		public String name() {			
			return name;
		}
		public List<Double> run() throws UnsupportedAudioFileException, IOException {
			AudioDispatcher dispatcher = AudioDispatcherFactory.fromFile(audioFile, audioBufferSize, bufferOverlap);

			
			List<Double> dbspl = new ArrayList<>();
			SilenceDetector sd = new SilenceDetector();
			dispatcher.addAudioProcessor(sd);
			dispatcher.addAudioProcessor(new AudioProcessor() {

				@Override
				public boolean process(AudioEvent audioEvent) {
					dbspl.add(sd.currentSPL());					
					return true;
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
	private class RootMeanSquareExtractor implements Extractor {
		String name = "root mean square";
		@Override
		public String name() {			
			return name;
		}
		public List<Double> run() throws UnsupportedAudioFileException, IOException {
			AudioDispatcher dispatcher = AudioDispatcherFactory.fromFile(audioFile, audioBufferSize, bufferOverlap);

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
//			System.out.println(rms.size());
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
