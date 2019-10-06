package codingcompetition2019;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class CodingCompCSVUtil {
	
	
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		List<List<String>> disasters = new ArrayList<List<String>>();
		try {
			String line;
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String[] lineArr = line.split(",");
				if(lineArr[0].contentEquals(countryName)) { //country is equal
					disasters.add(new ArrayList<String>()); //add new row
					for(String str : lineArr) {
						disasters.get(disasters.size()-1).add(str);
					}
				}
			}
			br.close();
		}
		catch (IOException e) {
			throw e;
		}
		return disasters;
	}
	
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		
		List<List<String>> disasters = new ArrayList<List<String>>();
		try {
			String line;
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			br.readLine(); //skip the header line
			while ((line = br.readLine()) != null) {
				String[] lineArr = line.split(",");
				disasters.add(new ArrayList<String>()); //add new row
				for(String str : lineArr) {
					disasters.get(disasters.size()-1).add(str);
				}
				
			}
			br.close();
		}
		catch (IOException e) {
			throw e;
		}
		return disasters;
	}
	
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		
		List<List<String>> disasters = new ArrayList<List<String>>();
		try {
			String line;
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {
				String[] lineArr = line.split(",");
				disasters.add(new ArrayList<String>()); //add new row
				for(String str : lineArr) {
					disasters.get(disasters.size()-1).add(str);
				}
				
			}
			br.close();
		}
		catch (IOException e) {
			throw e;
		}
		return disasters;
	}
	
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		int maxYear = 0;
		int maxDisasters = -1;
		for(int i = 0;i <records.size(); i++) {
			List<String> line = records.get(i);
			int currYear = Integer.parseInt(line.get(3));
			int currDisasters = 0;
			while(Integer.parseInt(line.get(3)) == currYear) {
				currDisasters++;
				i++;
				if(i <= records.size()) {
					break;
				}
				line = records.get(i);
			}
			if(currDisasters >= maxDisasters) {
				maxDisasters = currDisasters;
				maxYear = currYear;
			}
		}
		return new DisasterDescription(maxYear, "", 0);
	}

	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		for(int i = 0; i < records.size(); i ++) {
			if(!records.get(i).get(0).contentEquals(category)) {
				records.remove(i);
			}
		}
		return(getMostImpactfulYear(records));
	}

	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		for(int i = 0; i < records.size(); i ++) {
			if(!records.get(i).get(2).contentEquals(year)) {
				records.remove(i);
			}
		}
		String category = "";
		int max = 0;
		for(int i = 0; i < records.size(); i ++) {
			if(Integer.parseInt(records.get(i).get(3)) > max) {
				max = Integer.parseInt(records.get(i).get(3));
				category = records.get(i).get(0);
			}
		}
		return new DisasterDescription(0, category, 0);
	
	}

	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		// TODO implement this method
		return null;
	}
	
	/**
	 * This method will return the count if the number of incident falls within the provided range.
	 * To simplify the problem, we assume:
	 * 	+ A value of -1 is provided if the max range is NOT applicable.
	 *  + A min value can be provided, without providing a max value (which then has to be -1 like indicated above).
	 *  + If a max value is provided, then a max value is also needed.
	 */
	public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {
		// TODO implement this method
		return -1;
	}
	
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		return records1.size() > records2.size();
	}
	
	public static void main(String[] args) throws IOException {
		String naturalDisasterByTypeFile = "src/main/resources/natural-disasters-by-type.csv";
		
		String significantEarthquakeFileNameName = "src/main/resources/significant-earthquakes.csv";
		
		String significantVolcanicEruptionsFileName = "src/main/resources/significant-volcanic-eruptions.csv";
		CodingCompCSVUtil util = new CodingCompCSVUtil();
		
		List<List<String>> earthquakeRecords = util.readCSVFileByCountry(significantEarthquakeFileNameName, "United States");
		System.out.println(util.getMostImpactfulYear(earthquakeRecords).getYear());
	}
}
