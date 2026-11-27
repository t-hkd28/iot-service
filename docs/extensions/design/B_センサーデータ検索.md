# センサーデータ検索：設計

## 追加する機能

既存の`GET /api/sensors`に検索条件を追加します。いずれの条件も**省略可能**で、複数指定したときは**AND条件**で絞り込みます。条件がなければ従来の一覧と同じです。

| 課題 | 追加する検索条件 | 画面でできること |
|---|---|---|
| センサー種別検索（B1） | `sensorType` | 温度・湿度・照度を選んで検索 |
| 測定日時範囲検索（B2） | `from`・`to` | 開始・終了日時を指定して検索 |
| 設置場所検索（B3） | `locationId` | 設置場所を選んで検索 |

## API・検索仕様

| パラメータ | 値・判定 |
|---|---|
| `limit` | 従来どおり省略時100、1～1000 |
| `sensorType` | `TEMPERATURE` / `HUMIDITY` / `ILLUMINANCE` |
| `from` | `yyyy-MM-ddTHH:mm:ss`、測定日時が開始以上 |
| `to` | 同形式、測定日時が終了未満 |
| `locationId` | `SENSOR_LOCATION.LOCATION_ID`に対応する番号 |

- `from`だけ、`to`だけでも検索できる。両方指定するときは`from`が`to`より前であること。
- 条件で絞り込み、**測定日時の降順・登録IDの降順**に並べた後、`limit`件に制限する。
- 戻り値は従来の`SensorData`のJSON配列。0件なら空配列。
- 不正な条件はHTTP 400で通知する。利用者向けの**センサーID検索欄は作らない**。

## 設置場所検索だけの取り決め

- 場所の選択肢は`GET /api/locations`から取得する。
- データの所属場所は、センサーが割り当てられたデバイスの**現在の設置場所**で判断する。移設履歴は扱わない。
- デバイスとの対応が未登録のセンサーデータは、**場所を指定した場合のみ除外**する。場所を指定しなければ取得対象に含める。
- センサーIDは`SENSOR_ASSIGNMENT`を通して`IOT_DEVICE`へ対応付ける。各センサーIDの割当先は最大1台。

## 変更する主なソース

| 層 | 変更対象・役割 |
|---|---|
| Resource | `SensorResource`：検索条件を受け取る |
| Service | `SensorDataService`：検索処理をRepositoryへ渡す |
| Repository | `SensorDataRepository`・`JdbcSensorDataRepository`：検索条件に応じて取得する |
| Model | `SensorDataFilter`（追加案）：検索条件を保持する |
| Web | `sensor-api.js`・`script.js`・`index.html`：条件指定と検索結果の表示 |

既存の件数指定だけの取得処理は利用できる状態を維持する。**SQLの構造とパラメータ設定方法は実装担当者が検討する。**
