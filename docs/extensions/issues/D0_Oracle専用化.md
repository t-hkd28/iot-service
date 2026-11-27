# Oracle専用化（D0）

**前提：** 現行版完成  
**設計：** [機能仕様](../design/D0_Oracle専用化.md) ／ **テスト：** [D0-01～03](../tests/README.md)

## 目的

Oracleを唯一の保存先として使えるようにする。CSV用ソースは残す。

## 作業

RepositoryProducerの選択処理、保存先設定、テスト設定・注入処理を整理する。

## 完成条件

MQTT→Oracle保存、REST→Web表示、CSV設定なしでの起動を確認する。

対応するテストを実施し、結果をPRに記録してレビューを受けること。
