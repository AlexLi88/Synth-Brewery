import java.util.*;

import java.io.FileInputStream; 
import java.io.FileInputStream;
import javax.sound.sampled.*; 

public class OdeToJoy
{
	public static void main(String[] args){

		//Pre-computations
		final double twopi = 2*Math.PI; 

		//Set Options
		final int AMPLITUDE_MAX = 10000;
		final int AMPLITUDE_START = 8000;
		final int SAMPLING_RATE = 44100; 

		/* 
		This creates an Audio Track with a length in beats and a specified bpm (beats/min)
		Note: Do not confuse this with an Android AudioTrack, 
		this is the AuioTrack class from Synth-Brewery API 
		*/
		int song_length = 24; 
		double song_bpm= 100;

		//AudioTrack track1 = new AudioTrack(SAMPLING_RATE, song_length, song_bpm, WaveType.VIOLIN);
		//AudioTrack track2 = new AudioTrack(SAMPLING_RATE, song_length, song_bpm, WaveType.CLARINET);
		AudioTrack track3 = new AudioTrack(SAMPLING_RATE, song_length, song_bpm, WaveType.FLUTE);

		//Initialize a tuning object so we can get Equally tempered frequencies
		Tuning et = new Tuning(TuningMode.EQUALLY_TEMPERED);

		
/****************************************/	
		for(int i = 0; i < 2; i++){
			track3.writeBeat(et.getNoteFreq("E_4"), AMPLITUDE_START);
		}
		
		for(int i = 0; i < 1; i++){
			track3.writeBeat(et.getNoteFreq("F_4"), AMPLITUDE_START);
		}
		
		for(int i = 0; i < 2; i++){
			track3.writeBeat(et.getNoteFreq("G_4"), AMPLITUDE_START);
		}
		
		for(int i = 0; i < 1; i++){
			track3.writeBeat(et.getNoteFreq("F_4"), AMPLITUDE_START);
		}
		
		for(int i = 0; i < 1; i++){
			track3.writeBeat(et.getNoteFreq("E_4"), AMPLITUDE_START);
		}
		
		for(int i = 0; i < 1; i++){
			track3.writeBeat(et.getNoteFreq("D_4"), AMPLITUDE_START);
		}
		
		for(int i = 0; i < 2; i++){
			track3.writeBeat(et.getNoteFreq("C_4"), AMPLITUDE_START);
		}
		
		for(int i = 0; i < 1; i++){
			track3.writeBeat(et.getNoteFreq("D_4"), AMPLITUDE_START);
		}
		
		for(int i = 0; i < 1; i++){
			track3.writeBeat(et.getNoteFreq("E_4"), AMPLITUDE_START);
		}
		
		for(int i = 0; i < 1; i++){
			track3.writeOneHalfBeat(et.getNoteFreq("E_4"), AMPLITUDE_START);
		}
		
		for(int i = 0; i < 1; i++){
			track3.writeHalfBeat(et.getNoteFreq("D_4"), AMPLITUDE_START);
		}
		
		for(int i = 0; i < 1; i++){
			track3.writeBeat(et.getNoteFreq("D_4"), AMPLITUDE_START);
		}
		
		
		
		mixer.addTrack(track3, 0); 


		//Using standard java libraries for sound playback
		try{
			//instantiate javax.sound.sampled structures for playback
			AudioFormat audioFormat = new AudioFormat(SAMPLING_RATE, 16, 1, true, false);
			SourceDataLine dataLine = AudioSystem.getSourceDataLine(audioFormat);
			dataLine.open(audioFormat);
			dataLine.start(); 

			//Obtain the samples array of track1 in bytes
			byte[] mixer_bytes = mixer.getByteArray(); 
			
			//This writes the audio to the audio buffer and plays it
			System.out.println("Playing: Writing to audio Buffer..");

			int i = 0; 
			while(i < mixer_bytes.length){
				byte[] temp = new byte[SAMPLING_RATE];
				for(int j = 0; j < SAMPLING_RATE; j++){		
					if (i >= mixer_bytes.length){
						break;
					}
					temp[j] = mixer_bytes[i++];
				}
				dataLine.write(temp, 0, temp.length);
			}

		} catch (Exception e){
			e.printStackTrace(); 
		}
	}
}
