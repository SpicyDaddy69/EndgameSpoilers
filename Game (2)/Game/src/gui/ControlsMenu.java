
package gui;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.*;

public class ControlsMenu extends Application {

	Parent createContent() {
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

		Title title = new Title("C O N T R O L S");
		title.setTranslateX(100);
		title.setTranslateY(200);

		MenuBox vbox = new MenuBox(new MenuItem("MOVEMENT"), new MenuItem("Forward"), new MenuItem("Backward"),
				new MenuItem("Left"), new MenuItem("Right"), new MenuItem("ATTACK"), new MenuItem("BACK"));
		vbox.setTranslateX(190);
		vbox.setTranslateY(325);

		MenuBox controlsBox = new MenuBox(new ControlsItem(""), new ControlsItem("W"), new ControlsItem("S"),
				new ControlsItem("A"), new ControlsItem("D"), new ControlsItem(""));

		controlsBox.setTranslateX(230);
		controlsBox.setTranslateY(325);

		root.getChildren().addAll(title, vbox, controlsBox);

		return root;

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		primaryStage.setFullScreen(true);
		primaryStage.setTitle("Dungeon Crawl");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private static class Title extends StackPane {
		public Title(String name) {
			Rectangle bg = new Rectangle(400, 60);
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

		public MenuBox(ControlsItem... items) {
			getChildren().add(createSeperator());

			for (ControlsItem item : items) {
				getChildren().addAll(item, createSeperator());
			}
		}

		private Line createSeperator() {
			Line sep = new Line();
			sep.setEndX(180);
			sep.setStroke(Color.DARKGREY);
			return sep;
		}

	}

	private static class MenuItem extends StackPane {
		public MenuItem(String name) {

			Rectangle bg = new Rectangle(180, 40);
			bg.setOpacity(0.2);

			Text text = new Text(name);
			text.setFill(Color.LIGHTGREY);
			text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));

			setAlignment(Pos.CENTER);

			getChildren().addAll(bg, text);
			setOnMouseEntered(event -> {
			});

			setOnMouseExited(event -> {
				bg.setFill(Color.BLACK);
			});
			setOnMousePressed(event -> {
				bg.setFill(Color.AZURE);
				((Node) (event.getSource())).getScene().getWindow().hide();

			});

			setOnMouseReleased(event -> {
				bg.setFill(Color.BLACK);
			});

		}

	}

	private static class ControlsItem extends StackPane {
		public ControlsItem(String name) {

			Rectangle bg = new Rectangle(41, 40);
			bg.setOpacity(0.2);

			Text text = new Text(name);
			text.setFill(Color.LIGHTGREY);
			text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));

			setAlignment(Pos.CENTER_RIGHT);

			getChildren().addAll(bg, text);
			setOnMouseEntered(event -> {

			});

			setOnMouseExited(event -> {
				bg.setFill(Color.BLACK);
			});
			setOnMousePressed(event -> {
				bg.setFill(Color.AZURE);
			});

			setOnMouseReleased(event -> {
				bg.setFill(Color.BLACK);
			});
		}
	}

	public static void main(String[] args) {

		launch(args);
	}
}