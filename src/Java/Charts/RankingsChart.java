package Java.Charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import org.jfree.data.time.Day;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.NumberFormat;
import java.util.Date;

public class RankingsChart extends JPanel {

    private TimeSeriesCollection dataset;
    private JFreeChart chart;

    public RankingsChart() {
        super(new BorderLayout());
        dataset = new TimeSeriesCollection();

        // Create chart
        chart = ChartFactory.createTimeSeriesChart(
                "Song Ranking Over Time", 
                "Date", 
                "Rank", 
                dataset,
                true,
                false,
                false);

        // Disable scientific notation
        NumberAxis rangeAxis = (NumberAxis) chart.getXYPlot().getRangeAxis();
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        rangeAxis.setNumberFormatOverride(format);
        rangeAxis.setInverted(true);
        rangeAxis.setTickUnit(new NumberTickUnit(1));


        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(425, 425));
        add(panel);
    }

    public void addDataPoint(String song, Date date, double rank) {
        TimeSeries series = dataset.getSeries(song);
        if (series == null) {
            series = new TimeSeries(song);
            dataset.addSeries(series);
        }
        series.addOrUpdate(new Day(date), rank);
    }

    public void clearData() {
        for (int i = dataset.getSeriesCount() - 1; i >= 0; i--) {
            dataset.removeSeries(i);
        }

    }

}
