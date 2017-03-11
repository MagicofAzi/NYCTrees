
public class Tree implements Comparable<Tree>{
	
	public int id;
	
	public int diam;
	
	public String status;
	
	public String health;
	
	public String spc;
	
	public int zip;
	
	public String boro;
	
	public double x;
	
	public double y; 
	
	/*
	 * Constructor taking 9 parameters. Each parameter has
	 * specific values and has appropriate exception handling.
	 */
	public Tree ( int id, int diam, String status, String health, String spc,int zip, String boro, double x, double y ) {
		/*
		 * Setter for ID, does not take negative values.
		 * throws Illegal Args Exception
		 */
		if( id < 0) throw new IllegalArgumentException("The id cannot be negative");
		else {
			this.id = id;
		}
		/*
		 * Setter for diameter, does not take negative values,
		 * throws Illegal Args Exception
		 */
		if( diam < 0) throw new IllegalArgumentException("The diameter cannot be negative"); 
		else {
			this.diam = diam;
		}
		/*
		 * Setter for status as string
		 */
		this.status = status;
		/*
		 * Setter for health as string
		 */
		this.health = health;
		/*
		 * Setter for species as string, does not take null values
		 * throws Illegal Args Exception
		 */
		if( spc == null) throw new IllegalArgumentException("The species cannot be null"); 
		else {
			this.spc = spc;
		}
		/*
		 * Setter for zip, Non negative and less than 99999 as legal zip codes,
		 * throws Illegal args exception
		 */
		if( zip < 0 || zip > 99999)  throw new IllegalArgumentException("The zipcode cannot be negative"); {
			this.zip = zip; 
		}
		
		/*
		 * Setter for Borough, takes only 5 possible answers.
		 */
		if ((boro.equalsIgnoreCase("manhattan")) || (boro.equalsIgnoreCase("queens")) 
			|| (boro.equalsIgnoreCase("brooklyn")) || (boro.equalsIgnoreCase("staten island")) || (boro.equalsIgnoreCase("bronx")) ) 
			this.boro = boro;
		else {
			throw new IllegalArgumentException("The borough is not recognized.");
		}
		
		
		/*
		 * Setter for X as a string
		 */
		this.x = x;
		/*
		 * Setter for X as a string
		 */
		this.y = y;
				
			}

		/*
		 * Return int of ID
		 */
		public int getId() {
			return id;
		}

		/*
		 * Return int of Diam
		 */
		public int getDiam() {
			return diam;
		}
		
		/*
		 * Return String of Status
		 */
		public String getStatus() {
			return status;
		}

		/*
		 * Return String of Health
		 */
		public String getHealth() {
			return health;
		}

		/*
		 * Return String of Species name
		 */
		public String getSpc() {
			return spc;
		}

		/*
		 * Return int of Zip
		 */
		public int getZip() {
			return zip;
		}

		/*
		 * Return String of Borough
		 */
		public String getBoro() {
			return boro;
		}

		/*
		 * Return double of X coordinate
		 */
		public double getX() {
			return x;
		}

		/*
		 * Return double of Y coordinate
		 */
		public double getY() {
			return y;
		}
		
		/*
		 * Overridden toString that returns species name and location
		 */
		@Override
		public String toString() {
			return "I am a " + spc + " tree residing in " + boro;

		}

		/*
		 * Overridden compareTo that returns 1 for greater, 0 for equal, -1 for lesser
		 */
		@Override
		public int compareTo(Tree o) {
			if(this.getSpc().equalsIgnoreCase(o.getSpc())){
				if(this.getId() > o.getId()){
					return 1;
				}
				else if(this.getId() == o.getId()){
					return 0;
				}
				else {
					return -1;
				}
			}
			return this.getSpc().compareTo(o.getSpc());
	
				
		}
		
		/*
		 * Equals method that returns true if two tree objects are equal
		 */
		public boolean equals(Tree o)  {
			if(this.getId() == o.getId() && !(this.getSpc().equalsIgnoreCase(o.getSpc()))) {
				throw new IllegalArgumentException("Two objects cannot have the same Id and different species names.");
			}
			return true;
		}
		
		

}
