package Streams_api;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapStreamDemo {
	public static void main(String[] args){
		Map<String,String> people = new HashMap<>();
		//people.put("Polo","Pune");
		//people.put("Mili","Mumbai");
		//people.put("Deny","Delhi");
		
		//people.values().stream().forEach(System.out::println);
		
		//List<String> cities = people.values().stream().map(c -> c.toUpperCase()).sorted().collect(Collectors.toList());
		
		//cities.forEach(System.out::println);
		
		Map<String, List<String>> contacts = new HashMap<>();
		contacts.put("Frudo", Arrays.asList("1212-3434","5656-7878"));
		contacts.put("Sean", Arrays.asList("2112-3534","5666-7888","8989-5050"));
		contacts.put("Ben", Arrays.asList("5633-1188","8090-2321"));
		
		//contacts.values().stream().flatMap(Collection::stream).forEach(System.out::println);
		
		contacts.values().stream().flatMap(Collection::stream).filter(num -> num.length()==8).forEach(System.out::println);
		
		//Print all phone number  removing "-"
		contacts.values().stream().flatMap(Collection::stream).filter(num -> num.replace("-","").length()==8).forEach(System.out::println);
		
		//Print all phone number containing 8
		
		contacts.values().stream().flatMap(Collection::stream).map(num -> num.replace("-","").length()).forEach(System.out::println);
		
	
	}

}
