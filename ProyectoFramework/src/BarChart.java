import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import mvc.model.Candidate;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class BarChart extends JFrame implements Observer{
    private DefaultCategoryDataset datos;
    private JFreeChart bar_graph;
    private final JPanel mainPanel = new JPanel();

    public BarChart(){
        datos = new DefaultCategoryDataset();
        mainPanel.setLayout(new BorderLayout());

        datos.setValue(0, "Candidato uno", "Candidato uno");
        datos.setValue(0,"Candidato dos", "Candidato dos");
        datos.setValue(0, "Candidato tres", "Candidato tres");

        bar_graph = ChartFactory.createBarChart("Votos", "Cantidatos", "votos", datos, PlotOrientation.VERTICAL,true, true,false);
        ChartPanel panel = new ChartPanel(bar_graph);
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
        datos.setValue(candidato.getNumVotes(), candidato.getName(), candidato.getName());
    }
}