# IoTセンサー情報管理システム - 訓練生用ベースプロジェクト

## このプロジェクトについて
これは完成品ではありません。チームで分担して、MQTT受信、非同期処理、データ保存、REST API、Web画面を完成させるための**開発途中の土台**です。TODOコメントのある箇所を実装してください。

## 技術構成
- JDK 21 / Maven / Quarkus 3
- Eclipse Paho MQTT Client
- Oracle Database（JDBC）またはCSVファイル
- REST API（Quarkus REST / JSON）
- HTML / CSS / JavaScript

## 開発開始（GitHubからクローン）
1. 指導員から共有されたGitHubリポジトリのURLを確認する。
2. 作業用フォルダーで次のコマンドを実行する。
   ```bash
   git clone https://github.com/t-hkd28/iot-service.git
   ```
3. 作成されたプロジェクトフォルダーへ移動する。
   ```bash
   cd iot-service
   ```
4. Eclipseで「既存のMavenプロジェクト」としてインポートする。
5. 指導員の指示に従って担当ブランチを作成する。`main`へ直接コミット・pushしない。

※訓練生が新しいGitHubリポジトリを作成したり、`git init`や初回pushを行ったりする必要はありません。リポジトリへのアクセス権がない場合は指導員に確認してください。

## 起動前に必要な準備
1. JDK 21とMavenを利用できる状態にする。
2. Eclipseに既存Mavenプロジェクトとしてインポートする。
3. MQTTブローカーを利用できるようにする（MQTT機能の実装・確認時）。
4. `application.properties`の環境変数を実習環境に合わせて設定する。
5. JDBC方式を使う場合はOracle Databaseを利用できるようにし、`db/schema-oracle.sql`を実行する。

## 設定する環境変数
| 変数 | 内容 | 例（`00`の部分は他メンバーと重複しないよう変更する） |
|---|---|---|
| `DB_USERNAME` | DBユーザー | `iot00` |
| `DB_PASSWORD` | DBパスワード | `iot00` |
| `DB_JDBC_URL` | JDBC URL | `jdbc:oracle:thin:@//192.168.11.6:1521/freepdb1` |
| `MQTT_BROKER_URL` | MQTTブローカー | `tcp://192.168.11.6:1883` |
| `MQTT_CLIENT_ID` | クライアントID | `iot-service00` |
| `MQTT_TOPIC` | 購読トピック | `iot/sensor/00` |
| `APP_STORAGE_TYPE` | 保存方式 | `JDBC` または `CSV` |

## 開発する機能
1. MQTT受信：ブローカーに接続してトピックを購読する。
2. 非同期処理：受信コールバックと保存処理を分離し、Executorで保存タスクを実行する。
3. サービス：JSONをSensorDataに変換し、Repositoryへ保存・取得を依頼する。
4. Repository：入力検証、JDBC版の保存と取得、保存方式切り替えを実装する。共通インターフェース、CSV版の保存と取得は実装済み。
5. REST API：`GET /api/sensors?limit=100`を実装する。
6. Web画面：APIを呼び出してセンサーデータを表形式で表示する。

## 推奨実装順
- 第1段階：クラス間のインターフェースとJSON形式を確認する。
- 第2段階：完成済みCSV Repositoryの仕様・動作を確認する。
- 第3段階：ServiceとREST APIを実装し、ブラウザやPostmanでJSONを確認する。
- 第4段階：MQTT受信とExecutorによる非同期保存を実装する。
- 第5段階：Web画面を実装してREST APIと接続する。
- 第6段階：JDBC Repositoryを実装し、Oracle方式へ切り替えて同じ機能が動くことを確認する。

## CSV方式での動作確認
JDBC Repositoryが未完成でも、`APP_STORAGE_TYPE`の値に関わらずCSV Repositoryを利用できます。CSVファイルがまだ存在しない場合、一覧取得は空のJSON配列に相当する空リストを返し、最初の保存時にファイルが作成されます。

CSV Repository単体の確認では、`CsvSensorDataRepository`を生成し、`save()`でデータを保存した後、`findAll(limit)`で取得します。アプリケーション全体での確認には、訓練生が実装するService・REST API等も必要です。

## 実行・確認

### コマンドによる実行
```dos
rem 環境変数の設定
set DB_USERNAME=iot00
set DB_PASSWORD=iot00
         :

rem Quarkusプロジェクトを開発モードで実行
quarkus dev（または mvn quarkus:dev）
```

### Eclipseによる実行
1. `pom.xml`を右クリック - 実行 - Maven ビルド
2. 「メイン」タブの名前に任意の値、ゴールに`quarkus:dev`を入力
3. 「環境」タブで追加ボタンをクリックし、必要な環境変数を追加
4. 実行ボタンをクリック

※以降は 1. の操作時に 2. で入力した名前を選択すれば、実行可能です。

### REST APIの確認
Postmanで`http://localhost:8080/api/sensors?limit=20`等にGETリクエストを送信する。

## MQTTメッセージ例
```json
{
  "sensorId": "sensor-001",
  "sensorType": "TEMPERATURE",
  "value": 25.6,
  "unit": "℃",
  "measuredAt": "2026-11-12T08:30:00"
}
```
`measuredAt`はISO形式の日時文字列です。

## GitHubでのチーム開発ルール
- `main`へ直接pushしない。作業前に最新の`main`を取り込み、担当ブランチを作成する。
- 1つのPull Request（PR）で扱う変更を小さくする。
- PRには変更目的・変更内容・動作確認結果・未対応事項を記載する。
- 指導員またはチームでレビューを受けてからマージする。
- マージ後は各自の作業ブランチへ最新の`main`を取り込む。
- `target/`、実行ログ、CSV実データ、個人の接続情報はコミットしない。
