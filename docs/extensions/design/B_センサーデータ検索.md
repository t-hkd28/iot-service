# センサーデータ検索の設計（種別・期間・設置場所）

> **作業名の確認：** [タスク早見表](../タスク早見表.md)（D0・B1・A1などの記号と担当機能の対応）

## 開発単位と依存関係

| Task | 条件 | 前提 | そのTaskだけで完成する機能 |
|---|---|---|---|
| センサー種別検索（B1） | `sensorType` | Oracle専用化（D0） | 種別条件によるOracle検索、Web一覧 |
| 測定日時範囲検索（B2） | `from`、`to` | センサー種別検索（B1） | 期間条件の検索、種別とのAND、Web一覧 |
| 設置場所検索（B3） | `locationId` | 測定日時範囲検索（B2）、デバイスとセンサーの関連付け（C5） | 現在の設置場所条件、種別・期間とのAND、Web一覧 |

## REST API仕様

既存の`GET /api/sensors`を拡張する。指定可能な条件は段階的に増やし、未指定の条件は絞り込まない。

| クエリ | 例 | 概要 |
|---|---|---|
| `limit` | `100` | 最大件数。既定100、1～1000 |
| `sensorType` | `TEMPERATURE` | センサー種別検索（B1）。センサー種別の一致 |
| `from` | `2026-11-01T00:00:00` | 測定日時範囲検索（B2）。測定日時が指定時刻以上 |
| `to` | `2026-12-01T00:00:00` | 測定日時範囲検索（B2）。測定日時が指定時刻未満 |
| `locationId` | `1` | 設置場所検索（B3）。現時点の設置場所IDに一致 |

- すべて任意。開始のみ・終了のみも可。両方指定した場合`from < to`。不正な日時・範囲はHTTP 400とする。
- AND検索。**絞り込み→測定日時DESC・ID DESCで並べ替え→`limit`制限**の順序。
- 条件未指定なら従来と同じ`SensorData`配列（0件は`[]`）。
- 公開APIに`sensorId`パラメータを追加しない。内部モデルには`sensorId`を保持する。
- 空入力は送信しない。場所未登録のセンサーデータは、場所条件指定時は検索結果に含まない。

### リクエスト例

```http
GET /api/sensors?limit=100&sensorType=TEMPERATURE
GET /api/sensors?limit=100&from=2026-11-01T00%3A00%3A00&to=2026-12-01T00%3A00%3A00
GET /api/sensors?limit=100&sensorType=TEMPERATURE&from=2026-11-01T00%3A00%3A00&locationId=1
```

## SQL設計（考え方）

センサー種別検索（B1）・測定日時範囲検索（B2）では`SENSOR_DATA`だけを使う。条件未指定時に動作する既存のSELECT処理との互換性を維持する。設置場所検索（B3）で場所のJOINを追加する。

```sql
-- B3以降。指定された条件に応じてWHERE句を構築する想定。
SELECT SENSOR_ID, SENSOR_TYPE, SENSOR_VALUE, UNIT, MEASURED_AT
FROM (
    SELECT sd.ID, sd.SENSOR_ID, sd.SENSOR_TYPE, sd.SENSOR_VALUE, sd.UNIT, sd.MEASURED_AT
    FROM SENSOR_DATA sd
    WHERE 1 = 1
      -- sensorType指定時: AND sd.SENSOR_TYPE = ?
      -- from指定時:       AND sd.MEASURED_AT >= ?
      -- to指定時:         AND sd.MEASURED_AT < ?
      -- locationId指定時は次のEXISTSを追加する:
      -- AND EXISTS (
      --     SELECT 1 FROM DEVICE_SENSOR ds
      --     JOIN SENSOR_DEVICE d ON d.DEVICE_ID = ds.DEVICE_ID
      --     WHERE ds.SENSOR_ID = sd.SENSOR_ID
      --       AND d.LOCATION_ID = ?
      -- )
    ORDER BY sd.MEASURED_AT DESC, sd.ID DESC
)
WHERE ROWNUM <= ?
```

上記は**組み立て方の説明例**であり、そのまま実行する完成SQLではない。実装時は指定された条件だけをWHERE句へ追加し、値は`PreparedStatement`の`?`に設定する。ユーザー入力をSQL文字列へ直接連結しない。`EXISTS`を使用するのは、対応付けによってデータ行が重複するのを避けるためである。

## 変更・新規ファイル

| ファイル | 内容 |
|---|---|
| `model/SensorDataFilter.java`（新規） | 種別・期間・設置場所・件数を保持。センサー種別検索（B1）時点は必要な項目のみ、順次追加 |
| `resource/SensorResource.java` | Optionalなクエリパラメータを受け取りFilterへ反映 |
| `service/SensorDataService.java` | `search(filter)`追加。既存`findAll(limit)`は互換性のため維持 |
| `repository/SensorDataRepository.java` | `search(filter)`追加。既存`findAll(limit)`は維持 |
| `repository/JdbcSensorDataRepository.java` | 任意条件・順序・取得件数を扱うOracle SQL |
| `js/sensor-api.js` | `URLSearchParams`等で検索パラメータを作成 |
| `index.html`、`js/script.js` | センサー種別検索（B1）は種別欄、測定日時範囲検索（B2）は日時入力、設置場所検索（B3）は場所選択欄を追加 |
| 設置場所マスタ・デバイス登録／一覧（C1）で追加する場所一覧API | 設置場所検索（B3）では既存`GET /api/locations`を使い選択肢を表示 |

## 設置場所検索（B3）の場所の解釈

- 場所はセンサーデータの記録時に保存されていない。**現在のデバイス所属場所**で検索する。
- 同じセンサーが別の場所へ移った場合、過去データも新しい所属場所で検索される。この仕様を画面・説明に明記する。
- `DEVICE_SENSOR`に紐づかないデータは場所指定検索から除外する。場所未指定なら除外しない。

## 完了条件

- センサー種別検索（B1）：種別指定・省略時の一覧とHTTP APIの動作が完成。
- 測定日時範囲検索（B2）：開始のみ／終了のみ／両方／種別とのAND、日時逆転のエラーが完成。
- 設置場所検索（B3）：場所単独、場所＋種別、場所＋期間、全条件AND、場所未指定時の回帰試験が完成。
- 各段階でJUnit／Mockito、実Oracleとブラウザ動作確認。複雑な日付・タイムゾーン変換は扱わない。
