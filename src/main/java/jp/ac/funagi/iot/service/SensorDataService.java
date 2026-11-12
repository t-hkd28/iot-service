package jp.ac.funagi.iot.service;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jp.ac.funagi.iot.model.SensorData;
import jp.ac.funagi.iot.repository.SensorDataRepository;

/** 【訓練生実装】MQTT JSONの変換とRepository呼び出しを担当する。 */
@ApplicationScoped
public class SensorDataService {

    private final SensorDataRepository repository;
    private final ObjectMapper objectMapper;

    @Inject
    public SensorDataService(SensorDataRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    /** TODO 1: JSONをSensorDataに変換し、Repositoryへ保存する。 */
    public void register(String json) {
        throw new UnsupportedOperationException("TODO: JSONの解析と保存を実装してください");
    }

    /** TODO 2: Repositoryからデータを取得して呼び出し元へ返す。 */
    public List<SensorData> findAll(int limit) {
        throw new UnsupportedOperationException("TODO: センサーデータ一覧の取得を実装してください");
    }
}
