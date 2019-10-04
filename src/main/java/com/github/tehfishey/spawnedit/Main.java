package com.github.tehfishey.spawnedit;

import java.lang.reflect.Constructor;

import com.github.tehfishey.spawnedit.controller.ControllerStateManager;
import com.github.tehfishey.spawnedit.model.Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       
    	final Model model = new Model();
    	final ControllerStateManager manager = new ControllerStateManager();
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getClassLoader().getResource("fxml/Main.fxml"));
    	loader.setControllerFactory((Class<?> type) -> {
            try {
                for (Constructor<?> c : type.getConstructors()) {
                	if (c.getParameterCount() == 2 && c.getParameterTypes()[0] == ControllerStateManager.class && c.getParameterTypes()[1] == Model.class) {
                        return c.newInstance(manager, model);
                	}
                    else if (c.getParameterCount() == 1 && c.getParameterTypes()[0] == ControllerStateManager.class)
                        return c.newInstance(manager);

                }
                return type.newInstance();
            } catch (Exception exc) {
                throw new RuntimeException(exc);
            }
        });

        Parent root = loader.load();
    	primaryStage.setTitle("Pixelmon Spawn Editor");
    	primaryStage.setScene(new Scene(root));
    	primaryStage.show();
    }
}
