package com.company;

/**
 * Created by Oussama on 19/04/2017.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class executionFrame extends JFrame {

    public JLabel lblTempsActuel;
    public JPanel contentPane;
    public int choiceOfAlgo;
    public int quantum;
    public JTable originalList;
    public JTable waitingList;
    public JTable processingList;
    public static ArrayList<Processus> file;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Frame frame = new Frame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * Create the frame.
     */

    void makeStuffWork() {
        Algorithmes algorithmes = new Algorithmes(file);
        switch (choiceOfAlgo) {
            case 0:
                System.out.println("first to come");
                algorithmes.firstComeFirstServe(algorithmes.file, this);
                break;
            case 1:
                System.out.println("shortest job");
                algorithmes.shortJobFirst(algorithmes.file);
                break;
            case 2:
                System.out.println("round robin");
                algorithmes.roundRobinNonPreemptif(algorithmes.file, quantum);
                break;
            case 3:
                System.out.println("priority");
                algorithmes.priorityNonPremptif(algorithmes.file);
                break;
            default:
                System.out.println("default");
                algorithmes.firstComeFirstServe(algorithmes.file, this);
                break;
        }

    }

    public executionFrame(ArrayList<Processus> file, int choiceOfAlgo, int quantum) {
        this.file = file;
        this.choiceOfAlgo = choiceOfAlgo;
        this.quantum = quantum;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1020, 610);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        originalList = new JTable();
        originalList.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "New column"
                }
        ));
        originalList.setBounds(12, 67, 123, 200);
        panel.add(originalList);

        waitingList = new JTable();
        waitingList.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "New column", "New column", "New column", "New column", "New column"
                }
        ));
        waitingList.setBounds(225, 67, 689, 174);
        panel.add(waitingList);

        processingList = new JTable();
        processingList.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "New column", "New column", "New column", "New column", "New column"
                }
        ));
        processingList.setBounds(225, 283, 689, 200);
        panel.add(processingList);

        JLabel lblListeTotal = new JLabel("Liste Total");
        lblListeTotal.setHorizontalAlignment(SwingConstants.CENTER);
        lblListeTotal.setBounds(12, 38, 123, 16);
        panel.add(lblListeTotal);

        JLabel lblFileDattente = new JLabel("file d'attente");
        lblFileDattente.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblFileDattente.setHorizontalAlignment(SwingConstants.CENTER);
        lblFileDattente.setBounds(499, 38, 123, 16);
        panel.add(lblFileDattente);

        JLabel lblCpu = new JLabel("CPU");
        lblCpu.setHorizontalAlignment(SwingConstants.CENTER);
        lblCpu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblCpu.setBounds(499, 254, 123, 16);
        panel.add(lblCpu);

        JButton btnCommencez = new JButton("Commencez");
        btnCommencez.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                makeStuffWork();
            }
        });
        btnCommencez.setBounds(225, 515, 103, 25);
        panel.add(btnCommencez);

        JButton btnAccelrer = new JButton("Accel\u00E9rer");
        btnAccelrer.setBounds(817, 515, 97, 25);
        panel.add(btnAccelrer);

        lblTempsActuel = new JLabel("Temps actuel:");
        lblTempsActuel.setHorizontalAlignment(SwingConstants.CENTER);
        lblTempsActuel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTempsActuel.setBounds(12, 518, 166, 16);
        panel.add(lblTempsActuel);

         DefaultTableModel model = (DefaultTableModel) originalList.getModel();
        for (int i = 0; i < file.size(); i++) {
            Object[] a = {"Processus " +file.get(i).getName()};
            model.addRow(a);
        }
    }
}


