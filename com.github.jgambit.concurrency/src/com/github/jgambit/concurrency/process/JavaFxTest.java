package com.github.jgambit.concurrency.process;

import com.github.jgambit.concurrency.gui.TestNode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxTest extends Application {

	@Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fl = new FXMLLoader();
        fl.setLocation(getClass().getResource("/com/github/jgambit/concurrency/gui/TestNode.fxml"));
        fl.load();
        Parent root = fl.getRoot();
        TestNode node = (TestNode)fl.getController();
        node.setStage(stage);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
