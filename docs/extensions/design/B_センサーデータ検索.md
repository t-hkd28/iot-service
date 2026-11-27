# B1～B4：センサーデータ検索（外部・内部設計）

## API仕様

既存の`GET /api/sensors`を拡張する。従来の`limit`以外はすべて任意。条件未指定では従来の一覧取得。

| クエリ | 型・例 | B段階 | 意味 |
|---|---|---|---|
| `limit` | 整数、既定100、1～1000 | 既存 | 最大取得件数 |
| `sensorType` | `TEMPERATURE`等 | B1 | 測定種別が一致 |
| `sensorId` | 文字列（完全一致） | B2 | センサーIDが一致 |
| `from` | `2026-11-01T00:00:00` | B3 | 測定日時が指定時刻以降 |
| `to` | `2026-12-01T00:00:00` | B3 | 測定日時が指定時刻より前 |

**条件の組合せ**：指定された条件はAND。`from`・`to`の片方のみも可。両方ある場合は`from < to`を要求する。空文字は未指定扱い。`sensorId`は既存の`SENSOR_DATA.SENSOR_ID`に対応し、`deviceId`とは別。

**段階ごとの契約**：
- B1：`sensorType`のみ。従来の`limit`との併用。
- B2：`sensorId`追加。`sensorType`とIDのAND検索もB2で完成。
- B3：`from`・`to`追加。単独の日時検索を完成。既存の2条件は引き続き利用可。
- B4：種別・ID・日時のすべてを任意に組み合わせるAND検索を完成。

例：`/api/sensors?limit=50&sensorType=TEMPERATURE&sensorId=S-001&from=2026-11-01T00%3A00%3A00&to=2026-12-01T00%3A00%3A00`

**結果**：既存`SensorData`のJSON配列を返す（形式は不変）。一致0件は`[]`。条件をすべて適用して**測定日時の降順・ID降順**に並べた後、`limit`件で切る。

## 画面

既存の検索フォームに`sensorType`選択、`sensorId`入力、`from`と`to`の日時入力を段階的に追加。検索ボタンを押すと一覧表と件数を更新。条件未指定の画面動作は現行と同じ。空欄はクエリに送らない。エラー時のメッセージは既存UIを使う。

## 内部設計・ソース対応

| ソース | 変更案 |
|---|---|
| `model/SensorDataFilter.java`（新規） | `sensorType`、`sensorId`、`from`、`to`、`limit`をまとめるrecord。B1時点は必要最小限の項目で開始し、各段階で追加。 |
| `resource/SensorResource.java` | クエリパラメータの受取・単純な形式検証・Service呼出し。 |
| `service/SensorDataService.java` | `search(filter)`を追加。既存`findAll(int)`を残し、旧APIの互換性を保つ。 |
| `repository/SensorDataRepository.java` | `search(filter)`を追加。`findAll(int)`を残す。 |
| `repository/JdbcSensorDataRepository.java` | `PreparedStatement`で条件付きSQLを実行。 |
| `js/sensor-api.js` | `getSensors`を条件対応に拡張。`URLSearchParams`等でエンコードする。 |
| `index.html`、`js/script.js` | 検索項目・入力検証・一覧更新。 |

## 完成ゲート

- B1：種別単独／未指定の一覧表示が使える。
- B2：ID単独と種別＋IDが使える。
- B3：期間単独と開始のみ・終了のみが使える。
- B4：種別＋ID＋期間の4項目すべてをANDで検索できる。
- 各段階ともJUnit（Resource／Service／Repository）と実Oracle・ブラウザで確認。

## 除外

部分一致・複数選択・ページネーション・複雑な日時タイムゾーン換算・高度な検索UIは扱わない。
