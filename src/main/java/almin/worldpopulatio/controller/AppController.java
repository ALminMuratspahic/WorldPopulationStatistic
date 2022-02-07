package almin.worldpopulatio.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import almin.worldpopulatio.model.PopulationStats;
import almin.worldpopulatio.service.PopulationData;

@Controller
public class AppController {
	
	@Autowired
	private PopulationData populationData;
	
	@GetMapping("/")
	public String home(Model model) {
		List<PopulationStats>allStats=populationData.getAllStats();
		List<PopulationStats>worldPopulation= 
								allStats.stream()
								.filter(stat->stat.getCountry().equalsIgnoreCase("world"))
								.collect(Collectors.toList());
		 long totalWorldPopulation2018= 
				 worldPopulation.get(worldPopulation.size()-1).getValue();
		 String totalWorldPopulatio2018Format=
				 NumberFormat.getInstance().format(totalWorldPopulation2018);
		 long totalWorldPopulation2017=	
				 worldPopulation.get(worldPopulation.size()-2).getValue();
		 	
		 long compareToLastYear=totalWorldPopulation2018-totalWorldPopulation2017;
		 String newBorn=  NumberFormat.getInstance().format(compareToLastYear);
		
		 String bosniaCase=NumberFormat.getInstance().format(populationData.getBosnaCase());
		
		model.addAttribute("populationStats", populationData.getAllStats());
		model.addAttribute("bosniaCase",bosniaCase);
		model.addAttribute("totalWorldPopulation2018", totalWorldPopulatio2018Format);
		model.addAttribute("newBorn", newBorn);
		
		return "home";
	}

}
