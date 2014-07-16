import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashSet;
import java.io.*;

public class shapeWaysTest {

	private HashMap<String, HashSet<Integer>> artistMapLine; // Map with artist
																// as key and
																// occurrence
																// based on line
																// number as
																// value
	private int lineNumber; // Counter to calculate line number
	private String inputFileName;
	private String outputFileName;

	public shapeWaysTest(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		this.lineNumber = 0;
	}

	public void calculatePairs() throws IOException {

		FileReader fileReader = new FileReader(inputFileName);
		;
		FileWriter fileWriter = new FileWriter(outputFileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		artistMapLine = new HashMap<String, HashSet<Integer>>();
		HashSet<Integer> tempHashSet; // Set used to insert items into
										// artistMapLine
		HashSet<Integer> intersection; // Set that holds the intersection of
										// occurrences of artists
		ArrayList<String> tempWrite = new ArrayList<String>(); // Temporary Set
																// used to write
																// into the file
		while (bufferedReader.ready()) {

			String line = bufferedReader.readLine();
			++lineNumber;

			String artist[] = line.split(",");

			for (int i = 0; i < artist.length; i++) {

				if (!artistMapLine.containsKey(artist[i])) {

					tempHashSet = new HashSet<Integer>();
					tempHashSet.add(lineNumber);
					artistMapLine.put(artist[i], tempHashSet);

				}

				else {
					tempHashSet = artistMapLine.get(artist[i]);
					tempHashSet.add(lineNumber);
					artistMapLine.put(artist[i], tempHashSet);

				}

			}

		}

		// Loop to remove when number of occurrences is less than 50 which gives
		// the reduced set to consider
		for (Iterator<Map.Entry<String, HashSet<Integer>>> it = artistMapLine
				.entrySet().iterator(); it.hasNext();) {

			Map.Entry<String, HashSet<Integer>> entry = it.next();

			if (entry.getValue().size() < 50) {
				it.remove();

			}

		}

		// Loop to find intersection of occurrences between 2 artists (pair)
		for (Entry<String, HashSet<Integer>> entry1 : artistMapLine.entrySet()) {

			String key1 = entry1.getKey();
			HashSet value1 = entry1.getValue();
			int hash1 = System.identityHashCode(key1);

			for (Entry<String, HashSet<Integer>> entry2 : artistMapLine
					.entrySet()) {

				String key2 = entry2.getKey();
				if (hash1 >= System.identityHashCode(key2)) // to avoid
															// duplicate
															// comparisons
					continue;

				else {

					intersection = (HashSet<Integer>) value1.clone();
					HashSet value2 = entry2.getValue();
					intersection.retainAll(value2);
					if (intersection.size() >= 50) { // if size of intersecting
														// set is greater than
														// 50 write to temporary
														// hashSet

						tempWrite.add(key1 + "," + key2 + "\n");

					}

				}

			}

		}
		// Write into file output.txt
		for (String str : tempWrite) {
			fileWriter.write(str);
		}
		fileWriter.flush();

		bufferedReader.close();
		fileWriter.close();
	}
}
