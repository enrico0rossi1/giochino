package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.net.URL;

public class Sound {
  
    Clip clip;
    URL soundURL[] = new URL[20];
  
    public Sound () {

     soundURL[0]  = getClass().getResource("/Audio/Music/GreatFairyFountainBeepBox.wav");
     soundURL[1]  = getClass().getResource("/Audio/SFX/sampleSFX.wav");
     soundURL[2]  = getClass().getResource("/Audio/Music/DriftveilCityBeepBox.wav");
     soundURL[3]  = getClass().getResource("/Audio/Music/GreatFairyFountainBeepBox.wav");
  }

  public void setFile(int i){

    try {

        AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]) ;
        this.clip = AudioSystem.getClip();
        clip.open(ais);

    } catch(Exception e){

    }
  }

  public void play() {

     clip.start();
  }

  public void loop() {

    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }

  public void stop(){

    clip.stop();
  }


}