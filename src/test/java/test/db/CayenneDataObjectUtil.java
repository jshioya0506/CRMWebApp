package test.db;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.ObjectContext;

import jp.co.nexus.crm.db.Area;
import jp.co.nexus.crm.db.Employee;
import jp.co.nexus.crm.db.NCCalldoc;
import jp.co.nexus.crm.db.NCCustomer;
import jp.co.nexus.crm.db.NCDivision;
import jp.co.nexus.crm.db.NCOutsideMake;
import jp.co.nexus.crm.db.NCPerson;
import jp.co.nexus.crm.db.NCStandard;

public final class CayenneDataObjectUtil {

	public static CayenneDataObject findDataObject(
			ObjectContext context,
			String tableName) {
		if (DBConstants.EMPLOYEE.equals(tableName)) {
			return context.newObject(Employee.class);
		} else if (DBConstants.AREA.equals(tableName)) {
			return context.newObject(Area.class);
		} else if (DBConstants.NC_CUSTOMER.equals(tableName)) {
			return context.newObject(NCCustomer.class);
		} else if (DBConstants.NC_PERSON.equals(tableName)) {
			return context.newObject(NCPerson.class);
		} else if (DBConstants.NC_CALLDOC.equals(tableName)) {
			return context.newObject(NCCalldoc.class);
		} else if (DBConstants.NC_OUTSIDE_MAKE.equals(tableName)) {
			return context.newObject(NCOutsideMake.class);
		} else if (DBConstants.NC_STANDARD.equals(tableName)) {
			return context.newObject(NCStandard.class);
		} else if (DBConstants.NC_DIVISION.equals(tableName)) {
			return context.newObject(NCDivision.class);
		} else {
			throw new RuntimeException("Table class not found.[" + tableName + "]");
		}
	}
	
}
