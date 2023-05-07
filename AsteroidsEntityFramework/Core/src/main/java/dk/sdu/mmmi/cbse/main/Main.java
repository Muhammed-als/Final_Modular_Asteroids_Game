package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Asteroids");
		config.setWindowedMode(600, 600);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("dk.sdu.mmmi.cbse.main");
		context.refresh();
		new Lwjgl3Application((ApplicationListener) context.getBean("Game"), config);
	}

}