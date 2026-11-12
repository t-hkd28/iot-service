package jp.ac.funagi.iot.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import jp.ac.funagi.iot.model.SensorData;
import jp.ac.funagi.iot.model.SensorType;

/** Test_01：共通処理はCDIの外でテストする。 */
class AbstractSensorDataRepositoryTest {
	private final LocalDateTime time = LocalDateTime.parse("2026-11-20T10:00:00");

	private SensorData sample() {
		return new SensorData("TRAIN-01", SensorType.TEMPERATURE, 23.5, "C", time);
	}

	private static class FakeRepository extends AbstractSensorDataRepository {
		SensorData saved;
		int saveCount, findCount, lastLimit;
		List<SensorData> result = new ArrayList<>();

		@Override
		protected void doSave(SensorData data) {
			saved = data;
			saveCount++;
		}

		@Override
		protected List<SensorData> doFindAll(int limit) {
			lastLimit = limit;
			findCount++;
			return result;
		}
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T01_01_正常データの保存() {
		// TODO（対応するテスト仕様書を参照）：正常なデータを保存したとき、保存先に同じデータが1回渡ることを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T01_02_データ未指定() {
		// TODO（対応するテスト仕様書を参照）：データそのものが未指定の場合、保存処理を受け付けないことを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T01_03_識別子が不正() {
		// TODO（対応するテスト仕様書を参照）：識別子の未設定・空文字・空白のみをそれぞれ拒否することを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T01_04_種別が未指定() {
		// TODO（対応するテスト仕様書を参照）：センサー種別が未指定のデータを拒否することを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T01_05_単位が不正() {
		// TODO（対応するテスト仕様書を参照）：単位の未設定・空文字・空白のみをそれぞれ拒否することを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T01_06_測定日時が未指定() {
		// TODO（対応するテスト仕様書を参照）：測定日時がないデータを拒否することを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T01_07_取得件数の境界値() {
		// TODO（対応するテスト仕様書を参照）：最小値と最大値の取得要求が正常に扱われることを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T01_08_取得件数が不正() {
		// TODO（対応するテスト仕様書を参照）：下限未満と上限超過の件数指定を拒否することを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T01_09_一覧を変更できない() {
		// TODO（対応するテスト仕様書を参照）：取得した一覧の変更を防ぎ、取得元の変更の影響も受けないことを確認する。
	}
}
