package jp.ac.funagi.iot.repository;

import java.util.List;
import javax.sql.DataSource;
import jp.ac.funagi.iot.model.SensorData;

/** 【訓練生実装】JDBCを使ってOracle Databaseとやり取りする。 */
public class JdbcSensorDataRepository extends AbstractSensorDataRepository {
    
    private final DataSource dataSource;

    public JdbcSensorDataRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void doSave(SensorData data) {
        // TODO: PreparedStatementでSENSOR_DATAへINSERTする。
        // TODO: Connection / PreparedStatementはtry-with-resourcesで確実に閉じる。
        throw new UnsupportedOperationException("TODO: JDBCによるINSERTを実装してください");
    }

    @Override
    protected List<SensorData> doFindAll(int limit) {
        // TODO: 測定日時の降順で最大limit件をSELECTする。
        // TODO: ResultSetの各行をSensorDataに変換してListで返す。
        throw new UnsupportedOperationException("TODO: JDBCによるSELECTを実装してください");
    }
}
