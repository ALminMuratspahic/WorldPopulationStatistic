package almin.worldpopulatio.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVFormat.Builder;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import almin.worldpopulatio.model.PopulationStats;
@Service
public class PopulationData {
	private long bosniaCase;
	
	private final String DATA_SOURCE="https://raw.githubusercontent.com/datasets/population/master/data/population.csv";
	private List<PopulationStats>allStats= new ArrayList<>();
	
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *") 
	public void getPopulationData() throws IOException, InterruptedException {
		
		List<PopulationStats>stats =  new ArrayList<>();
		List<PopulationStats>bosna= new ArrayList<>();
		
		HttpClient clinet= HttpClient.newHttpClient();
		HttpRequest request= HttpRequest.newBuilder().uri(URI.create(DATA_SOURCE)).build();
		HttpResponse<String>httpResponse=clinet.send(request, HttpResponse.BodyHandlers.ofString());
		StringReader csvBodyReader= new StringReader(httpResponse.body());
		
		@SuppressWarnings("deprecation")
		Iterable<CSVRecord> records=CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		
		for(CSVRecord record:records) {
			PopulationStats populationStats= new PopulationStats();
			populationStats.setCountry(record.get("Country Name"));
			populationStats.setCountryCode(record.get("Country Code"));
			
			int year=Integer.parseInt(record.get("Year"));
			populationStats.setYear(year);
			
			long value=Long.parseLong(record.get("Value"));
			populationStats.setValue(value);
			
			stats.add(populationStats);
			
			if(populationStats.getCountry().equals("Bosnia and Herzegovina")) {
				bosna.add(populationStats);
			}
			
		}
		
		long lastYear=bosna.get((bosna.size()-1)).getValue();
		long prevYear=bosna.get((bosna.size()-2)).getValue();
		bosniaCase=lastYear-prevYear;
	
		this.allStats=stats;
	
	}
	public long getBosnaCase() {
		return bosniaCase;
	}
	
	public List<PopulationStats>getAllStats(){
		return allStats;
	}
	
}
