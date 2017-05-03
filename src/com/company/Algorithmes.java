package com.company;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by Oussama on 19/04/2017.
 */
public class Algorithmes {
    ArrayList<Processus> file;

    public Algorithmes(ArrayList<Processus> file) {
        this.file = file;
    }

    protected void firstComeFirstServe(ArrayList<Processus> file, executionFrame frame) {
        int currentTime = 0;
        int numberOfProcess = file.size();
        ArrayList<Processus> waitingList = new ArrayList<>();

        DefaultTableModel processingModel = (DefaultTableModel) frame.processingTable.getModel();

        while (numberOfProcess > 0) {

            System.out.println("current time: " + currentTime);
            System.out.println("process filling phase:");

            for (int i = 0; i < file.size(); i++) {
                //if the processus are arriving
                if (file.get(i).getArriveTime() <= currentTime && !file.get(i).isPassed()) {
                    waitingList.add(file.get(i));
                    file.get(i).setPassed(true);
                    processingModel.setValueAt("" + currentTime, i, 1);
                }
            }
            System.out.println();

            System.out.println("process working phase:");
            if (waitingList.size() > 0) {
                System.out.println("processus " + waitingList.get(0).getName() + " is being processed");
                currentTime += waitingList.get(0).getCpuTime();
                waitingList.get(0).processing(waitingList.get(0).getCpuTime());
                System.out.println("processus " + waitingList.get(0).getName() + " left the system");

                int indexOfProcessLeaving = 0;
                while (waitingList.get(0).getName() != file.get(indexOfProcessLeaving).getName()) {
                    ++indexOfProcessLeaving;
                }

                processingModel.setValueAt("" + currentTime, indexOfProcessLeaving, 2);

                waitingList.remove(0);
                --numberOfProcess;
            } else {
                System.out.println("no Processus are waiting");
                ++currentTime;
            }

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

                if (!check) {
                    System.out.println("No Proceses remained");
                }
            }
            System.out.println();
            frame.repaint();
        }
        //temps que la list des processus n est pas vide (il y a des processus dans le tableau

    }

    protected void shortJobFirstPreemptif(ArrayList<Processus> file, executionFrame frame) {
        int currentTime = 0;
        int numberOfProcess = file.size();
        ArrayList<Processus> waitingList = new ArrayList<>();

        DefaultTableModel processingModel = (DefaultTableModel) frame.processingTable.getModel();

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

                    processingModel.setValueAt("" + currentTime, i, 1);
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

                    int indexOfProcessLeaving = 0;
                    while (waitingList.get(processusToPass).getName() != file.get(indexOfProcessLeaving).getName()) {
                        ++indexOfProcessLeaving;
                    }

                    System.out.println("processus " + waitingList.get(processusToPass).getName() + " left the system");
                    waitingList.remove(processusToPass);
                    --numberOfProcess;

                    processingModel.setValueAt("" + currentTime, indexOfProcessLeaving, 2);
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

                if (!check) {
                    System.out.println("No Proceses remained");
                }
            }
            System.out.println();
        }

    }

    protected void shortJobFirstNoPreemptif(ArrayList<Processus> file, executionFrame frame, int quantum) {
        int currentTime = 0;
        int numberOfProcess = file.size();
        ArrayList<Processus> waitingList = new ArrayList<>();

        DefaultTableModel processingModel = (DefaultTableModel) frame.processingTable.getModel();

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
                    processingModel.setValueAt("" + currentTime, i, 1);
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

                currentTime += quantum;
                System.out.println("processus " + waitingList.get(processusToPass).getName() + " is being processed...");
                waitingList.get(processusToPass).processing(quantum);

                if (waitingList.get(processusToPass).getCpuTime() <= 0) {

                    int indexOfProcessLeaving = 0;
                    while (waitingList.get(processusToPass).getName() != file.get(indexOfProcessLeaving).getName()) {
                        ++indexOfProcessLeaving;
                    }

                    System.out.println("processus " + waitingList.get(processusToPass).getName() + " left the system");
                    waitingList.remove(processusToPass);
                    --numberOfProcess;

                    processingModel.setValueAt("" + currentTime, indexOfProcessLeaving, 2);

                }

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

                if (!check) {
                    System.out.println("No Proceses remained");
                }
            }
            System.out.println();
        }

    }


    protected void priorityNonPremptif(ArrayList<Processus> file, executionFrame frame, int quantum) {
        int currentTime = 0;
        int numberOfProcess = file.size();

        ArrayList<Processus> waitingList = new ArrayList<>();

        DefaultTableModel processingModel = (DefaultTableModel) frame.processingTable.getModel();

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
                    processingModel.setValueAt("" + currentTime, i, 1);

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

                currentTime += quantum;
                System.out.println("processus " + waitingList.get(processusToPass).getName() + " is being processed...");
                waitingList.get(processusToPass).processing(quantum);

                int indexOfProcessLeaving = 0;
                while (waitingList.get(processusToPass).getName() != file.get(indexOfProcessLeaving).getName()) {
                    ++indexOfProcessLeaving;
                }

                processingModel.setValueAt("" + currentTime, indexOfProcessLeaving, 2);

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

                if (!check) {
                    System.out.println("No Proceses remained");
                }
            }
            System.out.println();
        }

    }

    protected void priorityPremptif(ArrayList<Processus> file, executionFrame frame) {
        int currentTime = 0;
        int numberOfProcess = file.size();

        DefaultTableModel processingModel = (DefaultTableModel) frame.processingTable.getModel();

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
                    processingModel.setValueAt("" + currentTime, i, 1);
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

                ++currentTime;
                System.out.println("processus " + waitingList.get(processusToPass).getName() + " is being processed...");
                waitingList.get(processusToPass).processing(1);

                if (waitingList.get(processusToPass).getCpuTime() <= 0) {
                    int indexOfProcessLeaving = 0;
                    while (waitingList.get(processusToPass).getName() != file.get(indexOfProcessLeaving).getName()) {
                        ++indexOfProcessLeaving;
                    }

                    processingModel.setValueAt("" + currentTime, indexOfProcessLeaving, 2);

                    System.out.println("processus " + waitingList.get(processusToPass).getName() + " left the system");
                    waitingList.remove(processusToPass);
                    --numberOfProcess;
                }

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

                if (!check) {
                    System.out.println("No Proceses remained");
                }
            }
            System.out.println();
        }

    }


    protected void roundRobinNonPreemptif(ArrayList<Processus> file, int quantum, executionFrame frame) {
        int currentTime = 0;
        int numberOfProcess = file.size();

        ArrayList<Processus> waitingList = new ArrayList<>();

        DefaultTableModel processingModel = (DefaultTableModel) frame.processingTable.getModel();

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
                    processingModel.setValueAt("" + currentTime, i, 1);
                }
            }

            System.out.println();

            System.out.println("process working phase:");
            if (waitingList.size() > 0) {

                currentTime += quantum;
                System.out.println("processus " + waitingList.get(0).getName() + " is being processed...");
                waitingList.get(0).processing(quantum);

                if (waitingList.get(0).getCpuTime() > 0) {
                    Processus p = waitingList.get(0);
                    //waitingList.add(waitingList.size() - 1, waitingList.get(0));
                    waitingList.remove(0);
                    waitingList.add(p);
                    System.out.println("processus " + waitingList.get(0).getName() + " is the head of the list");
                    System.out.println("processus " + waitingList.get(waitingList.size() - 1).getName() + " is the tail of the list");
                } else {
                    System.out.println("processus " + waitingList.get(0).getName() + " has left the system");
                    --numberOfProcess;
                    int indexOfProcessLeaving = 0;
                    while (waitingList.get(0).getName() != file.get(indexOfProcessLeaving).getName()) {
                        ++indexOfProcessLeaving;
                    }

                    processingModel.setValueAt("" + currentTime, indexOfProcessLeaving, 2);
                    waitingList.remove(0);
                }


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

                if (!check) {
                    System.out.println("No Proceses remained");
                }
            }
            System.out.println();
        }
    }
}
