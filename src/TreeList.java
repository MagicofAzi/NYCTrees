import java.util.ArrayList;

public class TreeList extends ArrayList<Tree>{

	private static final long serialVersionUID = 1L;
	
	public TreeList() {
	}
	/*
	 * Returns the number of trees in the TreeList
	 */	
	public int getTotalNumberOfTrees() {
		return this.size();	
	}
	/*
	 * Returns the number of trees of a specific species
	 */
	public int getCountByTreeSpecies ( String speciesName ) {
		int counter = 0;
		for(int i = 0; i < this.size(); i++) {
			if(this.get(i).getSpc().contains(speciesName)) {
				counter++;
			}
		}
		return counter;	
	}
	/*
	 * Returns an Int with number of trees per borough
	 */
	public int getCountByBorough ( String boroName ) {
		int counter = 0;
		for(int i = 0; i < this.size(); i++) {
			if(this.get(i).getBoro().equalsIgnoreCase(boroName)) {
				counter++;
			}
		}
		return counter;
	}
	/*
	 * Counts amount of trees per borough
	 */
	public int getCountByTreeSpeciesBorough ( String speciesName, String boroName ) {
		int counter = 0;
		for(int i = 0; i < this.size(); i++) {
			if(this.get(i).getBoro().equalsIgnoreCase(boroName) && this.get(i).getSpc().contains(speciesName)) {
				counter++;
			}
		}
		return counter;
	}
	/*
	 * Returns a string of species that are the same, does not repeat
	 */
	public ArrayList<String> getMatchingSpecies(String speciesName) {
		ArrayList<String> matched = new ArrayList<>();
		matched.add(0,"All matching species:");
		for(int i = 0; i < this.size(); i++) {
			if(this.get(i).getSpc().contains(speciesName) && !(matched.contains(this.get(i).getSpc()))) {
				matched.add(this.get(i).getSpc());
			}	
		}
		//Only has "All matching species" in array list
		if(matched.size() == 1) {
			matched.remove(0);
			matched.add("There is no record of " + speciesName + " on NYC streets \n");
		}
		return matched;	
	}
	/*
	 * Overridden toString that size of TreeList
	 */
	@Override
	public String toString() {
		return "This treelist contains " + this.size() + " trees." ;

	}
	/*
	 * Checks to see if two trees have same id but different species name, 
	 * throws exception and skips. 
	 */
	public boolean checkTrees(Tree o) {
		for(int i = 0; i < this.size(); i++) {
			if(this.get(i).getId() == o.getId() && !(this.get(i).getSpc().equalsIgnoreCase(o.getSpc()))) {
				throw new IllegalArgumentException("There already exists a species with this id and species name.");

			}
			
		}
		return true;
	}
	

}
