package com.luias.libLuix.cores;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.luias.libLuix.graphics.Scene;
import com.luias.libLuix.graphics.SimpleScene;
import com.luias.libLuix.graphics.ui.effects.Effect;

public class Container {
	public static float fps = 0.0f;

	private Scene cur_scene;
	private boolean _running = false;
	private long start_time = System.currentTimeMillis();
	private long refresh_time;
	private int fpsCount = 0;

	private JFrame frame;

	public Container(String title, Configurations config) {
		this(title, config, null);
	}
	
	public Container(String title, Configurations config, Scene defaultScene) {
		Configurations.getInstance().setConfig(config);

		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds((d.width - Configurations.getInstance().width) / 2,
				(d.height - Configurations.getInstance().height) / 2, Configurations.getInstance().width,
				Configurations.getInstance().height);

		if (defaultScene == null) {
			cur_scene = new SimpleScene() {
				private static final long serialVersionUID = 1L;
				private boolean first = false;

				@Override
				public void render(float dt) {

					batch.clearRect(0, 0, Configurations.getInstance().width, Configurations.getInstance().height);
					batch.setColor(Color.white);

					batch.drawString("Hello~ Luias Game Lib.", (Configurations.getInstance().width - 150.0f) / 2.0f,
							(Configurations.getInstance().height - 80.0f) / 2.0f);
					super.render(dt);
				}

				@Override
				public void updater(float dt) {

					if (!first) {
						camera.addEffect(new Effect.TypoEffect(batch,
								"This is my first game library, For pure java - made by Luias",
								(Configurations.getInstance().width - 340.0f) / 2.0f,
								(Configurations.getInstance().height - 30.0f) / 2.0f, false));

						camera.addEffect(new Effect.TypoEffect(batch,
								"Just add effect. It just show your effect and image in your head",
								(Configurations.getInstance().width - 360.0f) / 2.0f,
								(Configurations.getInstance().height + 20.0f) / 2.0f, false));
						
						first = true;
					}

				}
			};
		}else {
			cur_scene = defaultScene;
		}

		frame.getContentPane().add(cur_scene);

		frame.setVisible(true);
		frame.setResizable(Configurations.getInstance().resizeable);

		_running = true;
		gameloop();
	}

	public void gameloop() {

		float wait = 1.0f / Configurations.getInstance().fps_limit;
		long fps_limit_time_s = System.currentTimeMillis();

		while (_running) {
			refresh_time = System.currentTimeMillis();
			long fps_limit_time_e = refresh_time;

			float dt = (float) (fps_limit_time_e - fps_limit_time_s) / 1000.0f;
			if (dt > wait) {
				cur_scene.render(dt);
				cur_scene.updater(dt);
				fpsCount++;
				fps_limit_time_s = fps_limit_time_e;

				if ((refresh_time - start_time) >= 1000) {
					fps = (fpsCount * (1000.0f / (float) (refresh_time - start_time)));
					start_time = refresh_time;
					fpsCount = 0;
				}
			}

		}

	}

	public Scene getScene() {
		return cur_scene;
	}

	public void setScene(Scene scene) {
		this.frame.getContentPane().remove(0);
		this.cur_scene = scene;
		this.frame.getContentPane().add(cur_scene);
	}

}
