/**
 * センサー情報を取得するAPIを呼び出す。
 *
 * @param {number} limit - 取得するセンサー情報の最大件数
 * @returns {Promise<Object[]>} 取得したセンサー情報の配列
 * @throws {Error} サーバーエラーやデータ形式の不正が発生した場合
 */
export async function getSensors(limit) {
    // TODO 1:
    // fetch()を使って、次のURLにGETリクエストを送信する。
    // /api/sensors?limit=取得件数
    // レスポンスを変数 response に代入する。

    // TODO 2:
    // response.ok が false の場合は、HTTPステータスコードを含む
    // エラーメッセージを持つ Error を throw する。

    // TODO 3:
    // response.json() でレスポンス本文をJSONとして読み込む。

    // TODO 4:
    // 取得したデータが配列でなければ Error を throw する。

    // TODO 5:
    // 取得したセンサーデータの配列を return する。
}
