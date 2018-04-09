package test.db;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class DBConstants {
	public static final File CSV_DIR = new File("doc/db/csv");
	
	public static final String NC_CUSTOMER = "NC_CUSTOMER";
	public static final String NC_PERSON = "NC_PERSON";
	public static final String NC_CALLDOC = "NC_CALLDOC";
	public static final String NC_OUTSIDE_MAKE = "NC_OUTSIDE_MAKE";
	public static final String AREA = "AREA";
	public static final String NC_STANDARD = "NC_STANDARD";
	//public static final String NC_COMMON_NM = "NC_COMMON_NM";
	public static final String EMPLOYEE = "EMPLOYEE";
	public static final String NC_DIVISION = "NC_DIVISION";
	
	public static final String[] TABLES = new String[] {
			AREA,
			EMPLOYEE,
			NC_CUSTOMER,
			NC_PERSON,
			NC_CALLDOC,
//			NC_OUTSIDE_MAKE,
//			NC_STANDARD,
			NC_DIVISION
	};
	
	public static final Map<Integer, Integer> employee2areaCdMap 
		= new HashMap<Integer, Integer>();
	
	static {
		employee2areaCdMap.put(1, 1);
		employee2areaCdMap.put(2, 2);
		employee2areaCdMap.put(3, 2);
		employee2areaCdMap.put(4, 2);
		employee2areaCdMap.put(5, 2);
	}
}
