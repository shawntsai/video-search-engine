package ui.component;

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
    }

    public void update(CategoryDataset dataset) {
        if(dataset == null)
            return;

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
