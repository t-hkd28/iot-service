package jp.ac.funagi.iot.repository;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/** Test_02/03：DB接続の振る舞いをMockitoで代替する。 */
class JdbcSensorDataRepositoryTest {
	private DataSource source;
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet rows;
	private JdbcSensorDataRepository repository;

	@BeforeEach
	void prepare() throws SQLException {
		source = mock(DataSource.class);
		connection = mock(Connection.class);
		statement = mock(PreparedStatement.class);
		rows = mock(ResultSet.class);
		when(source.getConnection()).thenReturn(connection);
		when(connection.prepareStatement(anyString())).thenReturn(statement);
		repository = new JdbcSensorDataRepository(source);
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T02_01_保存用SQLと値() {
		// TODO（対応するテスト仕様書を参照）：1件保存し、テーブル名・項目の順序・値の型・実行回数を確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T02_02_保存後に資源解放() {
		// TODO（対応するテスト仕様書を参照）：データ登録後に接続・SQL操作用の資源が解放されることを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T02_03_接続失敗の例外変換() {
		// TODO（対応するテスト仕様書を参照）：DB接続失敗を再現し、原因情報を失わずに例外が通知されることを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T03_01_検索の並びと件数() {
		// TODO（対応するテスト仕様書を参照）：新旧2件の検索結果を再現し、値・並び順と件数指定を確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T03_02_検索結果ゼロ件() {
		// TODO（対応するテスト仕様書を参照）：検索結果がない場合に空の一覧となることを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T03_03_検索時のDB失敗() {
		// TODO（対応するテスト仕様書を参照）：SQL準備中のDB障害を再現し、適切な例外となることを確認する。
	}

	@Test
	@Disabled("未実装：対応テストケースを完成後に解除")
	void T03_04_検索後に資源解放() {
		// TODO（対応するテスト仕様書を参照）：検索終了後に結果・SQL・接続の資源が解放されることを確認する。
	}
}
