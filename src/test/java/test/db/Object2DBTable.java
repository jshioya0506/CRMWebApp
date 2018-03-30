package test.db;

import java.util.List;
import java.util.Map;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;

/**
 * データオブジェクトからテーブルへデータ登録
 * @author jshioya
 *
 */
public class Object2DBTable {

	/**
	 * テーブルにデータを登録する
	 * @param tableName テーブル名
	 * @param dataMaps　データマップ
	 */
	public void registerdTable(
			String tableName, 
			List<Map<String,Object>> dataMaps) {
		
		// DBの設定情報を取得
		ServerRuntime cayenneRuntime = new ServerRuntime("cayenne-NexusCRM.xml");
		ObjectContext context = cayenneRuntime.getContext();
		
		// テーブルオブジェクトにデータを設定し、テーブルにデータを登録
		for (Map<String,Object> dataMap : dataMaps) {
			// データ登録対象のテーブルオブジェクトを取得
			CayenneDataObject dataObj 
				= CayenneDataObjectUtil.findDataObject(context, tableName);
			
			// テーブルオブジェクトにデータを設定
			for (Map.Entry<String,Object> entry : dataMap.entrySet()) {
				dataObj.writeProperty(entry.getKey(), entry.getValue());
			}
			
			// データ登録
			context.commitChanges();
		}
	}
}
