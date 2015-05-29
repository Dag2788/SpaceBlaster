import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Sound {

	public static final AudioClip BACKGROUND = Applet.newAudioClip(Sound.class.getResource("/ship_background.wav"));
	public static final AudioClip BULLET1 = Applet.newAudioClip(Sound.class.getResource("/ship_bullet1.wav"));
	public static final AudioClip BULLET2 = Applet.newAudioClip(Sound.class.getResource("/ship_bullet2.wav"));
	public static final AudioClip MENU = Applet.newAudioClip(Sound.class.getResource("/menu.wav"));
	public static final AudioClip GAMEOVR = Applet.newAudioClip(Sound.class.getResource("/ship_game_over.wav"));
	public static final AudioClip EXPLODE = Applet.newAudioClip(Sound.class.getResource("/ship_explotion.wav"));
	public static final AudioClip LIFE = Applet.newAudioClip(Sound.class.getResource("/ship_life.wav"));


}

