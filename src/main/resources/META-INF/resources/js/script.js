import * as SensorApi from './sensor-api.js';

// HTML要素を取得する。
const form = document.querySelector('#search-form');
const limitInput = document.querySelector('#limit');
const message = document.querySelector('#message');
const count = document.querySelector('#count');
const sensorList = document.querySelector('#sensor-list');

// APIからセンサーデータを取得し、画面に表示する。
async function loadSensors() {
    const limit = Number(limitInput.value);

    // TODO 1:
    // 取得件数が1～1000の整数であることを確認する。
    // 条件を満たさない場合はエラーメッセージを表示し、処理を終了する。

    // TODO 2:
    // データ取得中であることをメッセージに表示する。
    // 取得ボタンを一時的に無効にする。

    try {
        // TODO 3:
        // SensorApi.getSensors()を呼び出してデータを取得する。
        // 取得したデータを displaySensors() に渡す。
        // 正常に取得できたことをメッセージに表示する。

    } catch (error) {
        // TODO 4:
        // 取得件数を0にし、表の内容を空にする。
        // エラー内容を画面に表示する。

    } finally {
        // TODO 5:
        // 取得ボタンを有効に戻す。
    }
}

// 取得したデータを表に表示する。
function displaySensors(sensors) {
    // TODO 6:
    // 取得件数を画面に表示する。
    // 表の既存の行を削除する。
    // データが0件の場合は「データはありません。」と表示する。
    // それ以外の場合は、各センサー情報を表の行として追加する。
}

// 表の行を作成して追加する。
function addCellRow(values, columnCount = values.length) {
    // TODO 7:
    // tr要素を作成する。
    // valuesの各値についてtd要素を作成し、セルに値を設定する。
    // セルへの値の設定には textContent を使う。
    // valuesが1件だけの場合は、そのセルのcolSpanをcolumnCountにする。
    // 作成した行を sensorList に追加する。
}

// 画面上部のメッセージを表示する。
function showMessage(text, isError) {
    // TODO 8:
    // messageのtextContentにtextを設定する。
    // isErrorがtrueならclassNameを'error'、
    // falseなら'success'にする。
}

// フォーム送信時にページ遷移せず、データを再取得する。
form.addEventListener('submit', (event) => {
    event.preventDefault();
    loadSensors();
});

// 画面を開いたときに初回取得する。
loadSensors();
