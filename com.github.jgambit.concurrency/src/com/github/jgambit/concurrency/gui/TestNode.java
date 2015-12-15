package com.github.jgambit.concurrency.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TestNode implements Initializable {

	private Stage primaryStage;

	@FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        FXMLLoader fl = new FXMLLoader();
        fl.setLocation(getClass().getResource("/com/github/jgambit/concurrency/gui/TestContentNode.fxml"));
        fl.load();
        Parent root = fl.getRoot();

        Stage modal_dialog = new Stage(StageStyle.DECORATED);
        modal_dialog.initModality(Modality.WINDOW_MODAL);
        modal_dialog.initOwner(primaryStage);
        Scene scene = new Scene(root);

        TestContentNode t1 = (TestContentNode)fl.getController();
        t1.setStage(modal_dialog);
        modal_dialog.setScene(scene);
        modal_dialog.show();
    }

    public void setStage(Stage temp){
        primaryStage = temp;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
