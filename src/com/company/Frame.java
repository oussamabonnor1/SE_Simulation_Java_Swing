package com.company;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.border.LineBorder;

public class Frame extends JFrame {

    public JPanel contentPane;
    public JTextField arrivalTimeField;
    public JTextField cpuTimeField;
    public JTextField priorityTextField;
    public JTextField quantumField;
    public JTable table;
    //count is for the process's number tracking while adding them to a list based on the user's wishes (unpredictable)
    int count;
    int choiceOfAlgo;

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
    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1020, 610);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnDonnes = new JMenu("Donn\u00E9es");
        menuBar.add(mnDonnes);

        JMenuItem mntmDonnesAlatoires = new JMenuItem("Donn\u00E9es Al\u00E9atoires");
        mntmDonnesAlatoires.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Random r = new Random();
                DefaultTableModel model = (DefaultTableModel) table.getModel();

                int count = table.getRowCount();
                for (int i = count; i < count + 5; i++) {
                    Processus p = new Processus(i + 1, r.nextInt(11), r.nextInt(8) + 1, r.nextInt(4) + 1, false);
                    String[] a = {String.valueOf(p.getName()), "" + p.getArriveTime(), "" + p.getCpuTime(), "" + p.getPriority()};
                    model.addRow(a);
                }

                quantumField.setText("" + (r.nextInt(3) + 1));
            }
        });
        mnDonnes.add(mntmDonnesAlatoires);

        JMenuItem mntmNewMenuItem = new JMenuItem("Donn\u00E9es a partir de fichier");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(Frame.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    File theFile = fc.getSelectedFile();
                    Scanner sc = null;
                    String s;
                    try {
                        sc = new Scanner(theFile);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    int row = 0;
                    int column = 0;
                    while (sc.hasNextLine()) {
                        s = sc.next();

                        if (column == 0) {
                            String[] a = {"P" + (row + 1)};
                            model.addRow(a);
                            ++column;
                        }
                        if (column % 3 == 0 && column > 0) {
                            model.setValueAt(s, row, column);
                            ++row;
                            column = 0;
                        } else {
                            model.setValueAt(s, row, column);
                            ++column;
                        }
                    }
                } else {

                }


            }
        });
        mnDonnes.add(mntmNewMenuItem);

        JMenuItem mntmRnitialiserLesDonnes = new JMenuItem("R\u00E9nitialiser les Donn\u00E9es");
        mntmRnitialiserLesDonnes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();

                while (model.getRowCount() > 0) {
                    model.removeRow(0);
                }

                quantumField.setText("");
            }
        });
        mnDonnes.add(mntmRnitialiserLesDonnes);

        JMenuItem mntmQuitter = new JMenuItem("Quitter");
        mntmQuitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnDonnes.add(mntmQuitter);

        JMenu mnOptions = new JMenu("Options");
        menuBar.add(mnOptions);

        JMenu mnAlgorithmes = new JMenu("Algorithmes");
        mnOptions.add(mnAlgorithmes);

        JMenuItem mntmFirstComeFirst = new JMenuItem("First Come First Served");
        mntmFirstComeFirst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                choiceOfAlgo = 0;
            }
        });
        mnAlgorithmes.add(mntmFirstComeFirst);

        JMenuItem mntmShortestJobFirst = new JMenuItem("Shortest Job First");
        mntmShortestJobFirst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                choiceOfAlgo = 1;
            }
        });
        mnAlgorithmes.add(mntmShortestJobFirst);

        JMenuItem mntmRoundRobin = new JMenuItem("Round Robin");
        mntmRoundRobin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                choiceOfAlgo = 2;
            }
        });
        mnAlgorithmes.add(mntmRoundRobin);

        JMenuItem mntmPriority = new JMenuItem("Priority");
        mntmPriority.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                choiceOfAlgo = 3;
            }
        });
        mnAlgorithmes.add(mntmPriority);


        JCheckBoxMenuItem chckbxmntmPreemptivit = new JCheckBoxMenuItem("Preemptivit\u00E9");
        mnOptions.add(chckbxmntmPreemptivit);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblCoreSimulator = new JLabel("Core Simulator");
        lblCoreSimulator.setFont(new Font("Rockwell", Font.BOLD, 34));
        lblCoreSimulator.setBounds(371, 13, 298, 46);
        panel.add(lblCoreSimulator);

        JLabel lblProcessus = new JLabel("Processus: ");
        lblProcessus.setFont(new Font("Rockwell", Font.PLAIN, 15));
        lblProcessus.setBounds(40, 135, 87, 25);
        panel.add(lblProcessus);

        JLabel lblTempsDarriv = new JLabel("Temps d'arriv\u00E9");
        lblTempsDarriv.setFont(new Font("Rockwell", Font.PLAIN, 15));
        lblTempsDarriv.setBounds(136, 95, 105, 25);
        panel.add(lblTempsDarriv);

        JLabel lblTempsCpu = new JLabel("Temps CPU");
        lblTempsCpu.setFont(new Font("Rockwell", Font.PLAIN, 15));
        lblTempsCpu.setBounds(271, 95, 84, 25);
        panel.add(lblTempsCpu);

        JLabel lblPriorit = new JLabel("Priorit\u00E9");
        lblPriorit.setFont(new Font("Rockwell", Font.PLAIN, 15));
        lblPriorit.setBounds(382, 95, 76, 25);
        panel.add(lblPriorit);

        arrivalTimeField = new JTextField();
        arrivalTimeField.setBounds(139, 136, 89, 22);
        panel.add(arrivalTimeField);
        arrivalTimeField.setColumns(10);

        cpuTimeField = new JTextField();
        cpuTimeField.setColumns(10);
        cpuTimeField.setBounds(271, 137, 76, 22);
        panel.add(cpuTimeField);

        priorityTextField = new JTextField();
        priorityTextField.setColumns(10);
        priorityTextField.setBounds(370, 136, 76, 22);
        panel.add(priorityTextField);

        JButton btnNewButton = new JButton("Ajouter a la liste");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                ++count;
                String[] a = {String.valueOf(count), arrivalTimeField.getText(), cpuTimeField.getText(), priorityTextField.getText()};

                model.addRow(a);

                arrivalTimeField.setText("");
                cpuTimeField.setText("");
                priorityTextField.setText("");
            }
        });
        btnNewButton.setBounds(180, 186, 175, 25);
        panel.add(btnNewButton);

        JLabel lblQuantum = new JLabel("Quantum");
        lblQuantum.setFont(new Font("Rockwell", Font.PLAIN, 15));
        lblQuantum.setBounds(40, 360, 87, 25);
        panel.add(lblQuantum);

        quantumField = new JTextField();
        quantumField.setBounds(205, 362, 116, 22);
        panel.add(quantumField);
        quantumField.setColumns(10);

        table = new JTable();
        table.setBackground(SystemColor.inactiveCaptionBorder);
        table.setBorder(new LineBorder(new Color(51, 153, 255), 2, true));
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "N processus", "Temps d'arriv\u00E9", "Temps CPU", "Priority"
                }
        ));
        table.getColumnModel().getColumn(0).setPreferredWidth(93);
        table.getColumnModel().getColumn(1).setPreferredWidth(98);
        table.getColumnModel().getColumn(2).setPreferredWidth(95);
        table.getColumnModel().getColumn(3).setPreferredWidth(92);
        table.setFont(new Font("Ravie", Font.PLAIN, 15));
        table.setBounds(522, 101, 421, 175);
        panel.add(table);

        JButton btnCommencez = new JButton("Commencez !");

        btnCommencez.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ArrayList<Processus> file = new ArrayList<>();

                for (int i = 0; i < table.getRowCount(); i++) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();

                    Processus p = new Processus(i + 1, Integer.valueOf((String) model.getValueAt(i, 1)), Integer.valueOf((String) model.getValueAt(i, 2)), Integer.valueOf((String) model.getValueAt(i, 3)), false);


                    System.out.println("Processus " + p.getName() + " arrival time " + p.getArriveTime() + " cpu " + p.getCpuTime() + " Priority " + p.getPriority());
                    file.add(p);
                }
                System.out.println();

                for (int i = 0; i < file.size(); i++) {
                    System.out.println(file.get(i).getName());
                }

                executionFrame executionFrame = new executionFrame(file, choiceOfAlgo, Integer.parseInt(quantumField.getText()), chckbxmntmPreemptivit.getState());
                setVisible(false);
                executionFrame.setVisible(true);
            }
        });

        btnCommencez.setBounds(666, 361, 175, 25);
        panel.add(btnCommencez);


        JLabel label = new JLabel("Temps d'arriv\u00E9");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Rockwell", Font.PLAIN, 15));
        label.setBounds(628, 72, 105, 25);
        panel.add(label);

        JLabel label_1 = new JLabel("Temps CPU");
        label_1.setHorizontalAlignment(SwingConstants.CENTER);
        label_1.setFont(new Font("Rockwell", Font.PLAIN, 15));
        label_1.setBounds(745, 72, 84, 25);
        panel.add(label_1);

        JLabel label_2 = new JLabel("Priorit\u00E9");
        label_2.setHorizontalAlignment(SwingConstants.CENTER);
        label_2.setFont(new Font("Rockwell", Font.PLAIN, 15));
        label_2.setBounds(857, 72, 76, 25);
        panel.add(label_2);

        JLabel label_3 = new JLabel("Processus: ");
        label_3.setHorizontalAlignment(SwingConstants.CENTER);
        label_3.setFont(new Font("Rockwell", Font.PLAIN, 15));
        label_3.setBounds(529, 72, 87, 25);
        panel.add(label_3);
    }


}
