package com.company;

import javafx.scene.text.Font;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Random;

public class Main {


    public static void main(String[] args) {
        // write your code here

        Frame f = new Frame();
        f.setVisible(true);

        ArrayList<Processus> file = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < f.table.getRowCount(); i++) {
            //DefaultTableModel model = (DefaultTableModel) f.table.getModel();

            //Processus p = new Processus((int) model.getValueAt(i, 0), (int) model.getValueAt(i, 1), (int) model.getValueAt(i, 2), (int) model.getValueAt(i, 3), false);

            Processus p = new Processus(i + 1, r.nextInt(4), r.nextInt(5) + 1, r.nextInt(3), false);
            System.out.println("Processus " + p.getName() + " arrival time " + p.getArriveTime() + " cpu " + p.getCpuTime() + " Priority " + p.getPriority());
            file.add(p);
        }
        System.out.println();

        //firstComeFirstServe(file);
        //shortJobFirst(file);
        //priorityNonPremptif(file);
        //roundRobinNonPreemptif(file, 1);


    }


}

