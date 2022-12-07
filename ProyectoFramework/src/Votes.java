import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.event.*;

import mvc.controler.Control;
import mvc.view.View;;

public class Votes extends View implements ActionListener{
    
    private final JPanel mainPanel = new JPanel();
    private JButton candidato1, candidato2, candidato3;
    
    public Votes() {
        mainPanel.setLayout(new BorderLayout());
        candidato1 = new JButton("Candidato uno");
        candidato2 = new JButton("Candidato dos");
        candidato3 = new JButton("Candidato tres");
        candidato1.addActionListener(this);
        candidato2.addActionListener(this);
        candidato3.addActionListener(this);

        this.mainPanel.add(candidato1, BorderLayout.NORTH);
        this.mainPanel.add(candidato2, BorderLayout.CENTER);
        this.mainPanel.add(candidato3, BorderLayout.SOUTH);
        this.add(mainPanel);

        this.pack();
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public void setControl(Control control) {
        this.control = control;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String candidatoKey = ((JButton)e.getSource()).getText();
        
        this.control.UpdateData(candidatoKey);
    }
}
