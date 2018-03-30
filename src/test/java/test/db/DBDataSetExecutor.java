package test.db;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * データベースのデータセット用
 * @author jshioya
 *
 */
public class DBDataSetExecutor {
	
	private static final Logger LOGGER = Logger.getLogger(DBDataSetExecutor.class);
	
	private static final File CSV_DIR = new File("doc/db/csv");
	
	public static void main(String[] args) {
		for (String tableName : DBConstants.TABLES) {
			File csvFile = new File(CSV_DIR, tableName + ".csv");
			if (!csvFile.exists()) {
				System.err.println("CSVファイルが存在しません。[" + csvFile.getName() + "]");
				continue;
			}
			
			try {
				// CSVからサンプルデータを読み込み
				LOGGER.info("csvファイルからサンプルデータをロード開始。");
				CSVFileReader reader = new CSVFileReader();
				List<Map<String, Object>> dataMaps = reader.readData(csvFile);
				LOGGER.debug("dataMaps=" + dataMaps);
				LOGGER.info("csvファイルからサンプルデータをロード完了。");
				
				// サンプルデータをテーブルに登録
				LOGGER.info("サンプルデータをテーブルに登録開始。");
				Object2DBTable conv = new Object2DBTable();
				conv.registerdTable(tableName, dataMaps);
				LOGGER.info("サンプルデータをテーブルに登録完了。");
				
			} catch (IOException e) {
				LOGGER.error("エラーが発生しました。[" + e.getMessage() + "]");
				e.printStackTrace();
			}
		}
	}
}
