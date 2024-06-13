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


 public class  ProcessSpool {

    private String PsId;
    private List<Process> job = new ArrayList<>();
    private int TempsExecTotal;
    private String DateFin;

    public ProcessSpool(String PsId, int TempsExecTotal, String DateFin) {
        this.PsId = PsId;
        this.TempsExecTotal = TempsExecTotal;
        this.DateFin = DateFin;
    }

    public ProcessSpool() {
    }

    public String getPsId() {
        return PsId;
    }

    public List<Process> getJob() {
        return job;
    }

    public int getTempsExecTotal() {
        return TempsExecTotal;
    }

    public String getDateFin() {
        return DateFin;
    }

    public void setPsId(String PsId) {
        this.PsId = PsId;
    }

    public void setJob(List<Process> job) {
        this.job = job;
    }

    public void setTempsExecTotal(int TempsExecTotal) {
        this.TempsExecTotal = TempsExecTotal;
    }

    public void setDateFin(String DateFin) {
        this.DateFin = DateFin;
    }
 
 }
