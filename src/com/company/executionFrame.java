package com.company;

/**
 * Created by Oussama on 19/04/2017.
 */

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class executionFrame extends JFrame {


    public JPanel contentPane;
    public int choiceOfAlgo;
    public int quantum;
    private JTable table;
    private JTable table_1;
    private JTable table_2;
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
                algorithmes.firstComeFirstServe(algorithmes.file);
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
                algorithmes.firstComeFirstServe(algorithmes.file);
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

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "New column"
                }
        ));
        table.setBounds(12, 67, 123, 200);
        panel.add(table);

        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "New column", "New column", "New column", "New column", "New column"
                }
        ));
        table_1.setBounds(225, 67, 689, 174);
        panel.add(table_1);

        table_2 = new JTable();
        table_2.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "New column", "New column", "New column", "New column", "New column"
                }
        ));
        table_2.setBounds(225, 283, 689, 200);
        panel.add(table_2);

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

        JButton btnAccelrer = new JButton("Accel\u00E9rer");
        btnAccelrer.setBounds(817, 515, 97, 25);
        panel.add(btnAccelrer);

        JLabel lblTempsActuel = new JLabel("Temps actuel:");
        lblTempsActuel.setHorizontalAlignment(SwingConstants.CENTER);
        lblTempsActuel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTempsActuel.setBounds(12, 518, 166, 16);
        panel.add(lblTempsActuel);


    }
}


