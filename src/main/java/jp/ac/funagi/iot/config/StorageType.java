package jp.ac.funagi.iot.config;

/**
 * センサーデータの保存先を表す列挙型。
 * application.properties の app.storage.type で指定する。
 */
public enum StorageType {
	/** リレーショナルデータベースにJDBCで保存する。 */
	JDBC,
	/** CSVファイルに保存する。 */
	CSV
}
