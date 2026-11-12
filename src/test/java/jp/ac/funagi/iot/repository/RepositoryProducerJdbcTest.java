package jp.ac.funagi.iot.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jp.ac.funagi.iot.support.JdbcStorageProfile;

/** JDBC設定でQuarkusを起動し、実際のDB接続はせずにBeanの選択を確認する。 */
@QuarkusTest
@TestProfile(JdbcStorageProfile.class)
class RepositoryProducerJdbcTest {
	@Inject
	SensorDataRepository repository;

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T05_02_JDBC方式を注入() {
		// TODO（対応するテスト仕様書を参照）：設定に従ってJDBC用の部品が注入されることを確認する。
	}
}
