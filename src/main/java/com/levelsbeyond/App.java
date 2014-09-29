package com.levelsbeyond;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Hello world!
 */
public class App
{
	public static void main(String[] args)
	{
		new App();
	}

	public App() {
		Person jon = new Person("Tulsa", "Jon", 42);
		Person steve = new Person("London", "Steve", 23);
		Person amy = new Person("New York", "Amy", 29);
		Person cobb = new Person("Denver", "Cobb", 29);
		Person james = new Person("Denver", "James", 43);

		List<Person> people = Arrays.asList(jon, steve, amy, cobb, james);

		//set of ages
		Set<Integer> ages = people.stream()
				.map(p -> new Integer(p.getAge()))
				.collect(toSet());

		System.out.println(ages);

		//classify people by age
		Map<Integer, List<Person>> map = people.stream()
				.collect(groupingBy(Person::getAge));

		System.out.println(map);

		//sort based on length of city name
		List<Person> peeps = people.stream()
				.sorted(Comparator.comparing(p -> p.getCity().length()))
				.collect(toList());
		System.out.println(peeps);

		//group by city, produce comma separated names
		Map<String, String> names = people.stream()
				.collect(groupingBy(Person::getCity, mapping(Person::getName, joining(","))));
		System.out.println(names);
	}

	private class Person {
		private final String name;
		private final Integer age;
		private final String city;

		public String getName() {
			return name;
		}

		public Integer getAge() {
			return age;
		}

		public String getCity() {
			return city;
		}

		private Person( String city,String name, Integer age) {
			this.name = name;

			this.age = age;
			this.city = city;
		}

		@Override
		public String toString() {
			return name + " " + age + " " + city;
		}
	}
}
