/*
 * SNMP Inquisitor
 *
 * Copyright (C) 2000, Jonathan Sevy <jsevy@mcs.drexel.edu>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

import java.io.*;
import javax.sound.sampled.*;
import java.util.jar.*;
import java.util.zip.*;


/**
*	Utility class giving a convenient interface for playing sound from a file. Note
*	that this requires the javax.sound.sampled.* classes that are part of jdk1.3 and up;
*	as such, you should test the version of Java to make sure it's high enough to support
*	this - e.g.,
*
*	String version = System.getProperty("java.version");
*			
*	if (version.compareTo("1.3") >= 0)
*	{
*		AudioFilePlayer audioPlayer = new AudioFilePlayer("thisJarFile.jar", "mySound.wav");
*		audioPlayer.playFromJarFile();
*	}
*
**/

public class AudioFilePlayer
{
	
	private String soundFileName, jarFileName;
	
	
	/**
	*	Create a player for the audio file whose pathname is supplied.
	*	Note: used with standard file-system file, not jar file.
	**/
	
	public AudioFilePlayer(String soundFileName)
	{
		this.soundFileName = soundFileName;
	}
	
	
	
	/**
	*	Create a player for the specified audio file contained in the specified jar file. 
	*	Note that the jar file may be the one containing the application code.
	**/
	
	public AudioFilePlayer(String jarFileName, String soundFileName)
	{
		this.jarFileName = jarFileName;
		this.soundFileName = soundFileName;
	}
	
	
	
	/**
	*	Play the associated audio file contained in the associated jar file.
	**/
	
	public void playFromJarFile()
	{
	
		try
		{
			JarFile thisJarFile = new JarFile(jarFileName);
			
			JarEntry audioEntry = thisJarFile.getJarEntry(soundFileName);
			
			// need an input stream supporting mark() and reset(); used BufferedInputStream		
			BufferedInputStream jarFileInputStream = new BufferedInputStream(thisJarFile.getInputStream(audioEntry));
			
			// get the audio file format; think this is broken...
			AudioFileFormat audioFileFormat = AudioSystem.getAudioFileFormat(jarFileInputStream);
			
			// ..so the following doesn't work
			//AudioFormat audioFormat = audioFileFormat.getFormat();
			
			// so get the audio format hard-coded; wish the preceding commented-out line worked...
			AudioFormat audioFormat = new AudioFormat(11025, 16, 1, true, false);
			
			DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, audioFormat);
			
			Clip audioClip = (Clip)AudioSystem.getLine(dataLineInfo);
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(jarFileInputStream);
			
			audioClip.open(audioInputStream);
			
			audioClip.start();
		}
		catch (Exception e)
		{
			// do nothing...
			System.out.println(e);
		}
	
	}
	
	
	
	/**
	*	Play the associated audio file.
	**/
	
	public void playFromFile()
	{
	
		try
		{
			File audioFile = new File(soundFileName);
			
			AudioFileFormat audioFileFormat = AudioSystem.getAudioFileFormat(audioFile);
			
			AudioFormat audioFormat = audioFileFormat.getFormat();
					
			DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, audioFormat);
			
			Clip audioClip = (Clip)AudioSystem.getLine(dataLineInfo);
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
			
			audioClip.open(audioInputStream);
			
			audioClip.start();
		}
		catch (Exception e)
		{
			// do nothing...
			System.out.println(e);
		}
	
	}

}