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


 public class  Process implements Comparable<Process>{


    // Commençons par lrs attributs
    public  int Pid;
    public String Nom;
    public int TempsExec;// Le temps ici en secondes , mais en réalité le temps est essentiellement 1/50e de secondes
    public String Task;
    public String priorite;
    public String State;
    public String OwnerName ;

    // Ensuite attaquons nous aux constructeurs

    public Process() {
      this.Pid=-1;
      this.Nom="";
      this.TempsExec=-1;
      this.Task="";
      this.priorite="-1";
      this.State="";
       this.OwnerName = "";
    }

    public Process(int Pid, String Nom, int TempsExec, String Task, String priorite, String State) {
        this.Pid = Pid;
        this.Nom = Nom;
        this.TempsExec = TempsExec;
        this.Task = Task;
        this.priorite = priorite;
        this.State = State;

    }
    public Process(int pid,String nom,int exc,String task,String prio,String sta,String ownerId){
      this.Pid = pid;
      this.Nom = nom;
      this.TempsExec = exc;
      this.Task = task;
      this.priorite = prio;
      this.State = sta;
      this.OwnerName = ownerId;
    }


    // Procédons également avec les getter et les setter
    // Débutons avec les getter

    public int getPid() {
        return Pid;
    }

    public String getNom() {
        return Nom;
    }

    public int getTempsExec() {
        return TempsExec;
    }

    public String getTask() {
        return Task;
    }

    public String getPriorite() {
        return priorite;
    }

    public String getState() {
        return State;
    }

    public String getOwnerName(){
      return this.OwnerName;
    }



    // Poursuivons avec les setter

    public void setPid(int Pid) {
        this.Pid = Pid;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setTempsExec(int TempsExec) {
        this.TempsExec = TempsExec;
    }

    public void setTask(String Task) {
        this.Task = Task;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public void setState(String State) {
        this.State = State;
    }







    // Et enfin la méthode toString()

    @Override
    public String toString() {
        return "Process \n{" + "Pid=" + Pid + ", \nNom=" + Nom + ", TempsExec=" + TempsExec + ", \nTask=" + Task + ", priorite=" + priorite + ", \nState=" + State  + ", \n Owner=" + OwnerName + "}\n\n";
    }
// Maintenant implémentons la méthode de comparaison pour pouvoir faire le choix qua
    @Override
    public int compareTo(Process t){
      return this.TempsExec-t.getTempsExec();
    }

 }
