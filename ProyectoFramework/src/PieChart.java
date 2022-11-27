import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import mvc.model.Candidate;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class PieChart extends JFrame implements Observer{
    private DefaultPieDataset datos;
    private JFreeChart pie_graph;
    private final JPanel mainPanel = new JPanel();

    public PieChart(){
        datos = new DefaultPieDataset();
        mainPanel.setLayout(new BorderLayout());

        datos.setValue("Candidato uno",0);
        datos.setValue("Candidato dos", 0);
        datos.setValue("Candidato tres", 0);

        pie_graph = ChartFactory.createPieChart("Votos", datos, true, true, false);
        ChartPanel panel = new ChartPanel(pie_graph);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(400, 400));

        mainPanel.add(panel, BorderLayout.NORTH);
        this.add(mainPanel);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.pack();
        this.repaint();
        this.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        Candidate candidato = (Candidate) o;
        datos.setValue(candidato.getName(), candidato.getNumVotes());
    }
    
}
