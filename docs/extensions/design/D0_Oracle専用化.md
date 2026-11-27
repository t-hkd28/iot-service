# D0：Oracle専用化（CSVコードは保持）

## 目的

現行アプリのCSV/JDBC切替を廃止し、Oracle専用で動作させる。**`CsvSensorDataRepository.java`そのものは削除しない**。現行v1.0のタグを取得してから着手する。

## 主な変更

| ファイル | 変更内容 |
|---|---|
| `repository/RepositoryProducer.java` | `StorageType`とCSVパスの設定注入を外し、`JdbcSensorDataRepository`だけを`@Produces`で提供する。`Instance<DataSource>`または`DataSource`を注入する既存方法を尊重。 |
| `config/StorageType.java` | 参照がなくなれば削除可能（残す場合も未使用を明記）。 |
| `repository/CsvSensorDataRepository.java` | **削除・機能変更しない**。旧版確認用としてコードを残す。 |
| `application.properties` | `app.storage.type`と`app.csv.path`を廃止。OracleのDataSource設定は維持。 |
| `docs`、`README` | 追加版の稼働条件を「Oracle必須」に更新（現行文書の確定版は後で改訂）。 |
| `src/test/...` | CSV切替を前提としたProducerテストをJDBC注入の検証へ変更。実DB接続不要なテスト用設定を準備。 |

## 実装上のポイント

- インターフェース`SensorDataRepository`を維持する。ServiceやResourceをJDBC直接呼び出しにしない。
- DB側には既存の`SENSOR_DATA`テーブルを使用。D0でテーブル変更しない。
- `app.mqtt.enabled=false`で起動し、OracleからのREST取得と画面一覧表示を確認。その後MQTTを有効にして保存まで確認。

## 完成条件

1. CSV選択用の設定値が不要である。
2. CDIを通じて`SensorDataRepository`にJDBC実装が注入される。
3. 既存の`GET /api/sensors?limit=1`でOracleのデータが取得できる。
4. MQTT受信からOracleへの1件保存、ブラウザでの表示ができる。
5. 既存の`CsvSensorDataRepository.java`はソースに残り、実行経路からは除外されている。

## テスト

`D0-01` CDIから注入されたRepositoryがJDBC版／`D0-02` CSV設定なしで起動／`D0-03` OracleでGETと一覧表示／`D0-04` MQTT→Oracle→画面の一連の動作。回帰試験として既存のTest_01～10の該当範囲を実施。
