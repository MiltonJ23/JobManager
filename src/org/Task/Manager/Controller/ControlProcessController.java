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

 public class ControlProcessController implements Initializable{

   // Déclarons les composants fxml
  @FXML
  private Label UserNameField;
  @FXML
  private Label controlProcesslabl;
  @FXML
  private Label HistoProcesslabl;
  @FXML
  private Label HistoProcesslabl1;
  @FXML
  private Label LogOutlabl;
  @FXML
  private Label PIDLabl;
  @FXML
  private Label AjoutProcesslabl;
  @FXML
  private Label ProcessNameLabl;
  @FXML
  private Pane AjoutProcessPane;
  @FXML
  private Pane ControlProcessPane;
  @FXML
  private Pane HistoProcessPane;
  @FXML
  private Pane LogOutPane;
  @FXML
  private Pane HistoProcessPane1;
  @FXML
  private TextField hawkeye;
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
  private Button InfoProcessBu;
  @FXML
  private Button KillProcessBu;
  @FXML
  private Button SigkillProcessBu;
  @FXML
  private Button WaitProcessBu;
  @FXML
  private ComboBox PolitiqueBox;
  @FXML
  private AnchorPane trevor;

// On a fini avec les composants fxml
// Attaquons nous aux objets qui vont tout aussi nécessaire pour notre Controller
  private Stage primaryStage;
  private User Utilisateur=new User();

  // A présent attaquons les fonctions de nos différents objets

  public void SetprimaryStage(Stage stage){
        this.primaryStage= stage;
                            }
  public void SetUser(User tango){
      this.Utilisateur=tango;
                            }
  public void initialize(URL url,ResourceBundle bnc){
                              // Avant cela rassurons nous de bien placer le nom de l'utilisateur connecté à sa place
                                UserNameField.setText(Utilisateur.UserID);
                              // Commençons par remplir le ListView , mais je crois qu'un tableView serait plus approprié

                              // La tableView
                              //Commençons par metrre le pane à la couleur rouge , mais avant assurons nous que le Pane Gestion Personnel
                               HistoProcessPane.setStyle("-fx-background-color: #8e9c31");// #8e9c31
                               HistoProcesslabl.setStyle("-fx-text-fill: #ffffff");
                               ControlProcessPane.setStyle("-fx-background-color: #ffffff");
                               AjoutProcessPane.setStyle("-fx-background-color: #8e9c31");
                               controlProcesslabl.setStyle("-fx-text-fill: #8e9c31");
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
                                 ResultSet rs = srmt.executeQuery("SELECT * FROM Job WHERE State='StandBy' OR State='Ready' ");

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
                                     if(String.valueOf(Process.getPid()).toLowerCase().indexOf(SearchWord) > -1){
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
                                 System.out.println("Problème lors du chargement de la tableview 1");
                               }

                               // exécutons les tâches du timer
                               // Mais récupérons les informations du premier élement de la tableView
                               if(tablevision.getItems().size()>0){
                                 for(int i=0;i<=tablevision.getItems().size();i++){
                                   Process judy = findHighestPriorityProcess(tablevision.getItems());
                                   Timer timer = new Timer();
                                   TimerTask task = new TimerTask(){
                                     @Override
                                     public void run(){

                                       try {
                                         Class.forName("com.mysql.jdbc.Driver");
                                         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManager","root","");
                                         System.out.println("Database Connection Successfuled");
                                         String sequel="UPDATE Job SET State='Terminate' WHERE Pid=?";
                                         PreparedStatement stmt=con.prepareStatement(sequel);
                                         stmt.setString(1,String.valueOf(judy.Pid));
                                         stmt.executeUpdate();
                                         System.out.println("Execution Successfuled");
                                         ProcessNameLabl.setText(judy.getNom());
                                         PIDLabl.setText(String.valueOf(judy.getPid()));

                                         stmt.close();
                                       } catch (ClassNotFoundException ex) {
                                         ex.printStackTrace();
                                       } catch (SQLException ex) {
                                         ex.printStackTrace();
                                       }
                                       // Tentons de mettre l'ui à jour
                                       ObservableList<Process> damn = FXCollections.observableArrayList();
                                       // Connectons nous à la base de donnée pour récupérer nos objets InfoMarchandise
                                       try {
                                         Class.forName("com.mysql.jdbc.Driver");
                                         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManager","root","");
                                         System.out.println("Database Connection Successfuled");
                                         Statement srmt = con.createStatement();
                                         ResultSet rs = srmt.executeQuery("SELECT * FROM Job WHERE State='StandBy' OR State='Ready' ");

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
                                             if(String.valueOf(Process.getPid()).toLowerCase().indexOf(SearchWord) > -1){
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
                                         System.out.println("Problème lors du chargement de la tableview 2");
                                       }
                                     }
                                   };
                                   timer.schedule(task,judy.TempsExec*1000); }

                                 }else{
                                   Notifications builder = Notifications.create()
                                                                       .title("Unité d'exécution Vide")
                                                                       .text("Aucun Processus disponible")
                                                                       .graphic(null)
                                                                       .hideAfter(Duration.seconds(3))
                                                                       .position(Pos.TOP_RIGHT);


                                 builder.showWarning();
                                 }

                                 }

  // La fin du controller


 @FXML
 public void SeeInformation(MouseEvent event){
   if(!(tablevision.getSelectionModel().getSelectedItems().size() >0)){
     Notifications builder = Notifications.create()
                                         .title("Opération non-Autorisée")
                                         .text("Aucun Processus Sélectionné")
                                         .graphic(null)
                                         .hideAfter(Duration.seconds(3))
                                         .position(Pos.TOP_RIGHT);

   builder.darkStyle();
   builder.showError();
   }else {
     Process jaso = tablevision.getSelectionModel().getSelectedItems().get(0);
     // récupérins les informations de l'élément séléctionné
     Alert alert = new Alert(AlertType.INFORMATION);
     alert.setTitle("Informations sur le processus");
     alert.setHeaderText("Information on Job : "+jaso.Pid);
     // Mettons le contenu dans une String à part
     String content= "Process Pid="+jaso.Pid+"\t\t , Proocess Nom="+jaso.Nom+"\n"
                     + "Process Priority="+jaso.priorite+"\t\t , Process State="+jaso.State+"";
     alert.setContentText(content);
     alert.showAndWait();
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
      controller.SetUser(Utilisateur);
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
   HistoProcesslabl1.setStyle("-fx-text-fill: #ffffff");
   ControlProcessPane.setStyle("-fx-background-color: #8e9c31");
   controlProcesslabl.setStyle("-fx-text-fill: #ffffff");
   HistoProcessPane.setStyle("-fx-background-color: #8e9c31");
   HistoProcesslabl.setStyle("-fx-text-fill: #ffffff");
   AjoutProcessPane.setStyle("-fx-background-color: #ffffff");
   AjoutProcesslabl.setStyle("-fx-text-fill: #8e9c31");
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


// mettons en place des méthodes pour que certain utilisateurs n'aient pas droit à certain privilège
 @FXML
 public void KillProcessT(MouseEvent event){
   // Récupérons simplement l'information puis affichons là , les informations du processus sélectionné
   if(!(tablevision.getSelectionModel().getSelectedItems().size()>0)){
     Notifications builder = Notifications.create()
                                         .title("Opération Impossible")
                                         .text("Aucun Processus n'a été seléctionné")
                                         .graphic(null)
                                         .hideAfter(Duration.seconds(3))
                                         .position(Pos.TOP_RIGHT);

   builder.darkStyle();
   builder.showError();
   }else {
     if (!this.Utilisateur.ExecuteAcess.equals("x")) {
       Notifications builder = Notifications.create()
                                           .title("Opération non-Authorisée")
                                           .text("Vos droits ne vous permettent pas  cette suppression")
                                           .graphic(null)
                                           .hideAfter(Duration.seconds(3))
                                           .position(Pos.TOP_RIGHT);


     builder.showError();
     }else{
       Process tasha = tablevision.getSelectionModel().getSelectedItems().get(0);// On récupère le processus sélectionné
       try {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManager","root","");
         System.out.println("Database Connection Successfuled");
         String sequel="UPDATE Job SET State='Killed' WHERE Pid=?";
         PreparedStatement stmt=con.prepareStatement(sequel);
         stmt.setString(1,String.valueOf(tasha.Pid));
         stmt.executeUpdate();
         System.out.println("Destruction Successfuled");

         stmt.close();
       } catch (ClassNotFoundException ex) {
         ex.printStackTrace();
       } catch (SQLException ex) {
         ex.printStackTrace();
       }
       ObservableList<Process> damn = FXCollections.observableArrayList();
       // Connectons nous à la base de donnée pour récupérer nos objets InfoMarchandise
       try {
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManager","root","");
         System.out.println("Database Connection Successfuled");
         Statement srmt = con.createStatement();
         ResultSet rs = srmt.executeQuery("SELECT * FROM Job WHERE State='StandBy' OR State='Ready' ");

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
             if(String.valueOf(Process.getPid()).toLowerCase().indexOf(SearchWord) > -1){
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
         System.out.println("Problème lors du chargement de la tableview3 ");
       }

     }
   }

 }


 private Process findHighestPriorityProcess(ObservableList<Process> processes) {
     if (processes.isEmpty()) {
         return null;
     }

     Process highestPriorityProcess = processes.get(0); // Initialize with first element
     for (Process process : processes) {
         if (Integer.valueOf(process.getPriorite()) > Integer.valueOf(highestPriorityProcess.getPriorite())) {
             highestPriorityProcess = process;
         }
     }
     return highestPriorityProcess;
 }

@FXML
public void Close(MouseEvent event){
  this.primaryStage.close();
}





}
