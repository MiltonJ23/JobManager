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

 public class  ProcessUnit {
   // Attaquons nous aux attributs
   private String PuId;
   private String DateFin;
   private int BurstTime;

    public ProcessUnit() {
        this.PuId="";
        this.DateFin="";
    }

    public String getPuId() {
        return PuId;
    }

    public String getDateFin() {
        return DateFin;
    }

    public void setPuId(String PuId) {
        this.PuId = PuId;
    }

    public void setDateFin(String DateFin) {
        this.DateFin = DateFin;
    }

    @Override
    public String toString() {
        return "ProcessUnit{" + "PuId=" + PuId + ", DateFin=" + DateFin + '}';
    }





 }
