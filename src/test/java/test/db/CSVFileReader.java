package test.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CSVファイルからサンプルデータを収集するクラス
 * @author jshioya
 *
 */
public class CSVFileReader {

	/**
	 * CSVファイルからサンプルデータを収集する
	 * @param csvFile
	 * @return
	 * @throws IOException
	 */
	public List<Map<String, Object>> readData(File csvFile) throws IOException {
		BufferedReader reader = null;
		int rowNo = 1;
		
		List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
		
		String[] names = null;
		String[] dataTypes = null;
		List<String[]> bodys = new ArrayList<String[]>();
		try {
			reader = new BufferedReader(new FileReader(csvFile));
			while (reader.ready()) {
				String[] record = reader.readLine().split(",");
				switch (rowNo) {
				case 1:
					names = record;
					break;
				case 2:
					dataTypes = record;
					break;
				default:
					bodys.add(record);
					break;
				}
				rowNo++;
			}
			
			for (String[] record : bodys) {
				int column = 0;
				Map<String, Object> dataMap = new HashMap<String, Object>();
				for (String value: record) {
					if (!value.isEmpty() 
							&& "int".equals(dataTypes[column])) {
						dataMap.put(names[column], Integer.valueOf(value));
					} else {
						dataMap.put(names[column], value);
					}
					column++;
				}
				records.add(dataMap);
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return records;
	}
	
	
}
