package Screens;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Compare {
	public static class CompareSongs extends Application {
		public ArrayList<String[]> list = new ArrayList<>();
		
		public CompareSongs() {
			//list.add(new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"});
			//list.add(new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"});
			list = StoreSelected.getList();
		}
		
		@Override
		   public void start(Stage stage) {
		      //Defining the axes              
		      CategoryAxis xAxis = new CategoryAxis();  
		      xAxis.setCategories(FXCollections.<String>
		      observableArrayList(Arrays.asList("Danceability", "Energy", "Speechiness", "Acousticness", "Instrumentalness", "Liveness")));
		      xAxis.setLabel("category");
		       
		      NumberAxis yAxis = new NumberAxis();
		      yAxis.setLabel("rating");
		      
		      String songName1 = "";
		      if (list.get(0)[3] != null)
		    	  songName1 = list.get(0)[3];
		      else if (list.get(0)[30] != null)
		    	  songName1 = list.get(0)[30];
		      
		      String songName2 = "";
		      if (list.get(1)[3] != null)
		    	  songName2 = list.get(1)[3];
		      else if (list.get(1)[30] != null)
		    	  songName2 = list.get(1)[30];
		      
		      String artistName1 = "";
		      if (list.get(0)[1] != null)
		    	  artistName1 = list.get(0)[1];
		      else if (list.get(0)[31] != null)
		    	  artistName1 = list.get(0)[31];
		      
		      String artistName2 = "";
		      if (list.get(1)[1] != null)
		    	  artistName2 = list.get(1)[1];
		      else if (list.get(1)[31] != null)
		    	  artistName2 = list.get(1)[31];
		      
		      double danceability1 = 0;
		      if (list.get(0)[7] != null)
		    	  danceability1 = Double.parseDouble(list.get(0)[7].replaceAll("\"", ""));
		      else if (list.get(0)[42] != null)
		    	  danceability1 = Double.parseDouble(list.get(0)[42].replaceAll("\"", ""));
		      
		      double danceability2 = 0;
		      if (list.get(1)[7] != null)
		    	  danceability2 = Double.parseDouble(list.get(1)[7].replaceAll("\"", ""));
		      else if (list.get(1)[42] != null)
		    	  danceability2 = Double.parseDouble(list.get(1)[42].replaceAll("\"", ""));
		      
		      double energy1 = 0;
		      if (list.get(0)[8] != null)
		    	  energy1 = Double.parseDouble(list.get(0)[8].replaceAll("\"", ""));
		      else if (list.get(0)[43] != null)
		    	  energy1 = Double.parseDouble(list.get(0)[43].replaceAll("\"", ""));
		      
		      double energy2 = 0;
		      if (list.get(1)[8] != null)
		    	  energy2 = Double.parseDouble(list.get(1)[8].replaceAll("\"", ""));
		      else if (list.get(1)[43] != null)
		    	  energy2 = Double.parseDouble(list.get(1)[43].replaceAll("\"", ""));
		     
		      double loudness1 = 0;
		      if (list.get(0)[10] != null)
		    	  loudness1 = Double.parseDouble(list.get(0)[10].replaceAll("\"", ""));
		      else if (list.get(0)[45] != null)
		    	  loudness1 = Double.parseDouble(list.get(0)[45].replaceAll("\"", ""));
		      
		      double loudness2 = 0;
		      if (list.get(1)[10] != null)
		    	  loudness2 = Double.parseDouble(list.get(1)[10].replaceAll("\"", ""));
		      else if (list.get(1)[45] != null)
		    	  loudness2 = Double.parseDouble(list.get(1)[45].replaceAll("\"", ""));
		      
		      double speechiness1 = 0;
		      if (list.get(0)[11] != null)
		    	  speechiness1 = Double.parseDouble(list.get(0)[11].replaceAll("\"", ""));
		      else if (list.get(0)[47] != null)
		    	  speechiness1 = Double.parseDouble(list.get(0)[47].replaceAll("\"", ""));
		      
		      double speechiness2 = 0;
		      if (list.get(1)[11] != null)
		    	  speechiness2 = Double.parseDouble(list.get(1)[11].replaceAll("\"", ""));
		      else if (list.get(1)[47] != null)
		    	  speechiness2 = Double.parseDouble(list.get(1)[47].replaceAll("\"", ""));
		      
		      double acousticness1 = 0;
		      if (list.get(0)[12] != null)
		    	  acousticness1 = Double.parseDouble(list.get(0)[12].replaceAll("\"", ""));
		      else if (list.get(0)[48] != null)
		    	  acousticness1 = Double.parseDouble(list.get(0)[48].replaceAll("\"", ""));
		      
		      double acousticness2 = 0;
		      if (list.get(1)[12] != null)
		    	  acousticness2 = Double.parseDouble(list.get(1)[12].replaceAll("\"", ""));
		      else if (list.get(1)[48] != null)
		    	  acousticness2 = Double.parseDouble(list.get(1)[48].replaceAll("\"", ""));
		      
		      double instrumentalness1 = 0;
		      if (list.get(0)[13] != null)
		    	  instrumentalness1 = Double.parseDouble(list.get(0)[13].replaceAll("\"", ""));
		      else if (list.get(0)[49] != null)
		    	  instrumentalness1 = Double.parseDouble(list.get(0)[49].replaceAll("\"", ""));
		      
		      double instrumentalness2 = 0;
		      if (list.get(1)[13] != null)
		    	  instrumentalness2 = Double.parseDouble(list.get(1)[13].replaceAll("\"", ""));
		      else if (list.get(1)[49] != null)
		    	  instrumentalness2 = Double.parseDouble(list.get(1)[49].replaceAll("\"", ""));
		      
		      double liveness1 = 0;
		      if (list.get(0)[14] != null)
		    	  liveness1 = Double.parseDouble(list.get(0)[14].replaceAll("\"", ""));
		      else if (list.get(0)[50] != null)
		    	  liveness1 = Double.parseDouble(list.get(0)[50].replaceAll("\"", ""));
		      
		      double liveness2 = 0;
		      if (list.get(1)[14] != null)
		    	  liveness2 = Double.parseDouble(list.get(1)[14].replaceAll("\"", ""));
		      else if (list.get(1)[50] != null)
		    	  liveness2 = Double.parseDouble(list.get(1)[50].replaceAll("\"", ""));
		      
		      double duration1 = 0;
		      if (list.get(0)[17] != null)
		    	  duration1 = Double.parseDouble(list.get(0)[17].replaceAll("\"", ""));
		      else if (list.get(0)[39] != null)
		    	  duration1 = Double.parseDouble(list.get(0)[39].replaceAll("\"", ""));
		      
		      double duration2 = 0;
		      if (list.get(1)[17] != null)
		    	  duration2 = Double.parseDouble(list.get(1)[17].replaceAll("\"", ""));
		      else if (list.get(1)[39] != null)
		    	  duration2 = Double.parseDouble(list.get(1)[39].replaceAll("\"", ""));
		      
		      //Creating the Bar chart
		      BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis); 
		      barChart.setTitle("Comparison between " + songName1 + " by " + artistName1 + " and " + songName2 + " by " + artistName2);
		        
		      //Prepare XYChart.Series objects by setting data       
		      XYChart.Series<String, Number> series1 = new XYChart.Series<>();
		      series1.setName(songName1);
		      series1.getData().add(new XYChart.Data<>("Danceability", danceability1));
		      series1.getData().add(new XYChart.Data<>("Energy", energy1));
		      series1.getData().add(new XYChart.Data<>("Speechiness", speechiness1));
		      series1.getData().add(new XYChart.Data<>("Acousticness", acousticness1));
		      series1.getData().add(new XYChart.Data<>("Instrumentalness", instrumentalness1));
		      series1.getData().add(new XYChart.Data<>("Liveness", liveness1));
		        
		      XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		      series2.setName(songName2);
		      series2.getData().add(new XYChart.Data<>("Danceability", danceability2));
		      series2.getData().add(new XYChart.Data<>("Energy", energy2));
		      series2.getData().add(new XYChart.Data<>("Speechiness", speechiness2));
		      series2.getData().add(new XYChart.Data<>("Acousticness", acousticness2));
		      series2.getData().add(new XYChart.Data<>("Instrumentalness", instrumentalness2));
		      series2.getData().add(new XYChart.Data<>("Liveness", liveness2));
		              
		      //Setting the data to bar chart       
		      barChart.getData().addAll(series1, series2);
		        
		      //Creating a Group object 
		      Group root = new Group(barChart);
		        
		      //Creating a scene object
		      Scene scene = new Scene(root, 1200, 800);
		      
		      //Set barchart size
		      barChart.setPrefSize(1100, 600);

		      //Setting title to the Stage
		      stage.setTitle("Bar Chart");
		        
		      //Adding scene to the stage
		      stage.setScene(scene);
		        
		      //Displaying the contents of the stage
		      stage.show();        
		   }
		
		   public static void main(String args[]){
		      launch(args);
		   }
		   
		   public void set(ArrayList<String[]> list) {
	            this.list = list;
		   }
		   
		   public ArrayList<String[]> returnList() {
			   return list;
		   }
		   
		   /* public static void start(){
		      launch();
		   } */
	}
}


