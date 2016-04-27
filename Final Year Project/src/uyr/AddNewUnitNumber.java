package uyr;

import java.util.*;

public class AddNewUnitNumber {
	
	//First checks the unit numbers in the file being imported, then only adds new unit numbers to the database
	public static void addUnitNumber(ArrayList<ArrayList<String>> rawRailDataFile){
		HashSet<String> uniqueUnitNumbersInFile = new HashSet<String>();
		uniqueUnitNumbersInFile.add(rawRailDataFile.get(0).get(0));
		ArrayList<String> allUnitNumbers = SQLDatabase.selectAllFromUnit("SELECT Unit_No FROM Unit");
		for(int b = 0; b < allUnitNumbers.size(); b++){
			if(uniqueUnitNumbersInFile.contains(allUnitNumbers.get(b))){
				uniqueUnitNumbersInFile.remove(allUnitNumbers.get(b));
			}
		}
		SQLDatabase.addNewUnitNumbers(uniqueUnitNumbersInFile, "INSERT INTO Unit (Unit_No) VALUES (?)");
	}
	
}
