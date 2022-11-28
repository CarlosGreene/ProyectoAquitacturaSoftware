import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import csv.CSVGenerator;
import mvc.model.Candidate;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class table extends JFrame implements Observer, ActionListener {
    private JPanel mainPanel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane();
    private JTable table;
    private JButton button1;

    private int numVotesCand1 = 0;
    private int numVotesCand2 = 0;
    private int numVotesCand3 = 0;

    public table() {
        table = new JTable();
        button1 = new JButton("Generar CSV");
        button1.addActionListener(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Candidato uno",  new Integer(0)},
                {"Candidato dos",  new Integer(0)},
                {"Candidato tres",  new Integer(0)}
            },
            new String [] {
                "Candidato", "Votos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollPane.setViewportView(table);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(0, 204, Short.MAX_VALUE)
                .addComponent(button1))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        Candidate cand = (Candidate) o;
        switch(cand.getName()){
            case "Candidato uno" -> setNumVotesCand1(cand.getNumVotes());
            case "Candidato dos" -> setNumVotesCand2(cand.getNumVotes());
            case "Candidato tres" -> setNumVotesCand3(cand.getNumVotes());
        }
        updateTable();
    }
    
    private void updateTable(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Nombre del candidato");
        tableModel.addColumn("Votos");
        tableModel.addRow(new Object[]{"Candidato uno", getNumVotesCand1()});
        tableModel.addRow(new Object[]{"Candidato dos", getNumVotesCand2()});
        tableModel.addRow(new Object[]{"Candidato tres", getNumVotesCand3()});
        table.setModel(tableModel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<String[]> list = new ArrayList<String[]>();

        list.add(new String[]{"Candidato", "Votos"});
        list.add(new String[]{"Candidato uno", String.valueOf(getNumVotesCand1())});
        list.add(new String[]{"Candidato dos", String.valueOf(getNumVotesCand2())});
        list.add(new String[]{"Candidato tres", String.valueOf(getNumVotesCand3())});

        try {
            new CSVGenerator().generateCSV(list);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public int getNumVotesCand1() {
        return numVotesCand1;
    }

    public void setNumVotesCand1(int numVotesCand1) {
        this.numVotesCand1 = numVotesCand1;
    }

    public int getNumVotesCand2() {
        return numVotesCand2;
    }

    public void setNumVotesCand2(int numVotesCand2) {
        this.numVotesCand2 = numVotesCand2;
    }

    public int getNumVotesCand3() {
        return numVotesCand3;
    }

    public void setNumVotesCand3(int numVotesCand3) {
        this.numVotesCand3 = numVotesCand3;
    }

    
}
