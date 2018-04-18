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
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchDetector;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;
import be.tarsos.dsp.SpectralPeakProcessor; 
import be.tarsos.dsp.SpectralPeakProcessor.SpectralPeak; 



public class SoundExtractor {
	List<Feature> features = new ArrayList<>();
	int audioBufferSize;
	public SoundExtractor(File audioFile, int audioBufferSize, int bufferOverlap) {
		this.audioBufferSize = audioBufferSize;
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
			
			
			dispatcher = AudioDispatcherFactory.fromFile(audioFile, audioBufferSize, bufferOverlap);
			SoundBaseFrequencyExtractor sb = new SoundBaseFrequencyExtractor();
			List<Double> sbf = sb.run(dispatcher);
			System.out.println("sbf frequencies length");
			System.out.println(sbf.size());
			
			
		} catch (UnsupportedAudioFileException exception) {
			System.out.println("not support this file format");
		} catch (IOException exception) {
			System.out.println("file not correnct");
		}		
	}
	
	private class SoundBaseFrequencyExtractor {
		String name = "base frequency";
		
		public List<Double> run(AudioDispatcher dispatcher) {
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
		public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
			   double timeStamp = audioEvent.getTimeStamp(); 
			   double pitch = pitchDetectionResult.getPitch(); 
			   float probability = pitchDetectionResult.getProbability(); 
			   frequencies.add(pitch);
			   System.out.println(timeStamp+","+pitch+","+probability); 
		}
	}
	
	private class SoundDominantFrequencyExtractor {
		String name = "dominant frequency";
		public List<Double> run(AudioDispatcher dispatcher) {
						
			List<Double> frequencies = new ArrayList<>();
//			FFT fft = new FFT(audioBufferSize);
//			float[] amplitudes = new float[audioBufferSize / 2];
			SpectralPeakProcessor sp = new SpectralPeakProcessor(audioBufferSize, 0,44100);
			dispatcher.addAudioProcessor(sp);
			dispatcher.addAudioProcessor(new AudioProcessor() {
				@Override
				public boolean process(AudioEvent audioEvent) {
//					float[] audioFloatBuffer = audioEvent.getFloatBuffer();
//					float[] transformBuffer = new float[audioBufferSize*2];
//					System.arraycopy(audioFloatBuffer, 0, transformBuffer, 0, audioFloatBuffer.length);
//					fft.forwardTransform(transformBuffer);
//					fft.modulus(transformBuffer, amplitudes);					
					frequencies.add(findPeak(sp.getMagnitudes(), sp.getFrequencyEstimates());
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
