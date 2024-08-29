package streamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
		
		List<Integer> nums = numbers.stream()
			.filter(n -> n%2==0)
			.collect(Collectors.toList());
		
		System.out.println(nums);
		
		List<String> words = Arrays.asList("My", "name", "is", "Kumar");
		
		List<Character> chars = words.stream()
				.flatMap(word->word.chars().mapToObj(c-> (char) c))
				.collect(Collectors.toList());
		
		System.out.println("chars: "+chars);
		
			
		
		
		
	}
}
