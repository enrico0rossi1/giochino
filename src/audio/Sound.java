package audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.net.URL;

public class Sound {
  
  Clip clip;
  URL soundURL[] = new URL[13];
  FloatControl fc;
  public int volumeScale= 3;
  float volume;

  public Sound () {  
    soundURL[0]  = getClass().getResource("/audio/music/DriftveilCityBeepBox.wav");
    soundURL[1] = getClass().getResource("/audio/music/DarkWoods.wav");
    soundURL[2] = getClass().getResource("/audio/music/Jungle.wav");
    soundURL[3]  = getClass().getResource("/audio/music/Beach.wav");
    soundURL[4]  = getClass().getResource("/audio/music/GreatFairyFountainBeepBox.wav");
    soundURL[5]  = getClass().getResource("/audio/music/PauseMenu.wav");
    soundURL[6]  = getClass().getResource("/audio/sfx/sampleSFX.wav");
    soundURL[7] = getClass().getResource("/audio/sfx/LoadingSFX.wav");
    soundURL[8] = getClass().getResource("/audio/sfx/GameOverSFX.wav");
    soundURL[9] = getClass().getResource("/audio/sfx/AcceptSFX.wav");
    soundURL[10] = getClass().getResource("/audio/sfx/CancelSFX.wav");
    soundURL[11] = getClass().getResource("/audio/sfx/MenuSFX.wav");
    soundURL[12]  = getClass().getResource("/audio/music/EndGame.wav");
  }

  public void setFile(int i){

    try {

      AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]) ;
      this.clip = AudioSystem.getClip();
      clip.open(ais);
      fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
      checkVolume();

    }catch(Exception e){
      e.printStackTrace(); 
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

  public void checkVolume () {

    switch(volumeScale) {
      case 0: volume = -80f ; break;
      case 1: volume =  -10f ;  break;
      case 2:  volume = -2f;  break;
      case 3:  volume = 1f ; break;
      case 4:  volume = 3f ; break;
      case 5:  volume = 6f ; break;
     }
     fc.setValue(volume);
  }

}