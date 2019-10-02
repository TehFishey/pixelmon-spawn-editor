package com.github.tehfishey.spawnedit.model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class SpawnEntryTest {
	
	@Test
	public void testCartesianParts() {
		HashMap<String, Object[]> hash = new HashMap<String, Object[]>();
		String[] array1 = new String[3];
		Integer[] array2 = new Integer[2];
		String[] array3 = new String[4];
		
		array1[0] = "A";
		array1[1] = "B";
		array1[2] = "C";
		
		array2[0] = 1;
		array2[1] = 2;
		
		array3[0] = "Bacon";
		array3[1] = "Eggs";
		array3[2] = "Foo";
		array3[3] = "Bar";
		
		hash.put("strings", array1);
		hash.put("ints", array2);
		hash.put("references", array3);
		
		ArrayList<HashMap<String, Object>> output = new ArrayList<HashMap<String, Object>>();
		Iterator<Entry<String,Object[]>> iter = hash.entrySet().iterator();
		cartesianProductLoop(hash,  output, new HashMap<String, Object>());
		
		System.out.println(output);
	}
	
	private void cartesianProductLoop(HashMap<String, Object[]> arraysInput, ArrayList<HashMap<String, Object>> output, HashMap<String, Object> subOutput) {
		if (arraysInput.size() == 0) {
			output.add(subOutput);
		}
		else {
			String key = arraysInput.keySet().iterator().next();
			Object[] currentArray = arraysInput.get(key);
			
			for (int i = 0; i < currentArray.length; i++)
			{
				HashMap<String, Object> newSubOutput = new HashMap<String, Object>(subOutput);
				HashMap<String, Object[]> newSubInput = new HashMap<String, Object[]>(arraysInput);
				newSubInput.remove(key);
				newSubOutput.put(key, currentArray[i]);
				cartesianProductLoop(newSubInput,output,newSubOutput);
			}
		}
	}
	
	/*private void cartesianProductLoop(HashMap<String, Object[]> arrays, ArrayList<HashMap<String, Object>> output, HashMap<String, Object> subOutput, Iterator<Entry<String,Object[]>> iter) {
		if (!iter.hasNext())
			output.add(subOutput);
		else {
			Entry<String, Object[]> currentArray = iter.next();
			
			for (int i = 0; i < currentArray.getValue().length; i++)
			{
				HashMap<String, Object> newSubOutput = (HashMap<String, Object>) subOutput.clone();
				newSubOutput.put(currentArray.getKey(), currentArray.getValue()[i]);
				System.out.println("Searching: " + Arrays.toString(currentArray.getValue()));
				cartesianProductLoop(arrays,output,newSubOutput, iter);
			}
		}
	}
	*/
}


