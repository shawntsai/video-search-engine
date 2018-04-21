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

public class LineChart{

    private JPanel area;
    // TODO For connect to the backend
    private String selectedVideo;
    private final static String mock1 = "Item 1";

    public LineChart(JPanel targetArea) {
        this.area = targetArea;
//        JFreeChart lineChart = ChartFactory.createLineChart(
//                null,
//                "","",
//                createDataset(),
//                PlotOrientation.VERTICAL,
//                false,false,false);
//        setChartProperty(lineChart);
//
//
//
//
//        ChartPanel chartPanel = new ChartPanel( lineChart );
//        chartPanel.setPreferredSize(new java.awt.Dimension( 300 , 100 ) );
//
//        clearArea(this.area);
//        this.area.add(chartPanel);
//        b.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JFreeChart lineChart = ChartFactory.createLineChart(
//                        null,
//                        "Years","Number of Schools",
//                        anotherDataset(),
//                        PlotOrientation.VERTICAL,
//                        true,true,false);
//                jPanel.removeAll();
//                jPanel.revalidate();
//                ChartPanel chartPanel1 = new ChartPanel(lineChart);
//                chartPanel1.setPreferredSize(new java.awt.Dimension( 300 , 100 ) );
//                jPanel.add(chartPanel1);
//                jPanel.repaint();
//            }
//        });
//        jPanel.repaint();
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(jPanel);
//        frame.pack();
//        frame.setVisible(true);
//        setContentPane( chartPanel );
    }

    public void update(String input) {
//        clearArea(this.area);
//        JButton button = new JButton("Click me");
//        button.setBounds(0,0,300,100);
//        this.area.add(button);
//        area.validate();
//        area.repaint();
        // TODO: This part will connect to backend
        CategoryDataset dataset;
        if(input.equals(mock1)) {
            dataset = createDataset();
        }else {
            dataset = anotherDataset();
        }


        JFreeChart newChart = ChartFactory.createLineChart(
                null,
                "","",
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false);
        setChartProperty(newChart);

        this.clearArea(area);
        ChartPanel chartPanel = new ChartPanel( newChart );
        chartPanel.setPreferredSize(new java.awt.Dimension( 300 , 110 ) );
        area.add(chartPanel);
        area.validate();
        area.repaint();
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        for(int i = 1; i < 150; i++) {
            dataset.addValue(15 + i, "similarity", Integer.toString(i));
        }
        return dataset;
    }

    private CategoryDataset anotherDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        for(int i = 1; i < 150; i++) {
            dataset.addValue(15 * Math.PI + i, "similarity", Integer.toString(i));
        }
        return dataset;
    }

    private void setChartProperty(JFreeChart chart) {
        chart.removeLegend();
        CategoryPlot plots = (CategoryPlot) chart.getPlot();
        CategoryAxis categoryAxis = (CategoryAxis) plots.getDomainAxis();
        categoryAxis.setAxisLineVisible(false);
        categoryAxis.setTickMarksVisible(false);
        categoryAxis.setLabel(null);
        categoryAxis.setVisible(false);

        CategoryPlot p = chart.getCategoryPlot();
        ValueAxis axis2 = p.getRangeAxis();
        axis2.setTickMarksVisible(false);
        axis2.setAxisLineVisible(false);
        axis2.setVisible(false);
    }

    private void clearArea(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
    }

}
