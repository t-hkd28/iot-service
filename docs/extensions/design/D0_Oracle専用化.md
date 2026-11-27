# Oracle専用化：設計

## 完成後の動作

MQTTで受信したセンサーデータをOracle DBに保存し、REST APIとWeb画面から取得できます。CSVとOracleを選択する設定は廃止します。

## 対象

- `RepositoryProducer`は`JdbcSensorDataRepository`を提供する。
- `CsvSensorDataRepository`のJavaソースは**削除しない**。ただし実行時には選択しない。
- CSV保存先設定と保存方式切替設定を使わない構成にする。
- テスト用設定とProducerのJUnitテストをOracle専用の構成に合わせて見直す。
- 既存の`SensorDataRepository`インターフェースは維持する。

## 動作条件

Oracle接続、MQTT受信→保存、`GET /api/sensors`、Web一覧表示が動作すること。
