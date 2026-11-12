package jp.ac.funagi.iot.repository;

import java.util.List;

import jp.ac.funagi.iot.model.SensorData;

/**
 * 【訓練生実装】JDBC版・CSV版で共通する入力検証をまとめる抽象クラス。
 * 継承とTemplate Methodパターンを使い、保存先固有の処理はサブクラスに任せる。
 */
public abstract class AbstractSensorDataRepository implements SensorDataRepository {

    @Override
    public final void save(SensorData data) {
        // TODO 1: sensorId、sensorType、unit、measuredAtが空でないか検証する。
        doSave(data);
    }

    @Override
    public final List<SensorData> findAll(int limit) {
        // TODO 2: limitが1～1000の範囲か検証する。
        return List.copyOf(doFindAll(limit));
    }

    protected abstract void doSave(SensorData data);

    protected abstract List<SensorData> doFindAll(int limit);
}
