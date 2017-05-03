package com.company;

/**
 * Created by Oussama on 19/04/2017.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class executionFrame extends JFrame {

    private JScrollPane jScrollPane1;
    public JTable waitingTable;
    public JTable processingTable;
    public JPanel contentPane;
    public JLabel lblAlgorithmeUtilis;
    public int choiceOfAlgo;
    public int quantum;
    public static ArrayList<Processus> file;
    boolean preemptif = false;

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
                if (preemptif) {
                    System.out.println("shortest job Preemptif");
                    algorithmes.shortJobFirstPreemptif(algorithmes.file, this);
                } else {
                    System.out.println("shortest job non Preemtif");
                    algorithmes.shortJobFirstNoPreemptif(algorithmes.file, this, quantum);
                }
                break;
            case 2:
                System.out.println("round robin");
                algorithmes.roundRobinNonPreemptif(algorithmes.file, quantum, this);
                break;
            case 3:
                if (preemptif) {
                    System.out.println("priority Preemptif");
                    algorithmes.priorityPremptif(algorithmes.file, this);
                } else {
                    System.out.println("priority non preemptif");
                    algorithmes.priorityNonPremptif(algorithmes.file, this, quantum);
                }
                break;
            default:
                System.out.println("default");
                algorithmes.firstComeFirstServe(algorithmes.file, this);
                break;
        }

    }

    public executionFrame(ArrayList<Processus> file, int choiceOfAlgo, int quantum, boolean preemptif) {
        this.file = file;
        this.choiceOfAlgo = choiceOfAlgo;
        this.quantum = quantum;
        this.preemptif = preemptif;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1020, 610);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        waitingTable = new JTable();
        waitingTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "N° Processus", "Temps d'arrivé", "T CPU", "Priorité "
                }
        ));


        waitingTable.setBounds(32, 67, 439, 300);
        panel.add(waitingTable);


        processingTable = new JTable();
        processingTable.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "nom Processus", "temps début", "temps fin"
                }
        ));
        processingTable.setBounds(518, 67, 439, 300);
        panel.add(processingTable);

        JButton btnCommencez = new JButton("Commencer");
        btnCommencez.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                DefaultTableModel model = (DefaultTableModel) processingTable.getModel();

                for (int i = 0; i < file.size(); i++) {
                    String[] a = {"P " + String.valueOf(file.get(i).getName())};
                    model.addRow(a);
                }

                makeStuffWork();
            }
        });
        btnCommencez.setBounds(828, 515, 103, 25);
        panel.add(btnCommencez);

        JButton btnRetournez = new JButton("Retourner");
        btnRetournez.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Frame frame = new Frame();
                frame.setVisible(true);
                executionFrame.this.setVisible(false);
            }
        });
        btnRetournez.setBounds(685, 515, 103, 25);
        panel.add(btnRetournez);

        JLabel lblListeDattente = new JLabel("Liste d'attente");
        lblListeDattente.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblListeDattente.setHorizontalAlignment(SwingConstants.CENTER);
        lblListeDattente.setBounds(191, 13, 128, 16);
        panel.add(lblListeDattente);

        JLabel lblListeCpu = new JLabel("Liste CPU");
        lblListeCpu.setHorizontalAlignment(SwingConstants.CENTER);
        lblListeCpu.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblListeCpu.setBounds(685, 13, 128, 16);
        panel.add(lblListeCpu);

        DefaultTableModel model = (DefaultTableModel) waitingTable.getModel();

        for (int i = 0; i < file.size(); i++) {
            String[] a = {"P " + String.valueOf(file.get(i).getName()), String.valueOf(file.get(i).getArriveTime()), String.valueOf(file.get(i).getCpuTime()), String.valueOf(file.get(i).getPriority())};
            model.addRow(a);
        }


        JLabel lblNomProcessus = new JLabel("Nom Processus");
        lblNomProcessus.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNomProcessus.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomProcessus.setBounds(32, 46, 110, 16);
        panel.add(lblNomProcessus);

        JLabel lblTempDarriv = new JLabel("Temp d'arriv\u00E9");
        lblTempDarriv.setHorizontalAlignment(SwingConstants.CENTER);
        lblTempDarriv.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTempDarriv.setBounds(141, 47, 110, 16);
        panel.add(lblTempDarriv);

        JLabel lblTempCpu = new JLabel("Temp CPU");
        lblTempCpu.setHorizontalAlignment(SwingConstants.CENTER);
        lblTempCpu.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTempCpu.setBounds(252, 47, 110, 16);
        panel.add(lblTempCpu);

        JLabel lblPriorit = new JLabel("Priorit\u00E9");
        lblPriorit.setHorizontalAlignment(SwingConstants.CENTER);
        lblPriorit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPriorit.setBounds(361, 47, 110, 16);
        panel.add(lblPriorit);

        JLabel lblNomProcessus_1 = new JLabel("Nom Processus");
        lblNomProcessus_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomProcessus_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNomProcessus_1.setBounds(537, 47, 110, 16);
        panel.add(lblNomProcessus_1);

        JLabel lblTempsDeCommencement = new JLabel("Temps de d\u00E9but");
        lblTempsDeCommencement.setHorizontalAlignment(SwingConstants.CENTER);
        lblTempsDeCommencement.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTempsDeCommencement.setBounds(659, 46, 152, 16);
        panel.add(lblTempsDeCommencement);

        JLabel lblTempsDeFin = new JLabel("Temps de Fin");
        lblTempsDeFin.setHorizontalAlignment(SwingConstants.CENTER);
        lblTempsDeFin.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTempsDeFin.setBounds(828, 47, 110, 16);
        panel.add(lblTempsDeFin);

        String algoChosen;
        switch (choiceOfAlgo) {
            case 0:
                algoChosen = "First In First Out (FIFO)";
                break;
            case 1:
                if (preemptif) {
                    algoChosen = "Shortest Job First Preemptif (SJF)";
                } else {
                    algoChosen = "Shortest Job First non Preemptif (SJF)";
                }
                break;
            case 2:
                algoChosen = "Round Robin (RR)";
                break;
            case 3:
                if (preemptif) {
                    algoChosen = "Priority Preemptif";
                } else {
                    algoChosen = "Priority non Preemptif";
                }
                break;
            default:
                algoChosen = "First In First Out (FIFO)";
                break;
        }

        lblAlgorithmeUtilis = new JLabel("Algorithme utilis\u00E9: " + algoChosen);
        lblAlgorithmeUtilis.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblAlgorithmeUtilis.setBounds(32, 514, 500, 25);
        panel.add(lblAlgorithmeUtilis);
    }
}


