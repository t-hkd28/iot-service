package jp.ac.funagi.iot.support;

import java.util.Map;

import io.quarkus.test.junit.QuarkusTestProfile;

/** JDBC選択のCDI試験専用。DataSource取得だけ確認し、DBに接続しない。 */
public class JdbcStorageProfile implements QuarkusTestProfile {
	@Override
	public Map<String, String> getConfigOverrides() {
		return Map.of(
				"app.storage.type", "JDBC",
				"app.mqtt.enabled", "false",
				"quarkus.datasource.devservices.enabled", "false");
	}
}
