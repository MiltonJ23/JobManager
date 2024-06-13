/*
 * This file is part of the Task Manager distribution
 * Copyright (c) 2024 Acheron Systems.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

 package org.Task.Manager.Controller;

 import java.util.*;
 import javafx.fxml.FXML;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Parent;
 import javafx.fxml.Initializable;
 import javafx.scene.layout.AnchorPane;
 import javafx.scene.layout.*;
 import javafx.scene.input.MouseEvent;
 import javafx.scene.Scene.*;
 import javafx.stage.Stage;
 import javafx.stage.*;
 import javafx.scene.*;
 import java.net.URL;
 import java.util.ResourceBundle;
 import org.controlsfx.control.Notifications;
 import javafx.util.Duration;
 import java.sql.*;
 import javafx.geometry.*;
 import javafx.scene.image.*;
 import java.time.*;
 import javafx.beans.property.*;
 import javafx.collections.*;
 import java.text.*;
 import javafx.scene.control.cell.*;
 import javafx.collections.transformation.*;
 import javafx.scene.control.*;
 import javafx.scene.control.cell.*;
 import javafx.scene.control.Alert.AlertType;
 import javafx.scene.control.*;
 import org.Task.Manager.models.*;
 import org.Task.Manager.Controller.*;


 public class AccueilController {
   // Les composants fxml
   @FXML
   private Button lancer;
   @FXML
   private Button laisser;

   // le stage
   private  Stage primaryStage;


   @FXML
   public void LogOut(MouseEvent event){
            this.primaryStage.close();
                        }

  public void SetprimaryStage(Stage stage){
        this.primaryStage= stage;
                            }


   @FXML
   public void lauchApp(MouseEvent event){
     try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/Page_de_Connexion.fxml"));
        Pane root_pane = fxmlLoader.load();
        ConnexionController controller = fxmlLoader.getController();
        controller.SetprimaryStage(this.primaryStage);
        primaryStage.setTitle("Accueil JobManager");
        primaryStage.setScene(new Scene(root_pane, 863, 610));
        primaryStage.show();
      } catch(Exception e) {
        e.printStackTrace();
      }
   }
 }
