package pageObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveDuplicates {
	   
	public static void main(String[] args) {
		
		List<String> input= Arrays.asList("a","d","a","d","e");
		List<String> result=removeDuplicates(input);
		System.out.println(result);
	}
	
	public static List<String> removeDuplicates(List<String> input){
		
		List<String> result=new  ArrayList();
		
		for(String element:input) {
			boolean isDuplicate=false;
			
				for(String unique :result) {
					if(element.equalsIgnoreCase(unique)) {
						isDuplicate=true;
					}
				}
			if(!isDuplicate) {
				result.add(element);
			}
		}
		
		return result;
		
	}
}


