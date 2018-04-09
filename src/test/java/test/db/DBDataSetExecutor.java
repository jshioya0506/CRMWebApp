package test.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.ObjectId;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.log4j.Logger;

import jp.co.nexus.crm.db.Area;
import jp.co.nexus.crm.db.Employee;
import jp.co.nexus.crm.db.NCCalldoc;
import jp.co.nexus.crm.db.NCCustomer;
import jp.co.nexus.crm.db.NCDivision;
import jp.co.nexus.crm.db.NCPerson;

/**
 * データベースのデータセット用
 * 
 * @author jshioya
 *
 */
public class DBDataSetExecutor {

	private static final Logger LOGGER = Logger.getLogger(DBDataSetExecutor.class);

	private static final File CSV_DIR = new File("doc/db/csv");

	public static void main(String[] args) {

		try {

			// DBの設定情報を取得
			ServerRuntime cayenneRuntime = new ServerRuntime("cayenne-NexusCRM.xml");
			ObjectContext context = cayenneRuntime.getContext();
			
			// エリアテーブル
			Map<Integer, Area> areaMap = createAreaMap(context);
						
			// 職員テーブル
			Map<Integer, Employee> employeeMap = createEmployeeMap(context);
			
			// 顧客管理テーブル
			Map<Integer, NCCustomer> customerMap 
				= createCustomerMap(context, areaMap, employeeMap);
			
			// 担当テーブル
			Map<Integer,NCPerson> personMap = createPersonMap(context, areaMap, employeeMap);
			
			// 部署テーブル
			Map<Integer,NCDivision> divisionMap = createDivisionMap(context);
			
			// 訪問記録テーブル
			Map<Integer,NCCalldoc> callDocMap = createCallDocMap(context);


			
			
			// 各テーブルにデータ登録
			context.commitChanges();

		} catch (IOException e) {
			LOGGER.error("エラーが発生しました。[" + e.getMessage() + "]");
			e.printStackTrace();
		}
	}

	private static Map<Integer, Area> createAreaMap(
			ObjectContext context) throws IOException {
		
		File csvFile = new File(CSV_DIR, DBConstants.AREA + ".csv");
		if (!csvFile.exists()) {
			throw new FileNotFoundException("CSVファイルが存在しません。[" + csvFile.getName() + "]");
		}
		
		// CSVからサンプルデータを読み込み
		LOGGER.info("エリアテーブルのcsvファイルからサンプルデータをロード開始。");
		CSVFileReader reader = new CSVFileReader();
		List<Map<String, Object>> dataMaps = reader.readData(csvFile);
		LOGGER.debug("dataMaps=" + dataMaps);
		
		// テーブルオブジェクトにデータを設定し、テーブルにデータを登録
		LOGGER.info("サンプルデータをエリアテーブルのモデルに設定。");
		Map<Integer, Area> areaMap = new HashMap<Integer, Area>();
		for (Map<String, Object> dataMap : dataMaps) {
			// データ登録対象のテーブルオブジェクトを取得
			Area dataObj = context.newObject(Area.class);
			// テーブルオブジェクトにデータを設定
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
				dataObj.writeProperty(entry.getKey(), entry.getValue());
			}
			int areaCode = dataObj.getAreaCd();
			areaMap.put(areaCode, dataObj);
		}
		return areaMap;
	}
	
	private static Map<Integer, Employee> createEmployeeMap(
			ObjectContext context) throws IOException {
		File csvFile = new File(CSV_DIR, DBConstants.EMPLOYEE + ".csv");
		if (!csvFile.exists()) {
			throw new FileNotFoundException("CSVファイルが存在しません。[" + csvFile.getName() + "]");
		}
		
		// CSVからサンプルデータを読み込み
		LOGGER.info("職員テーブルのcsvファイルからサンプルデータをロード開始。");
		CSVFileReader reader = new CSVFileReader();
		List<Map<String, Object>> dataMaps = reader.readData(csvFile);
		LOGGER.debug("dataMaps=" + dataMaps);
		
		// テーブルオブジェクトにデータを設定し、テーブルにデータを登録
		LOGGER.info("サンプルデータを職員テーブルのモデルに設定。");
		Map<Integer, Employee> employeeMap = new HashMap<Integer, Employee>();
		for (Map<String, Object> dataMap : dataMaps) {
			// データ登録対象のテーブルオブジェクトを取得
			Employee dataObj = context.newObject(Employee.class);;
			// テーブルオブジェクトにデータを設定
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
				dataObj.writeProperty(entry.getKey(), entry.getValue());
			}
			int empNo = dataObj.getEmpNo();
			employeeMap.put(empNo, dataObj);
		}
		return employeeMap;
	}
	
	private static Map<Integer, NCCustomer> createCustomerMap(
			ObjectContext context,
			Map<Integer, Area> areaMap,
			Map<Integer, Employee> employeeMap) throws IOException {
		File csvFile = new File(CSV_DIR, DBConstants.NC_CUSTOMER + ".csv");
		if (!csvFile.exists()) {
			throw new FileNotFoundException("CSVファイルが存在しません。[" + csvFile.getName() + "]");
		}
		
		// CSVからサンプルデータを読み込み
		LOGGER.info("顧客管理テーブルのcsvファイルからサンプルデータをロード開始。");
		CSVFileReader reader = new CSVFileReader();
		List<Map<String, Object>> dataMaps = reader.readData(csvFile);
		LOGGER.debug("dataMaps=" + dataMaps);
		
		// テーブルオブジェクトにデータを設定し、テーブルにデータを登録
		LOGGER.info("サンプルデータを顧客管理テーブルのモデルに設定。");
		
		int id = 1;
		Map<Integer, NCCustomer> customerMap = new HashMap<Integer, NCCustomer>();
		for (Map<String, Object> dataMap : dataMaps) {
			// データ登録対象のテーブルオブジェクトを取得
			NCCustomer dataObj = context.newObject(NCCustomer.class);
			// テーブルオブジェクトにデータを設定
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
				if ("area".equals(entry.getKey())) {
					Integer areaCd = (Integer)entry.getValue();
					Area area = areaMap.get(areaCd);
					dataObj.setArea(area);
					area.getCustomers().add(dataObj);
				}else if ("customer".equals(entry.getKey())) {
					Integer empNo = (Integer)entry.getValue();
					Employee employee = employeeMap.get(empNo);
					dataObj.setEmployee(employee);
					employee.getCustomers().add(dataObj);
				} else {
					dataObj.writeProperty(entry.getKey(), entry.getValue());
				}
			}
			customerMap.put(Integer.valueOf(id), dataObj);
		}
		return customerMap;
	}
	
	private static Map<Integer, NCCalldoc> createCallDocMap(ObjectContext context) throws IOException {
		File csvFile = new File(CSV_DIR, DBConstants.NC_CALLDOC + ".csv");
		if (!csvFile.exists()) {
			throw new FileNotFoundException("CSVファイルが存在しません。[" + csvFile.getName() + "]");
		}
		
		// CSVからサンプルデータを読み込み
		LOGGER.info("訪問記録テーブルのcsvファイルからサンプルデータをロード開始。");
		CSVFileReader reader = new CSVFileReader();
		List<Map<String, Object>> dataMaps = reader.readData(csvFile);
		LOGGER.debug("dataMaps=" + dataMaps);
		
		// テーブルオブジェクトにデータを設定し、テーブルにデータを登録
		LOGGER.info("サンプルデータを訪問記録テーブルのモデルに設定。");
		
		int id = 1;
		Map<Integer, NCCalldoc> calldocMap = new HashMap<Integer, NCCalldoc>();
		for (Map<String, Object> dataMap : dataMaps) {
			// データ登録対象のテーブルオブジェクトを取得
			NCCalldoc dataObj = context.newObject(NCCalldoc.class);
			// テーブルオブジェクトにデータを設定
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
				dataObj.writeProperty(entry.getKey(), entry.getValue());
			}
			
			calldocMap.put(Integer.valueOf(id), dataObj);
		}
		return calldocMap;
	}

	private static Map<Integer, NCDivision> createDivisionMap(ObjectContext context) throws IOException {
		File csvFile = new File(CSV_DIR, DBConstants.NC_DIVISION + ".csv");
		if (!csvFile.exists()) {
			throw new FileNotFoundException("CSVファイルが存在しません。[" + csvFile.getName() + "]");
		}
		
		// CSVからサンプルデータを読み込み
		LOGGER.info("部署テーブルのcsvファイルからサンプルデータをロード開始。");
		CSVFileReader reader = new CSVFileReader();
		List<Map<String, Object>> dataMaps = reader.readData(csvFile);
		LOGGER.debug("dataMaps=" + dataMaps);
		
		// テーブルオブジェクトにデータを設定し、テーブルにデータを登録
		LOGGER.info("サンプルデータを部署テーブルのモデルに設定。");
		
		int id = 1;
		Map<Integer, NCDivision> divisionMap = new HashMap<Integer, NCDivision>();
		for (Map<String, Object> dataMap : dataMaps) {
			// データ登録対象のテーブルオブジェクトを取得
			NCDivision dataObj = context.newObject(NCDivision.class);
			// テーブルオブジェクトにデータを設定
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
				dataObj.writeProperty(entry.getKey(), entry.getValue());
			}
			
			divisionMap.put(Integer.valueOf(id), dataObj);
		}
		return divisionMap;
	}

	private static Map<Integer, NCPerson> createPersonMap(
			ObjectContext context,
			Map<Integer, Area> areaMap,
			Map<Integer, Employee> employeeMap) throws IOException {
		File csvFile = new File(CSV_DIR, DBConstants.NC_PERSON + ".csv");
		if (!csvFile.exists()) {
			throw new FileNotFoundException("CSVファイルが存在しません。[" + csvFile.getName() + "]");
		}
		
		// CSVからサンプルデータを読み込み
		LOGGER.info("担当者テーブルのcsvファイルからサンプルデータをロード開始。");
		CSVFileReader reader = new CSVFileReader();
		List<Map<String, Object>> dataMaps = reader.readData(csvFile);
		LOGGER.debug("dataMaps=" + dataMaps);
		
		// テーブルオブジェクトにデータを設定し、テーブルにデータを登録
		LOGGER.info("サンプルデータを担当者テーブルのモデルに設定。");
		
		int id = 1;
		Map<Integer, NCPerson> divisionMap = new HashMap<Integer, NCPerson>();
		for (Map<String, Object> dataMap : dataMaps) {
			// データ登録対象のテーブルオブジェクトを取得
			NCPerson dataObj = context.newObject(NCPerson.class);
			// テーブルオブジェクトにデータを設定
			for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
				if ("area".equals(entry.getKey())) {
					Integer areaCd = (Integer)entry.getValue();
					Area area = areaMap.get(areaCd);
					dataObj.setArea(area);
					area.getPersons().add(dataObj);
				}else if ("customer".equals(entry.getKey())) {
					Integer empNo = (Integer)entry.getValue();
					Employee employee = employeeMap.get(empNo);
					dataObj.setEmployee(employee);
					employee.setPerson(dataObj);
				} else {
					dataObj.writeProperty(entry.getKey(), entry.getValue());
				}
			}
			
			divisionMap.put(Integer.valueOf(id), dataObj);
		}
		return divisionMap;
	}
}
