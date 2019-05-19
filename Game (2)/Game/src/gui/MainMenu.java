package gui;

import intermediary.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.application.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.*;

public class MainMenu extends Application {
	private static Clip clip;
	private Parent createContent() {
		Pane root = new Pane();

		root.setPrefSize(1920, 1080);

		try (InputStream is = Files
				.newInputStream(Paths.get(getClass().getResource("/textures.menu/menuScreen.jpg").toURI()))) {
			ImageView img = new ImageView(new Image(is));
			img.setFitWidth(1920);
			img.setFitHeight(1080);
			root.getChildren().add(img);
		} catch (IOException | URISyntaxException e) {
			System.out.println("Couldn't load image");
		}

		Title title = new Title("T H E   A D V E N T U R E S   O F   C O M M U N I S M");
		title.setTranslateX(100);
		title.setTranslateY(200);

		MenuBox vbox = new MenuBox(new MenuItem("PLAY"), new MenuItem("CONTROLS"), new MenuItem("EXIT"));
		vbox.setTranslateX(250);
		vbox.setTranslateY(325);

		root.getChildren().addAll(title, vbox);
		return root;

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("The Adventures of Communism");
		primaryStage.setScene(scene);
		// primaryStage.setFullScreen(true);
		primaryStage.show();
	}

	public void close(Stage primaryStage) throws Exception {
		primaryStage.close();
	}

	private static class Title extends StackPane {
		public Title(String name) {
			Rectangle bg = new Rectangle(1250, 60);
			bg.setStroke(Color.WHITE);
			bg.setStrokeWidth(2);
			bg.setFill(null);

			Text text = new Text(name);
			text.setFill(Color.WHITE);
			text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));

			setAlignment(Pos.CENTER);
			getChildren().addAll(bg, text);
		}
	}

	private static class MenuBox extends VBox {
		public MenuBox(MenuItem... items) {
			getChildren().add(createSeperator());

			for (MenuItem item : items) {
				getChildren().addAll(item, createSeperator());
			}
		}

		private Line createSeperator() {
			Line sep = new Line();
			sep.setEndX(200);
			sep.setStroke(Color.DARKGREY);
			return sep;
		}

	}

	private static class MenuItem extends StackPane {
		public MenuItem(String name) {
			LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
					new Stop[] { new Stop(0, Color.GHOSTWHITE), new Stop(0.1, Color.BLACK), new Stop(0.9, Color.BLACK),
							new Stop(1, Color.GHOSTWHITE)

					});

			Rectangle bg = new Rectangle(200, 45);
			bg.setOpacity(0.2);

			Text text = new Text(name);
			text.setFill(Color.LIGHTGREY);
			text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));

			setAlignment(Pos.CENTER);

			getChildren().addAll(bg, text);

			setOnMouseEntered(event -> {
				bg.setFill(gradient);
				text.setFill(Color.WHITE);

			});

			setOnMouseExited(event -> {
				bg.setFill(Color.BLACK);
				text.setFill(Color.DARKGREY);
			});
			setOnMousePressed(event -> {
				bg.setFill(Color.AZURE);

				// Play Button
				if (event.getSceneX() >= 250 && event.getSceneX() <= 450 && event.getSceneY() >= 325
						&& event.getSceneY() <= 370) {
					Main game = new Main();

					game.run(null, 0, 3, clip);
					((Node) (event.getSource())).getScene().getWindow().hide();

				}
				// Controls Button
				else if (event.getSceneX() >= 250 && event.getSceneX() <= 450 && event.getSceneY() > 370
						&& event.getSceneY() <= 415) {
					ControlsMenu cm = new ControlsMenu();
					try {
						cm.start(new Stage());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				// Exit Button
				else if (event.getSceneX() >= 250 && event.getSceneX() <= 450 && event.getSceneY() > 415
						&& event.getSceneY() <= 460) {
					((Node) (event.getSource())).getScene().getWindow().hide();
				}

			});

			setOnMouseReleased(event -> {
				bg.setFill(gradient);
				text.setFill(Color.WHITE);

			});

		}

	}

	public static void main(String[] args) {
		playMusic("res/ussr_national_anthem_1944_russian.wav");
		launch(args);
	}

	public static void playMusic(String fileLocation)
	{
		File musicPath = new File(fileLocation);
		try
		{
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
			clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}catch(Exception e)
		{
			System.out.println("error");
		}
}
}