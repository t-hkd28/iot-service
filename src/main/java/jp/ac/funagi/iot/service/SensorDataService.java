package jp.ac.funagi.iot.service;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jp.ac.funagi.iot.model.SensorData;
import jp.ac.funagi.iot.repository.SensorDataRepository;
import jp.ac.funagi.iot.repository.SensorDataRepository.RepositoryException;

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

    /** TODO 1: JSONをSensorDataに変換し、repository.saveで保存する。 */
    public void register(String json) throws IOException, RepositoryException {
        throw new UnsupportedOperationException("TODO: MQTT JSONの解析と保存を実装してください");
    }

    /** TODO 2: Repositoryからデータを取得して呼び出し元へ返す。 */
    public List<SensorData> findAll(int limit) throws RepositoryException {
        throw new UnsupportedOperationException("TODO: センサーデータ一覧の取得を実装してください");
    }
}
