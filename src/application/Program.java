package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Employee> employees = new ArrayList<>();
		
		String path = "/home/mozao/eclipse-workspace/udemyLambda2/inLambda2.csv";
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String line = br.readLine();
			while(line != null) {
				String[] data = line.split(",");
				employees.add(new Employee(data[0], data[1], Double.parseDouble(data[2])));
				line = br.readLine();
			}
		}catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		System.out.println("Enter full file path: " + path);
		System.out.print("Enter salary: ");
		double salary = sc.nextDouble();
		
		System.out.println("Email of people whose salary is more than "
				+ salary);
		
		Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
		
		List<String> list = employees.stream()
				.filter(s -> s.getSalary() > salary)
				.map(e -> e.getEmail())
				.sorted(comp)
				.collect(Collectors.toList());
		
		list.forEach(email -> System.out.println(email));
		
		
		Double sumMName = employees.stream()
				.filter(s -> s.getName().charAt(0) == 'M')
				.map(e -> e.getSalary())
				.reduce(0.0,(s1, s2) -> s1+s2);
		
		System.out.println("Sum of salary of people whose name starts with"
				+ " 'M': " + sumMName);
		
		sc.close();
	}

}
