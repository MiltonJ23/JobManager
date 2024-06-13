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
 import javafx.scene.control.*;
 import java.io.*;
 import org.Task.Manager.models.Process;
 import org.Task.Manager.models.*;

 public class HistorController implements Initializable{
   // Déclarons d'abord les composants fxml
   @FXML
   private AnchorPane tane;
   @FXML
   private Label DownloadLabl;
   @FXML
   private Label AjoutProcesslabl;
   @FXML
   private Label controlProcesslabl;
   @FXML
   private Label HistoProcesslabl;
   @FXML
   private Label LogOutlabl;
   @FXML
   private Label UserNameField;
   @FXML
   private TextField hawkeye;
   @FXML
   private Label HistoProcesslabl1;
   @FXML
   private TableView<Process> tablevision;
   @FXML
   private TableColumn<Process,Integer> PidColumn;
   @FXML
   private TableColumn<Process,String> NameColumn;
   @FXML
   private TableColumn<Process,Integer> ExecColumn;
   @FXML
   private TableColumn<Process,String> TaskColumn;
   @FXML
   private TableColumn<Process,String> StateColumn;
   @FXML
   private TableColumn<Process,String> PrioriteColumn;
   @FXML
   private TableColumn<Process,String> UserColumn;
   @FXML
   private Pane AjoutProcessPane;
   @FXML
   private Pane ControlProcessPane;
   @FXML
   private Pane HistoProcessPane;
   @FXML
   private Pane LogOutPane;
   @FXML
   private Pane DownloadPane;
   @FXML
   private Pane HistoProcessPane1;

   // Après la déclaration des objets fxml , il nous faut à présent les objets tout aussi important
   private Stage primaryStage;
   private User Utilisateur=new User();
   private FileChooser filecho= new FileChooser();

   public void initialize(URL url,ResourceBundle bnc){
     // Avant cela rassurons nous de bien placer le nom de l'utilisateur connecté à sa place
       UserNameField.setText(Utilisateur.UserID);
     // Commençons par remplir le ListView , mais je crois qu'un tableView serait plus approprié
     // Mais avant configurons d'abord le FileChooser
     filecho.setInitialDirectory(new File("/Users/apple/Downloads"));
     // La tableView
     //Commençons par metrre le pane à la couleur rouge , mais avant assurons nous que le Pane Gestion Personnel
      HistoProcessPane.setStyle("-fx-background-color: #ffffff");// #8e9c31
      HistoProcesslabl.setStyle("-fx-text-fill: #8e9c31");
      ControlProcessPane.setStyle("-fx-background-color: #8e9c31");
      AjoutProcessPane.setStyle("-fx-background-color: #8e9c31");
      controlProcesslabl.setStyle("-fx-text-fill: #ffffff");
      AjoutProcesslabl.setStyle("-fx-text-fill: #ffffff");
      // Tentons de créer une fonction qui va prendre un tableview , qui va charger les élements de  notre base de données et lui permettre de faire fonctionner le champ de recherche

      // On crée le composant qui va retenir les noms
      ObservableList<Process> damn = FXCollections.observableArrayList();
      // Connectons nous à la base de donnée pour récupérer nos objets InfoMarchandise
      try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManager","root","");
        System.out.println("Database Connection Successfuled");
        Statement srmt = con.createStatement();
        ResultSet rs = srmt.executeQuery("SELECT * FROM Job WHERE State='Terminate' OR State='Killed' ");

          while(rs.next()){
            int pod = rs.getInt("Pid");
            String name = rs.getString("Nom");
            int exec = rs.getInt("TempsExec");
            String taches = rs.getString("Task");
            String priori = rs.getString("Priorité");
            String etat = rs.getString("State");
            String util = rs.getString("Propriétaire");
            Process yoke= new Process(pod,name,exec,taches,priori,etat,util);
            damn.add(yoke);
                            }

        srmt.close();
      } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }


      // Après avoir reçu les élements de la base de donnée , concentrons nous à peupler le tableview
      try {
        // Préparons le terrain pour les élements qui vont être passés aux colonnes de notre tableview
        PidColumn.setCellValueFactory(new PropertyValueFactory<Process,Integer>("Pid"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<Process,String>("Nom"));
        ExecColumn.setCellValueFactory(new PropertyValueFactory<Process,Integer>("TempsExec"));
        TaskColumn.setCellValueFactory(new PropertyValueFactory<Process,String>("Task"));
        StateColumn.setCellValueFactory(new PropertyValueFactory<Process,String>("State"));
        PrioriteColumn.setCellValueFactory(new PropertyValueFactory<Process,String>("priorite"));
        UserColumn.setCellValueFactory(new PropertyValueFactory<Process,String>("OwnerName"));
        // Après avoir préparer les colonnes de notre tableview entamons le remplissage effectif de notre tableview
        tablevision.setItems(damn);

        // A présent rendons le textfield champ de recherche à la google
        // Initialisons la liste triée
        FilteredList<Process> FilteredData = new FilteredList<>(damn,b-> true);

        hawkeye.textProperty().addListener((observable,oldValue,newValue)->{
            FilteredData.setPredicate(Process ->{
              //Si La valeur de recherche est vide , on garde l'affichage qui était déjà là
              if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
                return true;
              }

              // Lowering the case to easy the search
            String SearchWord =  newValue.toLowerCase();
            if(String.valueOf(Process.Pid).toLowerCase().indexOf(SearchWord) > -1){
              return true;// Means that we found an occurence
            }else if(Process.getNom().toLowerCase().indexOf(SearchWord)> -1){
              return true;
            }else if(String.valueOf(Process.getTempsExec()).toLowerCase().indexOf(SearchWord)> -1){
              return true;
            }else if(Process.getTask().toLowerCase().indexOf(SearchWord)> -1){
              return true;
            }else if(Process.getPriorite().toLowerCase().indexOf(SearchWord)> -1){
              return true;
            }else if(Process.getState().toLowerCase().indexOf(SearchWord)> -1){
              return true;
            }else if(Process.getOwnerName().toLowerCase().indexOf(SearchWord)> -1){
              return true;
            }else {
              return false;// Means that no match was found
            }
          });
        });
          SortedList<Process> SortedData = new SortedList<>(FilteredData);

          // Bind sorted list with the tableview data
          SortedData.comparatorProperty().bind(tablevision.comparatorProperty());

          tablevision.setItems(SortedData);


      } catch(Exception e) {
        System.out.println("Problème lors du chargement de la tableview  X");
      }



// La fin de la méthode initialize
   }
   @FXML
   public void LogOut(MouseEvent event){
            this.primaryStage.close();
                                            }
   @FXML
   public void DownloadSon(MouseEvent event){
     // le code permettant de télécharger ou sauvegarder les fichiers
     Window tango =DownloadPane.getScene().getWindow();
     filecho.setTitle("Sauvegarder la liste des Processus");
     filecho.setInitialFileName("Liste_Processus");
      filecho.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text file","*.txt"),
        new FileChooser.ExtensionFilter("Pdf file","*.pdf"));




        try {
        File file  =  filecho.showSaveDialog(tango);
        filecho.setInitialDirectory(file.getParentFile());// Qui va mémoriser le chemin de l'ancien fichier
        ObservableList<Process> ec = tablevision.getItems();
        String dataToSave = "";
        for(Process proc:ec){
          dataToSave+=proc.toString()+"\n";
        }
        FileWriter writer = new FileWriter(file);
        writer.write(dataToSave);
        writer.close();
      } catch(IOException e) {
            e.printStackTrace();
        }
   }
   public void SetUser(User tango){
     this.Utilisateur=tango;
   }
   public void SetprimaryStage(Stage stage){
         this.primaryStage= stage;
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
                               if(!Utilisateur.ExecuteAcess.equals("x")){
                                 Notifications builder = Notifications.create()
                                                               .title("Accès Refusé ")
                                                               .text("Vous ne disposez pas de suffisament de droits requis:Execution")
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
                                    controller.SetUser(Utilisateur);
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
   public void ToNewProcess(MouseEvent event){
                               HistoProcessPane1.setStyle("-fx-background-color: #8e9c31");
                               HistoProcesslabl.setStyle("-fx-text-fill: #ffffff");
                               ControlProcessPane.setStyle("-fx-background-color: #8e9c31");
                               controlProcesslabl.setStyle("-fx-text-fill: #ffffff");
                               HistoProcessPane.setStyle("-fx-background-color: #8e9c31");
                               HistoProcesslabl.setStyle("-fx-text-fill: #ffffff");
                               AjoutProcessPane.setStyle("-fx-background-color: #ffffff");
                               AjoutProcesslabl.setStyle("-fx-text-fill: #8e9c31");
                               if (!Utilisateur.writeAccess.equals("w")) {
                                 Notifications builder = Notifications.create()
                                                               .title("Accès Refusé ")
                                                               .text("Vous ne disposez pas de suffisament de droits requis:Lecture")
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
                                    controller.SetUser(Utilisateur);
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
                               HistoProcesslabl1.setStyle("-fx-text-fill: #ffffff");
                               ControlProcessPane.setStyle("-fx-background-color: #ffffff");
                               controlProcesslabl.setStyle("-fx-text-fill: #8e9c31");
                               HistoProcessPane.setStyle("-fx-background-color: #8e9c31");
                               HistoProcesslabl.setStyle("-fx-text-fill: #ffffff");
                               AjoutProcessPane.setStyle("-fx-background-color: #8e9c31");
                               AjoutProcesslabl.setStyle("-fx-text-fill: #ffffff");
                               // Vérifions les droits avant de lancer la page
                               if(!(Utilisateur.readAccess.equals("r") && Utilisateur.ExecuteAcess.equals("x"))){
                                 Notifications builder = Notifications.create()
                                                               .title("Accès Refusé ")
                                                               .text("Vous ne disposez pas de suffisament de droits requis:Lecture-Exécution")
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
                                    controller.SetUser(Utilisateur);
                                    controller.initialize(fm,messages);
                                    primaryStage.setTitle("Gestion des processus");
                                    primaryStage.setScene(new Scene(root_pane, 1330, 707));
                                    primaryStage.show();
                                  } catch(Exception e) {
                                    e.printStackTrace();
                                  }
                               }
                             }

                             // La fin du controller
 }
