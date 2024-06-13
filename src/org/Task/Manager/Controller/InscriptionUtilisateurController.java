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
 import javafx.scene.control.*;
 import javafx.scene.control.Alert.AlertType;
 import org.Task.Manager.models.*;
 import java.security.SecureRandom;

// le controlleur chargé des modifications liés aux utilisateurs
 public class InscriptionUtilisateurController implements Initializable{

 // Déclarons les composants fxml
  @FXML
  private AnchorPane trevor;
  @FXML
  private Label AjoutProcesslabl;
  @FXML
  private Label controlProcesslabl;
  @FXML
  private Label HistoProcesslabl;
  @FXML
  private Label HistoProcesslabl1;
  @FXML
  private Label LogOutlabl;
  @FXML
  private Label UserNameField;
  @FXML
  private Label UserManagLabl;
  @FXML
  private Pane AjoutProcessPane;
  @FXML
  private Pane ControlProcessPane;
  @FXML
  private Pane HistoProcessPane;
  @FXML
  private Pane LogOutPane;
  @FXML
  private Pane UserManagPane;
  @FXML
  private Pane HistoProcessPane1;
  @FXML
  private TableView<InfoUser> tablevision;
  @FXML
  private TableColumn<InfoUser,String> idColumn;
  @FXML
  private TableColumn<InfoUser,String> NameColumn;
  @FXML
  private TableColumn<InfoUser,String> IntegrationColumn;
  @FXML
  private TableColumn<InfoUser,String> DroitColumn;
  @FXML
  private MenuButton DroitMenuBu;
  @FXML
  private CheckMenuItem readItem;
  @FXML
  private CheckMenuItem WriteItem;
  @FXML
  private CheckMenuItem executeItem;
  @FXML
  private TextField hawkeye;
  @FXML
  private TextField IdField;
  @FXML
  private TextField NameField;
  @FXML
  private Button InsertBu;
  @FXML
  private Button ClearBu;
  @FXML
  private Button SupprimerUtilBu;
  // With this we finished the fxml parameters declarations
  private Stage primaryStage;
  private User Utilisateur= new User();

  // Now the fonctions
  @FXML
  public void Cleanser(MouseEvent event){
    IdField.setText("");
    NameField.setText("");
    clearMenuButtonSelections(DroitMenuBu);
  }

  public void clearMenuButtonSelections(MenuButton menuButton) {
    for (MenuItem item : menuButton.getItems()) {
        if (item instanceof CheckMenuItem) {
            ((CheckMenuItem) item).setSelected(false);
        }
    }
}

@FXML
public void Insertion(MouseEvent event){
  // Let's recover the value enter by the admin
  String ident = IdField.getText();
  String name = NameField.getText();
  ObservableList<MenuItem> barderlla = DroitMenuBu.getItems();
  // After checking if each of the CheckMenuItem is Selected get ready to address the values the represent
  Boolean readval = ((CheckMenuItem) barderlla.get(0)).isSelected();
  Boolean writeval=  ((CheckMenuItem) barderlla.get(1)).isSelected();
  Boolean Execteval= ((CheckMenuItem) barderlla.get(2)).isSelected();
  if(readval== false && writeval== false && Execteval == false){
    Notifications builder = Notifications.create()
                                    .title("Utilisateur Incorrect")
                                    .text("Vous devez sélectionner au moins un droit à l'utilisateur")
                                    .graphic(null)
                                    .hideAfter(Duration.seconds(3))
                                    .position(Pos.TOP_RIGHT);

builder.darkStyle();
builder.showError();

  }else{
    // Check the value of each of the item
    String read="";String write="";String exec="";
    read = readval ? "r" : "-";
    write = writeval ? "w" : "-";
    exec = Execteval ? "x" : "-";
    // After recovering the value of the DroitMenuBu , let's insert the new User in the Database
    try {
              Class.forName("com.mysql.jdbc.Driver");
              Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManager","root","");
              System.out.println("Database Connection Successfuled");
              String sqlque = "INSERT INTO Utilisateur VALUES(?,?,?,?,?,?,?);";
              PreparedStatement stmt = con.prepareStatement(sqlque);
              stmt.setString(1,ident);
              stmt.setString(2,name);
              stmt.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
              stmt.setString(4,read);
              stmt.setString(5,write);
              stmt.setString(6,exec);
              stmt.setString(7,generateur().get(1));
              stmt.executeUpdate();


            }catch (SQLException e) {
              e.printStackTrace();
            }catch (ClassNotFoundException ew) {
              ew.printStackTrace();
            }

    // After inserting the new user into the Database, let's actualize the tableView
              ObservableList<InfoUser> damn = FXCollections.observableArrayList();
              try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManager","root","");
                System.out.println("Database Connection Successfuled");
                Statement srmt = con.createStatement();
                ResultSet rs = srmt.executeQuery("SELECT * FROM Utilisateur ");

                  while(rs.next()){
                    String code = rs.getString("UserID");
                    String namei = rs.getString("Nom");
                    String integra = rs.getDate("DateIntégrationSystem").toString();
                    String re = rs.getString("ReadAccess");
                    String wr = rs.getString("WriteAccess");
                    String ex = rs.getString("ExecuteAccess");
                    String resu = re+wr+ex;
                    InfoUser yoke= new InfoUser(code,namei,integra,resu);
                    damn.add(yoke);
                                    }

                srmt.close();
              } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
              } catch (SQLException ex) {
                ex.printStackTrace();
              };

          try {
          // Préparons le terrain pour les élements qui vont être passés aux colonnes de notre tableview
          idColumn.setCellValueFactory(new PropertyValueFactory<InfoUser,String>("UserID"));
          NameColumn.setCellValueFactory(new PropertyValueFactory<InfoUser,String>("Nom"));
          IntegrationColumn.setCellValueFactory(new PropertyValueFactory<InfoUser,String>("DateIntegrationSystem"));
          DroitColumn.setCellValueFactory(new PropertyValueFactory<InfoUser,String>("ResuDroit"));
          // Après avoir préparer les colonnes de notre tableview entamons le remplissage effectif de notre tableview
          tablevision.setItems(damn);

          // A présent rendons le textfield champ de recherche à la google
          // Initialisons la liste triée
          FilteredList<InfoUser> FilteredData = new FilteredList<>(damn,b-> true);

          hawkeye.textProperty().addListener((observable,oldValue,newValue)->{
          FilteredData.setPredicate(InfoUser ->{
            //Si La valeur de recherche est vide , on garde l'affichage qui était déjà là
            if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
              return true;
            }

            // Lowering the case to easy the search
          String SearchWord =  newValue.toLowerCase();
          if(InfoUser.getUserID().toLowerCase().indexOf(SearchWord)> -1){
            return true;// Means that we found an occurence
          }else if(InfoUser.getNom().toLowerCase().indexOf(SearchWord)> -1){
            return true;
          }else if(InfoUser.getDateIntegrationSystem().toLowerCase().indexOf(SearchWord)> -1){
            return true;
          }else if(InfoUser.getResuDroit().toLowerCase().indexOf(SearchWord)> -1){
            return true;
          }else {
            return false;// Means that no match was found
          }
          });
          });
          SortedList<InfoUser> SortedData = new SortedList<>(FilteredData);

          // Bind sorted list with the tableview data
          SortedData.comparatorProperty().bind(tablevision.comparatorProperty());
          tablevision.setItems(SortedData);

          }catch (Exception e) {
          e.printStackTrace();
          }
// The end of the if statement
  }



// The end of the fonction
}

public void initialize(URL ln,ResourceBundle bn){
// Avant cela rassurons nous de bien placer le nom de l'utilisateur connecté à sa place
  UserNameField.setText(Utilisateur.UserID);
// Tentons de récupérer les élements de la base de données
// A présent actualisons l'affichage de la TableView
          ObservableList<InfoUser> damn = FXCollections.observableArrayList();
          try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManager","root","");
            System.out.println("Database Connection Successfuled");
            Statement srmt = con.createStatement();
            ResultSet rs = srmt.executeQuery("SELECT * FROM Utilisateur ");

              while(rs.next()){
                String code = rs.getString("UserID");
                String name = rs.getString("Nom");
                String integra = rs.getDate("DateIntégrationSystem").toString();
                String re = rs.getString("ReadAccess");
                String wr = rs.getString("WriteAccess");
                String ex = rs.getString("ExecuteAccess");
                String resu = re+wr+ex;
                InfoUser yoke= new InfoUser(code,name,integra,resu);
                damn.add(yoke);
                                }

            srmt.close();
          } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
          } catch (SQLException ex) {
            ex.printStackTrace();
          };

  try {
  // Préparons le terrain pour les élements qui vont être passés aux colonnes de notre tableview
  idColumn.setCellValueFactory(new PropertyValueFactory<InfoUser,String>("UserID"));
  NameColumn.setCellValueFactory(new PropertyValueFactory<InfoUser,String>("Nom"));
  IntegrationColumn.setCellValueFactory(new PropertyValueFactory<InfoUser,String>("DateIntegrationSystem"));
  DroitColumn.setCellValueFactory(new PropertyValueFactory<InfoUser,String>("ResuDroit"));
  // Après avoir préparer les colonnes de notre tableview entamons le remplissage effectif de notre tableview
  tablevision.setItems(damn);

  // A présent rendons le textfield champ de recherche à la google
  // Initialisons la liste triée
  FilteredList<InfoUser> FilteredData = new FilteredList<>(damn,b-> true);

  hawkeye.textProperty().addListener((observable,oldValue,newValue)->{
      FilteredData.setPredicate(InfoUser ->{
        //Si La valeur de recherche est vide , on garde l'affichage qui était déjà là
        if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
          return true;
        }

        // Lowering the case to easy the search
      String SearchWord =  newValue.toLowerCase();
      if(InfoUser.getUserID().toLowerCase().indexOf(SearchWord)> -1){
        return true;// Means that we found an occurence
      }else if(InfoUser.getNom().toLowerCase().indexOf(SearchWord)> -1){
        return true;
      }else if(InfoUser.getDateIntegrationSystem().toLowerCase().indexOf(SearchWord)> -1){
        return true;
      }else if(InfoUser.getResuDroit().toLowerCase().indexOf(SearchWord)> -1){
        return true;
      }else {
        return false;// Means that no match was found
      }
    });
  });
    SortedList<InfoUser> SortedData = new SortedList<>(FilteredData);

    // Bind sorted list with the tableview data
    SortedData.comparatorProperty().bind(tablevision.comparatorProperty());
  tablevision.setItems(SortedData);

}catch (Exception e) {
  e.printStackTrace();
}
// la fin de la méthode initialize
 }

 @FXML
 public void Suppression(MouseEvent event){
   if(!(tablevision.getSelectionModel().getSelectedItems().size() > 0)){
          Notifications builder = Notifications.create()
                                              .title("Suppression Utilisateur Impossible")
                                              .text("Aucun utilisateur n'a été sélectionné")
                                              .graphic(null)
                                              .hideAfter(Duration.seconds(3))
                                              .position(Pos.TOP_RIGHT);

        builder.darkStyle();
        builder.showError();

      }else{
        InfoUser tem = tablevision.getSelectionModel().getSelectedItems().get(0);
        // Allons l'enlever dans la base de données
        try {
              Class.forName("com.mysql.jdbc.Driver");
              Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManager","root","");
              System.out.println("Database Connection Successfuled");

              String stm="DELETE FROM Utilisateur WHERE UserID=?";
              PreparedStatement stmt = con.prepareStatement(stm);
              stmt.setString(1,tem.UserID);
              stmt.executeUpdate();
              System.out.println("Suppression Completed ");

            } catch(ClassNotFoundException e) {
              e.printStackTrace();
            } catch (SQLException ew) {
              ew.printStackTrace();
            }
            System.out.println("Suppression Completed");
// Then let's actualize the list of Users
              ObservableList<InfoUser> damn = FXCollections.observableArrayList();
              try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TaskManager","root","");
                System.out.println("Database Connection Successfuled");
                Statement srmt = con.createStatement();
                ResultSet rs = srmt.executeQuery("SELECT * FROM Utilisateur ");

                  while(rs.next()){
                    String code = rs.getString("UserID");
                    String name = rs.getString("Nom");
                    String integra = rs.getDate("DateIntégrationSystem").toString();
                    String re = rs.getString("ReadAccess");
                    String wr = rs.getString("WriteAccess");
                    String ex = rs.getString("ExecuteAccess");
                    String resu = re+wr+ex;
                    InfoUser yoke= new InfoUser(code,name,integra,resu);
                    damn.add(yoke);
                                    }

                srmt.close();
              } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
              } catch (SQLException ex) {
                ex.printStackTrace();
              };

              try {
              // Préparons le terrain pour les élements qui vont être passés aux colonnes de notre tableview
              idColumn.setCellValueFactory(new PropertyValueFactory<InfoUser,String>("UserID"));
              NameColumn.setCellValueFactory(new PropertyValueFactory<InfoUser,String>("Nom"));
              IntegrationColumn.setCellValueFactory(new PropertyValueFactory<InfoUser,String>("DateIntegrationSystem"));
              DroitColumn.setCellValueFactory(new PropertyValueFactory<InfoUser,String>("ResuDroit"));
              // Après avoir préparer les colonnes de notre tableview entamons le remplissage effectif de notre tableview
              tablevision.setItems(damn);

              // A présent rendons le textfield champ de recherche à la google
              // Initialisons la liste triée
              FilteredList<InfoUser> FilteredData = new FilteredList<>(damn,b-> true);

              hawkeye.textProperty().addListener((observable,oldValue,newValue)->{
              FilteredData.setPredicate(InfoUser ->{
              //Si La valeur de recherche est vide , on garde l'affichage qui était déjà là
              if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
              return true;
              }

              // Lowering the case to easy the search
              String SearchWord =  newValue.toLowerCase();
              if(InfoUser.getUserID().toLowerCase().indexOf(SearchWord)> -1){
              return true;// Means that we found an occurence
            }else if(InfoUser.getNom().toLowerCase().indexOf(SearchWord)> -1){
              return true;
            }else if(InfoUser.getDateIntegrationSystem().toLowerCase().indexOf(SearchWord)> -1){
              return true;
            }else if(InfoUser.getResuDroit().toLowerCase().indexOf(SearchWord)> -1){
              return true;
              }else {
              return false;// Means that no match was found
              }
              });
              });
              SortedList<InfoUser> SortedData = new SortedList<>(FilteredData);

              // Bind sorted list with the tableview data
              SortedData.comparatorProperty().bind(tablevision.comparatorProperty());
              tablevision.setItems(SortedData);

              }catch (Exception e) {
              e.printStackTrace();
              }
            }
      }


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
  public void ToUserManagement(MouseEvent event){
                                  HistoProcessPane1.setStyle("-fx-background-color: #ffffff");
                                  HistoProcesslabl.setStyle("-fx-text-fill: #8e9c31");
                                  ControlProcessPane.setStyle("-fx-background-color: #8e9c31");
                                  controlProcesslabl.setStyle("-fx-text-fill: #ffffff");
                                  HistoProcessPane.setStyle("-fx-background-color: #8e9c31");
                                  HistoProcesslabl.setStyle("-fx-text-fill: #ffffff");
                                  AjoutProcessPane.setStyle("-fx-background-color: #8e9c31");
                                  AjoutProcesslabl.setStyle("-fx-text-fill: #ffffff");
                                  if(!Utilisateur.writeAccess.equals("w")){
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

                                public List<String> generateur(){
                                   int ID_LENGTH = 8; // Adjust as needed
                                   int PASSWORD_LENGTH = 12; // Adjust as needed

                                   String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                                   Random random = new SecureRandom();

                                  List<String> passid = new ArrayList<>();
                                  // ON va tenter de stocker d'une part l'identifiant
                                  StringBuilder idBuilder = new StringBuilder();
                                    for (int i = 0; i < ID_LENGTH; i++) {
                                        idBuilder.append(ALPHA_NUMERIC_STRING.charAt(random.nextInt(ALPHA_NUMERIC_STRING.length())));
                                    };
                                    passid.add(idBuilder.toString());// On finit le stockage de l'identifiant
                                    // Procédons au générateur de mot de passe
                                    StringBuilder passwordBuilder = new StringBuilder();
                                    for (int i = 0; i < PASSWORD_LENGTH; i++) {
                                        passwordBuilder.append(ALPHA_NUMERIC_STRING.charAt(random.nextInt(ALPHA_NUMERIC_STRING.length())));
                                    };
                                    passid.add(passwordBuilder.toString());
                                    return passid;
                                }


// la fin du controller
  }
