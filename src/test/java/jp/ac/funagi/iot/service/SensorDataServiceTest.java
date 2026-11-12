package jp.ac.funagi.iot.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jp.ac.funagi.iot.repository.SensorDataRepository;
import jp.ac.funagi.iot.support.CsvStorageProfile;

/** ServiceをCDIから取得し、保存先だけをMockitoで置き換える。 */
@QuarkusTest
@TestProfile(CsvStorageProfile.class)
class SensorDataServiceTest {
	@Inject
	SensorDataService service;
	@InjectMock
	SensorDataRepository repository;

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T04_01_正常JSON登録() {
		// TODO（対応するテスト仕様書を参照）：正しいJSONが1件のデータに変換され、保存へ渡されることを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T04_02_不正JSON() {
		// TODO（対応するテスト仕様書を参照）：不正なJSONが保存されず、解析失敗を通知することを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T04_03_一覧の委譲() {
		// TODO（対応するテスト仕様書を参照）：指定件数で保存先へ問い合わせ、取得した一覧が返ることを確認する。
	}
}
