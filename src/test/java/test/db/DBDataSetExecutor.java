package test.db;

import java.io.File;
import java.io.FilenameFilter;

public class DBDataSetExecutor {
	
	
	public static void main(String[] args) {
		File csvDirPath = new File(args[0]);
		if (!csvDirPath.isDirectory()) {
			System.err.println("CSVファイルが格納されたディレクトリを指定してください。");
			return;
		}
		
		// DBにデータを登録
		execute(csvDirPath);
	}
	
	public static void execute(File csvDir) {
		
		File[] csvFiles = csvDir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith(".csv")) {
					return true;
				}
				return false;
			}
		});
		
		for (File csvFile : csvFiles) {
			
		}
	}
	
}
