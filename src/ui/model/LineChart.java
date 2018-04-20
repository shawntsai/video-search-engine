package ui.model;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineChart extends JFrame{
    public LineChart(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                null,
                "","",
                createDataset(),
                PlotOrientation.VERTICAL,
                false,false,false);
        lineChart.removeLegend();
        CategoryPlot plots = (CategoryPlot) lineChart.getPlot();
        CategoryAxis categoryAxis = (CategoryAxis) plots.getDomainAxis();
        categoryAxis.setAxisLineVisible(false);
        categoryAxis.setTickMarksVisible(false);
        categoryAxis.setLabel(null);
        categoryAxis.setVisible(false);

        CategoryPlot p = lineChart.getCategoryPlot();
        ValueAxis axis2 = p.getRangeAxis();
        axis2.setTickMarksVisible(false);
        axis2.setAxisLineVisible(false);
        axis2.setVisible(false);



        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize(new java.awt.Dimension( 300 , 100 ) );
        JPanel jPanel = new JPanel();
        jPanel.add(chartPanel);
        JButton b=new JButton("Click Here");
        b.setBounds(0, 567, 95, 30);
        jPanel.add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFreeChart lineChart = ChartFactory.createLineChart(
                        null,
                        "Years","Number of Schools",
                        anotherDataset(),
                        PlotOrientation.VERTICAL,
                        true,true,false);
                jPanel.removeAll();
                jPanel.revalidate();
                ChartPanel chartPanel1 = new ChartPanel(lineChart);
                chartPanel1.setPreferredSize(new java.awt.Dimension( 300 , 100 ) );
                jPanel.add(chartPanel1);
                jPanel.repaint();
            }
        });
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(jPanel);
        frame.pack();
        frame.setVisible(true);
//        setContentPane( chartPanel );
    }
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        for(int i = 1; i < 150; i++) {
            dataset.addValue(15 + i, "schools", Integer.toString(i));
        }
        return dataset;
    }

    private CategoryDataset anotherDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        dataset.addValue( 300 , "schools" , "1970" );
        dataset.addValue( 240 , "schools" , "1980" );
        dataset.addValue( 120 , "schools" ,  "1990" );
        dataset.addValue( 60 , "schools" , "2000" );
        dataset.addValue( 30 , "schools" , "2010" );
        dataset.addValue( 15 , "schools" , "2014" );
        return dataset;
    }

    public static void main( String[ ] args ) {
        LineChart chart = new LineChart("Car Usage Statistics",
                "Which car do you like?");
//        ChartPanel chPanel = new ChartPanel(chart);
//        Image image = chart.createImage(600,400);
//        JLabel label = new JLabel(new ImageIcon(image));
//        JPanel panel = new JPanel();
//        panel.add(label);

//        chart.pack( );
//        RefineryUtilities.centerFrameOnScreen( chart );
//        chart.setVisible( true );
    }
}
