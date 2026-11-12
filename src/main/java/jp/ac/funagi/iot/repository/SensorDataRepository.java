package jp.ac.funagi.iot.repository;

import java.util.List;

import jp.ac.funagi.iot.model.SensorData;

/**
 * センサーデータの保存先に依存しない、保存・取得操作のインターフェース。
 * <p>
 * CSVやDBなどの具体的な保存方式をサービス層から隠蔽する。
 * </p>
 */
public interface SensorDataRepository {

    /**
     * センサーデータを保存する。
     *
     * @param data 保存するデータ
     * @throws IllegalArgumentException データの内容が不正な場合
     * @throws RepositoryException      保存に失敗した場合
     */
    void save(SensorData data);

    /**
     * 測定データを最大limit件取得する。
     *
     * @param limit 取得する最大件数
     * @return センサーデータの一覧
     * @throws IllegalArgumentException limitが不正な場合
     * @throws RepositoryException      取得に失敗した場合
     */
    List<SensorData> findAll(int limit);

    /** Repository内で発生した入出力・データ変換エラーを表す実行時例外。 */
    public static class RepositoryException extends RuntimeException {

        /**
         * @param message エラーの概要
         * @param cause   元となった例外（存在しない場合はnull）
         */
        public RepositoryException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
