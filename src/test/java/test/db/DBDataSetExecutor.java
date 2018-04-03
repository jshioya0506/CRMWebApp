package test.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.log4j.Logger;

import jp.co.nexus.crm.db.Area;
import jp.co.nexus.crm.db.Employee;

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
			Area dataObj = context.newObject(Area.class);;
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
			int areaCode = dataObj.getEmpNo();
			employeeMap.put(areaCode, dataObj);
		}
		return employeeMap;
	}
}
