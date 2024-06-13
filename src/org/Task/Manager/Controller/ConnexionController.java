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
  import org.Task.Manager.Controller.*;


 public class ConnexionController{
   // La déclaration des composants fxml
   @FXML
   private TextField IdField;
   @FXML
   private PasswordField passField;

   // Après les composants fxml ,attaquons nous aux autres éléments importants
   private Stage primaryStage ;
   private User Utilisateur = new User();

   public void SetUser(User tango){
     this.Utilisateur=tango;
   }

   @FXML
   public void LogOut(MouseEvent event){
            this.primaryStage.close();
                                            }
  public void SetprimaryStage(Stage stage){
    this.primaryStage= stage;
            }


  @FXML
  private void Connexion(MouseEvent event){

            if(IdField.getText().isEmpty() || passField.getText().isEmpty()){
              Notifications builder = Notifications.create()
                                                  .title("Connexion Impossible")
                                                  .text("Les champs ne peuvent pas être vides")
                                                  .graphic(null)
                                                  .hideAfter(Duration.seconds(3))
                                                  .position(Pos.TOP_RIGHT);

            builder.darkStyle();
            builder.showError();
          }else{

            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManager","root","");
            System.out.println("Database Connection Successfuled");
            Statement srmt = con.createStatement();
            ResultSet rs = srmt.executeQuery("SELECT * FROM Utilisateur WHERE UserID='"+IdField.getText()+"';");

              while(rs.next()){

                Utilisateur.UserID= IdField.getText();
                Utilisateur.readAccess= rs.getString("ReadAccess");
                Utilisateur.writeAccess = rs.getString("WriteAccess");
                Utilisateur.ExecuteAcess = rs.getString("ExecuteAccess");
                Utilisateur.PassWord = rs.getString("PassWord");



                              }

          srmt.close();
          } catch (ClassNotFoundException ex){
          ex.printStackTrace();
          } catch (SQLException ex){
          ex.printStackTrace();
          }

            if( !passField.getText().equals(Utilisateur.PassWord)){
              Notifications builder = Notifications.create()
                                                  .title("Connexion Impossible")
                                                  .text("Identifiant ou Mot de Passe Incorrect")
                                                  .graphic(null)
                                                  .hideAfter(Duration.seconds(3))
                                                  .position(Pos.TOP_RIGHT);

            builder.darkStyle();
            builder.showError();
          }else{

            try {
              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/ControlProcess.fxml"));
              Pane root_pane = fxmlLoader.load();
              //tentons de créer les objets url et  ResourceBundle vides , donc nuls
              URL fm = null;
              ResourceBundle messages = null;
              //fin de création
              ControlProcessController controller = fxmlLoader.getController();
              controller.SetUser(this.Utilisateur);
              controller.SetprimaryStage(this.primaryStage);
              controller.SetUser(Utilisateur);
              controller.initialize(fm,messages);
              primaryStage.setTitle("Gestion Utilisateur");
              primaryStage.setScene(new Scene(root_pane, 1281,707));
              primaryStage.show();
            } catch(Exception e) {
              e.printStackTrace();
            }

          }
          }


          }
 }
