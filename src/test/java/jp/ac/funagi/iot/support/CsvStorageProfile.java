package jp.ac.funagi.iot.support;

import java.util.Map;

import io.quarkus.test.junit.QuarkusTestProfile;

/** CDIを使う試験の共通設定。実際のOracle・Brokerには接続しない。 */
public class CsvStorageProfile implements QuarkusTestProfile {
	@Override
	public Map<String, String> getConfigOverrides() {
		return Map.of(
				"app.storage.type", "CSV",
				"app.csv.path", "target/test-cdi-sensor-data.csv",
				"app.mqtt.enabled", "false",
				"quarkus.datasource.devservices.enabled", "false");
	}
}
