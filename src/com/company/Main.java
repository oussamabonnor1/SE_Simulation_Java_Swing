package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Main {


    public static void main(String[] args) {
        // write your code here
        ArrayList<Processus> file = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < 4; i++) {
            Processus p = new Processus(i + 1, r.nextInt(4), r.nextInt(5) + 1, r.nextInt(3), false);
            System.out.println("Processus " + p.getName() + " arrival time " + p.getArriveTime() + " cpu " + p.getCpuTime() + " Priority " + p.getPriority());
            file.add(p);
        }
        System.out.println();
        //firstComeFirstServe(file);
        //shortJobFirst(file);
        priorityNonPremptif(file);
    }

    static void firstComeFirstServe(ArrayList<Processus> file) {
        int currentTime = 0;
        ArrayList<Processus> waitingList = new ArrayList<>();

        while (file.size() > 0 && waitingList.size() > 0) {

            System.out.println("process filling phase:");

            for (int i = 0; i < file.size(); i++) {
                //if the processus are arriving
                if (file.get(i).getArriveTime() <= currentTime) {
                    waitingList.add(file.get(i));
                    file.remove(i);
                    System.out.println("the processus " + (i + 1) + " was added to the waiting list");
                }
            }
            System.out.println();

            System.out.println("process working phase:");
            if (waitingList.size() > 0) {
                System.out.println("processus " + waitingList.get(0).getName() + " is being processed");
                currentTime += waitingList.get(0).getCpuTime();
                waitingList.get(0).processing(waitingList.get(0).getCpuTime());
                System.out.println("processus " + waitingList.get(0).getName() + " left the system");
                waitingList.remove(0);
            } else {
                System.out.println("no Processus are waiting");
                ++currentTime;
            }

            System.out.println();

            if (file.size() > 0) {
                System.out.println("current time: " + currentTime);
                System.out.println("remaining processes: ");

                for (int i = 0; i < file.size(); i++) {
                    System.out.println("Processus " + file.get(i).getName());
                }

            } else {
                System.out.println("all processus are done");
            }
            System.out.println();
        }
        //temps que la list des processus n est pas vide (il y a des processus dans le tableau

    }

    static void shortJobFirst(ArrayList<Processus> file) {
        int currentTime = 0;
        int numberOfProcess = file.size();
        ArrayList<Processus> waitingList = new ArrayList<>();

        while (numberOfProcess > 0) {

            System.out.println("current time: " + currentTime);
            System.out.println();
            System.out.println("process filling phase:");

            for (int i = 0; i < file.size(); i++) {
                //if the processus are arriving
                if (file.get(i).getArriveTime() <= currentTime) {
                    System.out.println("the processus " + file.get(i).getName() + " was added to the waiting list");
                    waitingList.add(file.get(i));
                }
            }
            System.out.println();

            System.out.println("process working phase:");
            if (waitingList.size() > 0) {
                int processusToPass = 0;
                int shortestTime = waitingList.get(0).getCpuTime();

                for (int i = 0; i < waitingList.size(); i++) {
                    if (waitingList.get(i).getCpuTime() < shortestTime) {
                        processusToPass = i;
                        shortestTime = waitingList.get(i).getCpuTime();
                    }
                }

                System.out.println("processus " + waitingList.get(processusToPass).getName() + " is being processed...");
                waitingList.get(processusToPass).processing(1);

                if (waitingList.get(processusToPass).getCpuTime() <= 0) {
                    System.out.println("processus " + waitingList.get(processusToPass).getName() + " left the system");
                    waitingList.remove(processusToPass);
                    --numberOfProcess;
                }
                ++currentTime;

            } else {
                System.out.println("no Processus are waiting");
                ++currentTime;
            }
            //---------
            System.out.println();

            if (file.size() > 0) {
                System.out.println("remaining processes: ");
                boolean check = false;

                for (int i = 0; i < file.size(); i++) {
                    if (!file.get(i).isPassed()) {
                        System.out.println("Processus " + file.get(i).getName());
                        check = true;
                    }
                }

                if(!check){
                    System.out.println("No Proceses remained");
                }
            }
            System.out.println();
        }

    }

    static void priorityNonPremptif(ArrayList<Processus> file) {
        int currentTime = 0;
        int numberOfProcess = file.size();

        ArrayList<Processus> waitingList = new ArrayList<>();

        while (numberOfProcess > 0) {

            System.out.println("current time: " + currentTime);
            System.out.println();
            System.out.println("process filling phase:");

            for (int i = 0; i < file.size(); i++) {
                //if the processus are arriving
                if (file.get(i).getArriveTime() <= currentTime && !file.get(i).isPassed()) {
                    System.out.println("the processus " + file.get(i).getName() + " was added to the waiting list");
                    waitingList.add(file.get(i));
                    file.get(i).setPassed(true);
                }
            }

            System.out.println();

            System.out.println("process working phase:");
            if (waitingList.size() > 0) {
                int processusToPass = 0;
                int lowestPriority = waitingList.get(0).getPriority();

                for (int i = 0; i < waitingList.size(); i++) {
                    if (waitingList.get(i).getPriority() > lowestPriority) {
                        processusToPass = i;
                        lowestPriority = waitingList.get(i).getCpuTime();
                    }
                }

                currentTime += waitingList.get(processusToPass).getCpuTime();
                System.out.println("processus " + waitingList.get(processusToPass).getName() + " is being processed...");
                waitingList.get(processusToPass).processing(waitingList.get(processusToPass).getCpuTime());

                System.out.println("processus " + waitingList.get(processusToPass).getName() + " left the system");
                --numberOfProcess;
                waitingList.remove(processusToPass);

            } else {
                System.out.println("No processes available");
                currentTime++;
            }

            //---------
            System.out.println();

            if (file.size() > 0) {
                System.out.println("remaining processes: ");
                boolean check = false;

                for (int i = 0; i < file.size(); i++) {
                    if (!file.get(i).isPassed()) {
                        System.out.println("Processus " + file.get(i).getName());
                        check = true;
                    }
                }

                if(!check){
                    System.out.println("No Proceses remained");
                }
            }
            System.out.println();
        }

    }
}

