

import seatAllocation.MeritOrderAdmission;
import galeShapley.GaleShapleyAdmission;

/**
 * The constructors of the two methods are called and thus 
 * @author Sandeep
 * @param Address of the directory in which the three files i.e. programs.csv, ranklist.csv and choices.csv
 */
public class Main {

	public static void main(String[] args) {
			
		MeritOrderAdmission test = new MeritOrderAdmission(args[0]);
		GaleShapleyAdmission test2 = new GaleShapleyAdmission(args[0]);
		
	}

}
