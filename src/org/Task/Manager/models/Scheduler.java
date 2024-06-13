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

 public class  Scheduler {
    // commençons avec ses attributs qui sont pour le moins atipyque
    private int SchedulID ;
    private Date DateFin;//Correspond à la date de clôture ou de fermeture de l'application
    private String Politique ;// Ici Correspond aux principales Politiques d'ordonnacement
    private Process job;//Ce sera le conteneur du processus qui sera chargé de communiquer le prochain processus à l'unité de traitement
    // Il ne faudra pas oublier que le l'ordonnanceur ici mentionné est crée au lancement de l'application , juste après le lancement de l'unité de traitement

    public Scheduler() {
      this.Politique="ShortesJobFirst";
      job = new Process();

    }

    public Scheduler(int SchedulID, Date DateFin, String Politique) {
        this.SchedulID = SchedulID;
        this.DateFin = DateFin;
        this.Politique = Politique;
    }
    // Attaquons nous ensuite aux getter et aux setter
    // D'abord les getter

    public int getSchedulID() {
        return SchedulID;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public String getPolitique() {
        return Politique;
    }
    // Ensuite attaquons nous aux setter , histoire de galvaniser les évènements

    public void setSchedulID(int SchedulID) {
        this.SchedulID = SchedulID;
    }

    public void setDateFin(Date DateFin) {
        this.DateFin = DateFin;
    }

    public void setPolitique(String Politique) {
        this.Politique = Politique;
    }
    // Enfin la méthode toString()

    @Override
    public String toString() {
        return "Scheduler{" + "SchedulID=" + SchedulID + ", DateFin=" + DateFin + ", Politique=" + Politique + '}';
    }

    // Attaquons nous à la méthode chargée de renvoyer le prochain processus dans la liste en fonction de la politique choisie





// la fin de la méthode










// La fin du Scheduler
 }
