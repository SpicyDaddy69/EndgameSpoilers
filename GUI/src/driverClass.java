import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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

public class driverClass extends Application {

	private Parent createContent() {
		Pane root = new Pane();

		root.setPrefSize(1920, 1080);

		try (InputStream is = Files.newInputStream(Paths.get(getClass().getResource("/UHpNxB.jpg").toURI()))) {
			ImageView img = new ImageView(new Image(is));
			img.setFitWidth(1920);
			img.setFitHeight(1080);
			root.getChildren().add(img);
		} catch (IOException | URISyntaxException e) {
			System.out.println("Couldn't load image");
		}

		Title title = new Title("D U N G E O N   C R A W L");
		title.setTranslateX(100);
		title.setTranslateY(200);

		MenuBox vbox = new MenuBox(new MenuItem("PLAY"), new MenuItem("CONTROLS"),
				new MenuItem("EXIT"));
		vbox.setTranslateX(250);
		vbox.setTranslateY(325);

		root.getChildren().addAll(title, vbox);

		return root;

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("Dungeon Crawl");
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}

	private static class Title extends StackPane {
		public Title(String name) {
			Rectangle bg = new Rectangle(625, 60);
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

			});

			setOnMouseReleased(event -> {
				bg.setFill(gradient);
			});
	
		}
		
		public static class Controls extends StackPane {
			
		}
		
		
	}

	public static void main(String[] args) {

		launch(args);
	}
}