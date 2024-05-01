package Java.Charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Day;
import org.jfree.chart.axis.NumberAxis;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.NumberFormat;
import java.util.Date;





public class PopularityChart extends JPanel {

    private TimeSeriesCollection dataset;

    public PopularityChart() {
        super(new BorderLayout());
        dataset = new TimeSeriesCollection();

        // Create chart
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Song Popularity Over Time", 
                "Date", 
                "Popularity", 
                dataset,
                true,
                false,
                false);


        // Disable scientific notation
        NumberAxis rangeAxis = (NumberAxis) chart.getXYPlot().getRangeAxis();
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        rangeAxis.setNumberFormatOverride(format);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(425, 425));
        add(panel);
    }

    public void addDataPoint(String song, Date date, int popularity) {
        TimeSeries series = dataset.getSeries(song);
        if (series == null) {
            series = new TimeSeries(song);
            dataset.addSeries(series);
        }
        series.addOrUpdate(new Day(date), popularity);
    }

    public void clearData() {
        for (int i = dataset.getSeriesCount() - 1; i >= 0; i--) {
            dataset.removeSeries(i);
        }
    }
}
