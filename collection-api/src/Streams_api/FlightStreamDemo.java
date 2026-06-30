package Streams_api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlightStreamDemo {
	public static void main(String[] args) throws Exception {
		Stream<String> lines = Files.lines(Paths.get("src/flights"));
		
		List<Flight> flights = lines.map(line -> {
		
			String[] record = line.split(",");
			return new Flight(Integer.parseInt(record[0]),record[1],record[2],record[3]);
					}).collect(Collectors.toList());
			
			//flights.forEach(System.out::println); 
			
			flights.stream().filter(f ->f.getCode()==123).forEach(System.out::println); //Print details of flight no 123
			
			flights.stream().filter(f ->f.getCarrier().equals("Jet")).forEach(System.out::println); //all flight with carrier=Jet
			
			flights.stream().filter(f ->f.getSource().equals("Pune") && f.getDestination().equals("Delhi")).forEach(System.out::println); //all flight with source pune and destination delhi
			
			flights.removeIf(f ->f.getCode()==920); // remove flight with code 920
			
		
	}

}
