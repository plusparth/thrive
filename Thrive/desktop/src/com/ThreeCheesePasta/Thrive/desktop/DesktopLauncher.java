package com.ThreeCheesePasta.Thrive.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ThreeCheesePasta.Thrive.ThriveGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.vSyncEnabled = true;
		config.title = "Thrive Prealpha Development Phase 0.0.1";
		new LwjglApplication(new ThriveGame(), config);
	}
}
