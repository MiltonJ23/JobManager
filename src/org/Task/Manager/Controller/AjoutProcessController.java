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
 import java.util.Random;
 import javafx.fxml.FXML;
 import javafx.fxml.Initializable;
 import javafx.scene.layout.AnchorPane;
 import javafx.scene.control.*;
 import javafx.scene.layout.*;
 import javafx.scene.input.MouseEvent;
 import javafx.stage.Stage;
 import javafx.stage.*;
 import java.net.URL;
 import java.util.ResourceBundle;
 import org.controlsfx.control.Notifications;
 import javafx.util.Duration;
 import java.sql.*;
 import javafx.scene.Scene.*;
 import javafx.scene.*;
 import javafx.fxml.FXMLLoader;
 import javafx.scene.Parent;
 import javafx.geometry.*;
 import org.Task.Manager.models.*;
 import javafx.beans.property.*;
 import javafx.collections.*;
 import java.text.*;
 import javafx.scene.image.*;
 import java.time.*;
 import javafx.scene.control.cell.*;
 import javafx.collections.transformation.*;
 import javafx.scene.control.cell.*;
 import javafx.scene.control.Alert.AlertType;
 import java.io.*;
 import javafx.scene.control.*;
 import org.Task.Manager.models.Process;
 import org.Task.Manager.models.*;

 public  class AjoutProcessController implements Initializable{
   // The description of the fxml components
  @FXML
  private AnchorPane pane;
  @FXML
  private TextField NameField;
  @FXML
  private TextArea TaskField;
  @FXML
  private TextField ProcessTimeField;
  @FXML
  private ComboBox PriorityField;
  @FXML
  private Button PushBu;
  @FXML
  private Button ClearBu;
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
  @FXML
  private Label UserNameField;

  // On a fini de déclarer les composants fxml donc Maintenant lançons dans les composants tout aussi nécessaire
  private User utlisateur = new User();
  private Stage primaryStage;

  public void SetUser(User tango){
    this.utlisateur=tango;
  }
  public void SetprimaryStage(Stage stage){
        this.primaryStage= stage;
                            }
  // A présent passon aux méthodes , mais commençons par la méthode initialize
  public void initialize(URL ln,ResourceBundle bnd){
    HistoProcessPane1.setStyle("-fx-background-color: #8e9c31");
    HistoProcesslabl1.setStyle("-fx-text-fill: #ffffff");
    ControlProcessPane.setStyle("-fx-background-color: #8e9c31");
    controlProcesslabl.setStyle("-fx-text-fill: #ffffff");
    HistoProcessPane.setStyle("-fx-background-color: #8e9c31");
    HistoProcesslabl.setStyle("-fx-text-fill: #ffffff");
    AjoutProcessPane.setStyle("-fx-background-color: #ffffff");
    AjoutProcesslabl.setStyle("-fx-text-fill: #8e9c31");
    ObservableList<String> term = FXCollections.observableArrayList("0","1","2","3","4","5","6","7","8","9");
    PriorityField.setItems(term);
    // Avant cela rassurons nous de bien placer le nom de l'utilisateur connecté à sa place
      UserNameField.setText(utlisateur.UserID);


  }


  @FXML
  private  void Clear(MouseEvent event){
    NameField.setText("");
    TaskField.setText("");
    PriorityField.getSelectionModel().select(-1);
    ProcessTimeField.setText("");
  }

  @FXML
  private void InserProcess(MouseEvent event){
    if(NameField.getText().isEmpty() || TaskField.getText().isEmpty() || PriorityField.getSelectionModel().isEmpty() || TaskField.getText().isEmpty()){
                Notifications builder = Notifications.create()
                                              .title("Création Processus Invalide ")
                                              .text("Les champs ne peuvent pas être vides")
                                              .graphic(null)
                                              .hideAfter(Duration.seconds(3))
                                              .position(Pos.TOP_RIGHT);

                builder.darkStyle();
                builder.showError();
    }else{
      String name = NameField.getText();
      String tasks = TaskField.getText();
      String prito = PriorityField.getSelectionModel().getSelectedItem().toString();
      String proctime = ProcessTimeField.getText();
      int  exectime = Integer.valueOf(ProcessTimeField.getText());

      try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManager","root","");
                System.out.println("Database Connection Successfuled");
                String sqlque = "INSERT INTO Job VALUES(?,?,?,?,?,?,?,?);";
                PreparedStatement stmt = con.prepareStatement(sqlque);
                Random random = new Random();

                stmt.setInt(1,random.nextInt(10000));
                stmt.setString(2,name);
                stmt.setString(3,proctime);
                stmt.setString(4,tasks);
                stmt.setString(5,prito);
                stmt.setString(6,"Ready");
                stmt.setTimestamp(7,new Timestamp(System.currentTimeMillis()));
                stmt.setString(8,utlisateur.UserID);
                stmt.executeUpdate();
                System.out.println("Insertion Completed");

              }catch (SQLException e) {
                e.printStackTrace();
              }catch (ClassNotFoundException ew) {
                ew.printStackTrace();
              }

    }
  }


  @FXML
  private void LogOut(MouseEvent event){
    this.primaryStage.close();
  }

  @FXML
  public void ToUserManagement(MouseEvent event){
    HistoProcessPane1.setStyle("-fx-background-color: #ffffff");
    HistoProcesslabl1.setStyle("-fx-text-fill: #8e9c31");
    ControlProcessPane.setStyle("-fx-background-color: #8e9c31");
    controlProcesslabl.setStyle("-fx-text-fill: #ffffff");
    HistoProcessPane.setStyle("-fx-background-color: #8e9c31");
    HistoProcesslabl.setStyle("-fx-text-fill: #ffffff");
    AjoutProcessPane.setStyle("-fx-background-color: #8e9c31");
    AjoutProcesslabl.setStyle("-fx-text-fill: #ffffff");
    if(!utlisateur.writeAccess.equals("x")){
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
         controller.SetUser(utlisateur);
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
    HistoProcesslabl.setStyle("-fx-text-fill: #ffffff");
    ControlProcessPane.setStyle("-fx-background-color: #8e9c31");
    controlProcesslabl.setStyle("-fx-text-fill: #ffffff");
    HistoProcessPane.setStyle("-fx-background-color: #ffffff");
    HistoProcesslabl.setStyle("-fx-text-fill: #8e9c31");
    AjoutProcessPane.setStyle("-fx-background-color: #8e9c31");
    AjoutProcesslabl.setStyle("-fx-text-fill: #ffffff");
    try {
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/HistoriqueProcess.fxml"));
       Pane root_pane = fxmlLoader.load();
       //tentons de créer les objets url et  ResourceBundle vides , donc nuls
       URL fm = null;
       ResourceBundle messages = null;
       //fin de création
       HistorController controller = fxmlLoader.getController();
       controller.SetprimaryStage(this.primaryStage);
       controller.SetUser(utlisateur);
       controller.initialize(fm,messages);
       primaryStage.setTitle("Historique Processus");
       primaryStage.setScene(new Scene(root_pane, 1330, 707));
       primaryStage.show();
     } catch(Exception e) {
       e.printStackTrace();
     }
  }
  @FXML
  public void ToControlProcess(MouseEvent event){
    HistoProcessPane1.setStyle("-fx-background-color: #8e9c31");
    HistoProcesslabl.setStyle("-fx-text-fill: #ffffff");
    ControlProcessPane.setStyle("-fx-background-color: #ffffff");
    controlProcesslabl.setStyle("-fx-text-fill: #8e9c31");
    HistoProcessPane.setStyle("-fx-background-color: #8e9c31");
    HistoProcesslabl.setStyle("-fx-text-fill: #ffffff");
    AjoutProcessPane.setStyle("-fx-background-color: #8e9c31");
    AjoutProcesslabl.setStyle("-fx-text-fill: #ffffff");
    // Vérifions les droits avant de lancer la page
    if(!(this.utlisateur.ExecuteAcess.equals("x"))){
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
         controller.SetUser(utlisateur);
         controller.initialize(fm,messages);
         primaryStage.setTitle("Nouveau Processus");
         primaryStage.setScene(new Scene(root_pane, 1330, 707));
         primaryStage.show();
       } catch(Exception e) {
         e.printStackTrace();
       }
    }
  }

// La fin du controller
 }
