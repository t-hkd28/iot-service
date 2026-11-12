package jp.ac.funagi.iot.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jp.ac.funagi.iot.support.CsvStorageProfile;

/** CSV設定でQuarkusを起動し、CDIから実際のRepositoryを受け取る。 */
@QuarkusTest
@TestProfile(CsvStorageProfile.class)
class RepositoryProducerTest {
	@Inject
	SensorDataRepository repository;
	@Inject
	RepositoryProducer producer;

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T05_01_CSV方式を注入() {
		// TODO（対応するテスト仕様書を参照）：設定に従ってCSV用の部品が注入され、保存先ファイルが用意されることを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T05_03_DB接続なしでCSVを利用() {
		// TODO（対応するテスト仕様書を参照）：Oracleへの接続を行わずにCSV用の部品を使用できることを確認する。
	}
}
