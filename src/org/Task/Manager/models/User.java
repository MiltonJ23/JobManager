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

 package org.Task.Manager.models;

 import java.util.*;


 public class User {

   // commençons par créer les attributs de la classe

   public  String UserID ;
   public  String Nom;
   public  String DateIntegrationSystem;
   public  String readAccess;
   public  String writeAccess;
   public  String ExecuteAcess;
   public  String ResuDroit;
   public  String PassWord ;
   // Ensuite procédons à la création des constructeurs

    public User() {
        this.UserID = "";
        this.Nom = "";
        this.DateIntegrationSystem = "";
        this.readAccess = "";
        this.writeAccess = "";
        this.ExecuteAcess = "";
        this.ResuDroit=readAccess+writeAccess+ExecuteAcess;
    }

    public User(String UserID, String Nom, String DateIntegrationSystem, String readAccess, String writeAccess, String ExecuteAcess) {
        this.UserID = UserID;
        this.Nom = Nom;
        this.DateIntegrationSystem = DateIntegrationSystem;
        this.readAccess = readAccess;
        this.writeAccess = writeAccess;
        this.ExecuteAcess = ExecuteAcess;
        this.ResuDroit=readAccess+writeAccess+ExecuteAcess;
    }

    public User(String UserID, String Nom, String DateIntegrationSystem,  String ResuDroit) {
        this.UserID = UserID;
        this.Nom = Nom;
        this.DateIntegrationSystem = DateIntegrationSystem;
        this.ResuDroit=ResuDroit;
    }



   // à présent intéressons nous aux getter et aux setter

   // D'abord les getter
       public String getUserID() {
        return UserID;
    }

    public String getNom() {
        return Nom;
    }

    public String getDateIntegrationSystem() {
        return DateIntegrationSystem;
    }

    public String getReadAccess() {
        return readAccess;
    }

    public String getWriteAccess() {
        return writeAccess;
    }

    public String getExecuteAcess() {
        return ExecuteAcess;
    }

    // Ensuite les setter

     public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setDateIntegrationSystem(String DateIntegrationSystem) {
        this.DateIntegrationSystem = DateIntegrationSystem;
    }

    public void setReadAccess(String readAccess) {
        this.readAccess = readAccess;
    }

    public void setWriteAccess(String writeAccess) {
        this.writeAccess = writeAccess;
    }

    public void setExecuteAcess(String ExecuteAcess) {
        this.ExecuteAcess = ExecuteAcess;
    }
    public void setPassWord(String pass){
      this.PassWord=pass;
    }

    // Ensuite passons à la méthode toString()

    @Override
    public String toString() {
        return "User{" + "UserID=" + UserID + ", Nom=" + Nom + ", DateIntegrationSystem=" + DateIntegrationSystem + ", readAccess=" + readAccess + ", writeAccess=" + writeAccess + ", ExecuteAcess=" + ExecuteAcess + '}';
    }


 }
