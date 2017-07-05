import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class NYCStreetTrees {

	public NYCStreetTrees() {
		
	}
	//Splits a line from a cvs file into an arraylist of Strings.
	public static ArrayList<String> splitCSVLine(String textLine) {
		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry = false;
		
		//iterate over all characters in the textLine
		for (int i = 0; i< lineLength; i++) {
			nextChar = textLine.charAt(i);
			
			//handle smart quotes as well as regular quotes
			if (nextChar =='"' || nextChar == '\u201C' || nextChar =='\u201D') {
				//change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false;
				}
				else {
					insideQuotes = true;
					insideEntry = true;
				}
			}
			else if (Character.isWhitespace(nextChar)) {
				if (insideQuotes || insideEntry) {
					//add it to the current entry
					nextWord.append(nextChar);
				}
				else {  //skip all spaces between entries
					continue;
				}
			}
			else if (nextChar == ',') {
				if (insideQuotes) // comma inside an entry
					nextWord.append(nextChar);
				else {
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			}
			else {
				//add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			}	
		}
		//add the last word ( assuming not empty )
		//trim the white space before adding to the list
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}
		return entries;
	}

	public static void main(String[] args) throws FileNotFoundException {
		//Starts all values as null so try and catch block can commence and variables can be used later.
		Scanner input = null;
		String File = null;
		File csvFile = null;
		Scanner scan = null;
		
		//Throws 3 exceptions incase wrong array value, no files, wrong arg
		try {
			input = new Scanner(System.in);
			File = args[0]; 
			csvFile = new File(File);
			scan = new Scanner(csvFile);
			
		}catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("");
			System.exit(0);
			
		}catch (IllegalArgumentException e) {
			System.out.println("");
			System.exit(0);
			
		}catch (FileNotFoundException e) {
			System.out.println("");
			System.exit(0);
			
		}
		//New Treelist Array
		TreeList NYCtrees = new TreeList();
		//Skips header
		scan.nextLine();
		//While loop to cycle through complete file.
		while(scan.hasNextLine()) {
			
			String line = scan.nextLine();
			
			line = line.toLowerCase();
			//Uses splitLine Method
			ArrayList<String> splitLine = splitCSVLine(line);
			
			
			//Creates new tree objects with specific tree parameters
			try {
				Tree tree = new Tree( Integer.parseInt(splitLine.get(0)), Integer.parseInt(splitLine.get(3)), splitLine.get(6),
						splitLine.get(7), splitLine.get(9),Integer.parseInt(splitLine.get(25)), splitLine.get(29), 
						Double.parseDouble(splitLine.get(39)), Double.parseDouble(splitLine.get(40)) );
				//Checks for same id different species.
				if(NYCtrees.checkTrees(tree)){
					NYCtrees.add(tree);
				} 
			}catch(IllegalArgumentException e){
				e.getMessage();
			}
		
			
			
		
		}
		//User input for tree name
		System.out.println("Enter the tree species to learn more about it (\"quit\" to stop):");
		String user = input.nextLine();
		user = user.toLowerCase();
		while(!(user.equalsIgnoreCase("quit"))) {
			
			
			//Returns a  ArrayList of strings of all matching trees
			ArrayList<String> matchedTrees = NYCtrees.getMatchingSpecies(user);
			for(int i = 0; i < matchedTrees.size(); i++) {
				System.out.println(matchedTrees.get(i)); 
			}
			
			//If there are records of the specified tree, print info
			if(matchedTrees.size() > 1) {
				System.out.println("Popularity in the city:");
				
				System.out.println("\tNYC            : " + NYCtrees.getCountByTreeSpecies(user) + 
						"(" + NYCtrees.getTotalNumberOfTrees() + ") " + 
						Math.round((10000.0*NYCtrees.getCountByTreeSpecies(user)/NYCtrees.getTotalNumberOfTrees()))/100.0 + "%");
				
				System.out.println("\tManhattan      : " + NYCtrees.getCountByTreeSpeciesBorough(user, "Manhattan") + 
						"(" + NYCtrees.getCountByBorough("Manhattan") + ") " + 
						Math.round((10000.0*NYCtrees.getCountByTreeSpeciesBorough(user, "Manhattan")/NYCtrees.getCountByBorough("Manhattan")))/100.0 + "%");
				
				System.out.println("\tBronx          : " + NYCtrees.getCountByTreeSpeciesBorough(user, "Bronx") + 
						"(" + NYCtrees.getCountByBorough("Bronx") + ") " + 
						Math.round((10000.0*NYCtrees.getCountByTreeSpeciesBorough(user, "Bronx")/NYCtrees.getCountByBorough("Bronx")))/100.0 + "%");
				
				System.out.println("\tBrooklyn       : " + NYCtrees.getCountByTreeSpeciesBorough(user, "Brooklyn") + 
						"(" + NYCtrees.getCountByBorough("Brooklyn") + ") " + 
						Math.round((10000.0*NYCtrees.getCountByTreeSpeciesBorough(user, "Brooklyn")/NYCtrees.getCountByBorough("Brooklyn")))/100.0 + "%");
				
				System.out.println("\tQueens         : " + NYCtrees.getCountByTreeSpeciesBorough(user, "Queens") + 
						"(" + NYCtrees.getCountByBorough("Queens") + ") " + 
						Math.round((10000.0*NYCtrees.getCountByTreeSpeciesBorough(user, "Queens")/NYCtrees.getCountByBorough("Queens")))/100.0 + "%");
				
				System.out.println("\tStaten Island  : " + NYCtrees.getCountByTreeSpeciesBorough(user, "Staten Island") + 
						"(" + NYCtrees.getCountByBorough("Staten Island") + ") " + 
						Math.round((10000.0*NYCtrees.getCountByTreeSpeciesBorough(user, "Staten Island")/NYCtrees.getCountByBorough("Staten Island")))/100.0 + "%\n");
				
			}
			System.out.println("Enter the tree species to learn more about it (\"quit\" to stop):");
			user = input.nextLine();
			user = user.toLowerCase();
		}
		input.close();
		scan.close();
		
		int i = 0;
	}
}
