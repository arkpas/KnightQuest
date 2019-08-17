package arkpas.kursspring;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class KursspringApplication extends Application {

	private static ConfigurableApplicationContext ctx;

	public static void main(String[] args) {

		ctx = SpringApplication.run(KursspringApplication.class, args);
		launch(args);



	}
	public void start (Stage primaryStage) {
		VBox root = new VBox();
		Scene scene = new Scene(root, 100, 100);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest( action -> { Platform.exit(); SpringApplication.exit(ctx, () -> 0); } );

	}

}
