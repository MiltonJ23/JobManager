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


 public class InfoUser {

   // commençons par créer les attributs de la classe

   public  String UserID ;
   public  String Nom;
   public  String DateIntegrationSystem;
   public  String ResuDroit;
   // Ensuite procédons à la création des constructeurs

    public InfoUser() {
        this.UserID = "";
        this.Nom = "";
        this.DateIntegrationSystem = "";
        this.ResuDroit="";
    }



    public InfoUser(String UserID, String Nom, String DateIntegrationSystem,  String ResuDroit) {
        this.UserID = UserID;
        this.Nom = Nom;
        this.DateIntegrationSystem = DateIntegrationSystem;
        this.ResuDroit=ResuDroit;
    }

    public String getUserID(){
      return this.UserID;
    }

    public String getNom(){
      return this.Nom;
    }

    public String getDateIntegrationSystem(){
      return this.DateIntegrationSystem;
    }

    public String getResuDroit(){
      return this.ResuDroit;
    }









 }
