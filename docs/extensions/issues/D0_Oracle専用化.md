# [D0] Oracle専用化

- **種別：** enhancement / area:infra
- **見積工数：** 7人時（実装・テスト・修正・PR）
- **前提Task：** 現行v1.0完成
- **対応Milestone：** [GitHub運用](../github/準備ブランチ・Milestone・PR運用.md)を参照
- **詳細設計：** [設計書](../design/D0_Oracle専用化.md)
- **テストケース：** D0-01～04（[テスト一覧](../tests/README.md)）

## 目的

現行のCSV/JDBC選択機能を廃止し、Oracle専用で現行機能が動くようにする。CSV Repositoryソースは削除しない。

## 主な作業

RepositoryProducerからCSV選択を外す。CSV設定を削除し、CDI・テスト設定とREADMEを変更。

## 終了条件（Definition of Done）

- [ ] CSVソースは保存したまま、DBでのGET・MQTT保存・画面表示が正常に動く。
- [ ] 対応するテストケースを実施し、結果をPRに記録した。
- [ ] 既存の`GET /api/sensors?limit=1`とOracle利用経路に問題がない。
- [ ] 新機能に必要なAPI・DB・画面まで実際に確認した（該当しない層は除く）。
- [ ] レビューを受け、`master`へマージ可能な状態になった。

## 実装上の注意

- 追加課題用のブランチは`v1.0`タグ付けと設計資料のマージの**後**に最新`master`から作成。
- 新しい未完成機能を前提にしない。現段階だけで動作できること。
- 最小限の入力検証と単純なエラー処理に留める。必要に応じて先行Taskの設計とコードを確認する。
