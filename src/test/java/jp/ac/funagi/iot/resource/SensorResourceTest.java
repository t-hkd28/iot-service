package jp.ac.funagi.iot.resource;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jp.ac.funagi.iot.service.SensorDataService;
import jp.ac.funagi.iot.support.CsvStorageProfile;

/** ResourceはCDIから取得して直接メソッドを呼ぶ。HTTPを送信しない。 */
@QuarkusTest
@TestProfile(CsvStorageProfile.class)
class SensorResourceTest {
	@Inject
	SensorResource resource;
	@InjectMock
	SensorDataService service;

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T06_01_件数1の取得() {
		// TODO（対応するテスト仕様書を参照）：件数1の取得で、サービスから受け取ったデータが返ることを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T06_02_上限1000件() {
		// TODO（対応するテスト仕様書を参照）：上限1000件の指定を正常に処理することを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T06_03_件数0を拒否() {
		// TODO（対応するテスト仕様書を参照）：下限未満を拒否し、サービス処理を行わないことを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T06_04_件数1001を拒否() {
		// TODO（対応するテスト仕様書を参照）：上限超過を拒否し、サービス処理を行わないことを確認する。
	}
}
