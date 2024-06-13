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



 public class NavigationController {
   // Déclarons les composants fxml qu'il va manipuler tout le long

   @FXML
   private Pane ControlProcessPane;
   @FXML
   private Pane AjoutProcessPane;
   @FXML
   private Pane HistoProcessPane;
   @FXML
   private Pane HistoProcessPane1;
   @FXML
   private Pane LogOutPane;

   @FXML
   private Label controlProcesslabl;
   @FXML
   private Label AjoutProcesslabl;
   @FXML
   private Label HistoProcesslabl;
   @FXML
   private Label HistoProcesslabl1;
   @FXML
   private Label LogOutlabl;

   private Stage primaryStage;
   private User Utilisateur= new User();

   public void SetUser(User avon){
     this.utlisateur=avon;
   }

   @FXML
   private void LogOut(MouseEvent event){
     this.primaryStage.close();
   }
   public void SetprimaryStage(Stage stage){
     this.primaryStage= stage;
             }

   // Après la validation des composants tentons de créer les méthodes qui vont nous permettre de naviguer entre les pages



   @FXML
   public void ToUserManagement(MouseEvent event){
     HistoProcessPane1.setStyle("-fx-background-color: #ffffff");
     HistoProcesslabl.setStyle("-fx-background-color: #8e9c31");
     ControlProcessPane.setStyle("-fx-background-color: #8e9c31");
     controlProcesslabl.setStyle("-fx-background-color: #ffffff");
     HistoProcessPane.setStyle("-fx-background-color: #8e9c31");
     HistoProcesslabl.setStyle("-fx-background-color: #ffffff");
     AjoutProcessPane.setStyle("-fx-background-color: #8e9c31");
     AjoutProcesslabl.setStyle("-fx-background-color: #ffffff");
     if(!Utilisateur.writeAccess.equals("x")){
       Notifications builder = Notifications.create()
                                     .title("Accès Refusé ")
                                     .text("Vous ne disposez pas de suffisament de droits")
                                     .graphic(null)
                                     .hideAfter(Duration.seconds(3))
                                     .position(Pos.TOP_RIGHT);

       builder.darkStyle();
       builder.showError();
     }else {
       try {
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/UserManagement.fxml"));
          Pane root_pane = fxmlLoader.load();
          //tentons de créer les objets url et  ResourceBundle vides , donc nuls
          URL fm = null;
          ResourceBundle messages = null;
          //fin de création
          InscriptionUtilisateurController controller = fxmlLoader.getController();
          controller.SetprimaryStage(this.primaryStage);
          controller.setUser(Utilisateur);
          controller.initialize(fm,messages);
          primaryStage.setTitle("Gestion des utilisateurs");
          primaryStage.setScene(new Scene(root_pane, 1281, 707));
          primaryStage.show();
        } catch(Exception e) {
          e.printStackTrace();
        }
     }
   }


   @FXML
   public void ToHistoriProcess(MouseEvent event){
     HistoProcessPane1.setStyle("-fx-background-color: #8e9c31");
     HistoProcesslabl.setStyle("-fx-background-color: #ffffff");
     ControlProcessPane.setStyle("-fx-background-color: #8e9c31");
     controlProcesslabl.setStyle("-fx-background-color: #ffffff");
     HistoProcessPane.setStyle("-fx-background-color: #ffffff");
     HistoProcesslabl.setStyle("-fx-background-color: #8e9c31");
     AjoutProcessPane.setStyle("-fx-background-color: #8e9c31");
     AjoutProcesslabl.setStyle("-fx-background-color: #ffffff");
     try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/HistoriqueProcess.fxml"));
        Pane root_pane = fxmlLoader.load();
        //tentons de créer les objets url et  ResourceBundle vides , donc nuls
        URL fm = null;
        ResourceBundle messages = null;
        //fin de création
        HistorController controller = fxmlLoader.getController();
        controller.SetprimaryStage(this.primaryStage);
        controller.setUser(Utilisateur);
        controller.initialize(fm,messages);
        primaryStage.setTitle("Historique Processus");
        primaryStage.setScene(new Scene(root_pane, 1330, 707));
        primaryStage.show();
      } catch(Exception e) {
        e.printStackTrace();
      }
   }


   @FXML
   public void ToNewProcess(MouseEvent event){
     HistoProcessPane1.setStyle("-fx-background-color: #8e9c31");
     HistoProcesslabl.setStyle("-fx-background-color: #ffffff");
     ControlProcessPane.setStyle("-fx-background-color: #8e9c31");
     controlProcesslabl.setStyle("-fx-background-color: #ffffff");
     HistoProcessPane.setStyle("-fx-background-color: #8e9c31");
     HistoProcesslabl.setStyle("-fx-background-color: #ffffff");
     AjoutProcessPane.setStyle("-fx-background-color: #ffffff");
     AjoutProcesslabl.setStyle("-fx-background-color: #8e9c31");
     if (!Utilisateur.writeAccess.equals("w")) {
       Notifications builder = Notifications.create()
                                     .title("Accès Refusé ")
                                     .text("Vous ne disposez pas de suffisament de droits")
                                     .graphic(null)
                                     .hideAfter(Duration.seconds(3))
                                     .position(Pos.TOP_RIGHT);

       builder.darkStyle();
       builder.showError();
     }else {
       try {
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/AjoutProcess.fxml"));
          Pane root_pane = fxmlLoader.load();
          //tentons de créer les objets url et  ResourceBundle vides , donc nuls
          URL fm = null;
          ResourceBundle messages = null;
          //fin de création
          AjoutProcessController controller = fxmlLoader.getController();
          controller.SetprimaryStage(this.primaryStage);
          controller.setUser(Utilisateur);
          controller.initialize(fm,messages);
          primaryStage.setTitle("Nouveau Processus");
          primaryStage.setScene(new Scene(root_pane, 1330, 707));
          primaryStage.show();
        } catch(Exception e) {
          e.printStackTrace();
        }
     }
   }


      @FXML
      public void ToControlProcess(MouseEvent event){
        HistoProcessPane1.setStyle("-fx-background-color: #8e9c31");
        HistoProcesslabl.setStyle("-fx-background-color: #ffffff");
        ControlProcessPane.setStyle("-fx-background-color: #ffffff");
        controlProcesslabl.setStyle("-fx-background-color: #8e9c31");
        HistoProcessPane.setStyle("-fx-background-color: #8e9c31");
        HistoProcesslabl.setStyle("-fx-background-color: #ffffff");
        AjoutProcessPane.setStyle("-fx-background-color: #8e9c31");
        AjoutProcesslabl.setStyle("-fx-background-color: #ffffff");
        // Vérifions les droits avant de lancer la page
        if(!(Utilisateur.readAccess.equals("r") && Utilisateur.ExecuteAcess.equals("x"))){
          Notifications builder = Notifications.create()
                                        .title("Accès Refusé ")
                                        .text("Vous ne disposez pas de suffisament de droits")
                                        .graphic(null)
                                        .hideAfter(Duration.seconds(3))
                                        .position(Pos.TOP_RIGHT);

          builder.darkStyle();
          builder.showError();
        }else {
          try {
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/ControlProcess.fxml"));
             Pane root_pane = fxmlLoader.load();
             //tentons de créer les objets url et  ResourceBundle vides , donc nuls
             URL fm = null;
             ResourceBundle messages = null;
             //fin de création
             ControlProcessController controller = fxmlLoader.getController();
             controller.SetprimaryStage(this.primaryStage);
             controller.setUser(Utilisateur);
             controller.initialize(fm,messages);
             primaryStage.setTitle("Nouveau Processus");
             primaryStage.setScene(new Scene(root_pane, 1330, 707));
             primaryStage.show();
           } catch(Exception e) {
             e.printStackTrace();
           }
        }
      }







// la fin du controler
   }
