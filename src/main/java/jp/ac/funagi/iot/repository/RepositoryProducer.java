package jp.ac.funagi.iot.repository;

import java.nio.file.Path;
import javax.sql.DataSource;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jp.ac.funagi.iot.config.StorageType;

/** 【訓練生実装】設定値に応じてRepository実装を1つ生成する。 */
@ApplicationScoped
public class RepositoryProducer {

    @ConfigProperty(name = "app.storage.type")
    StorageType storageType;

    @ConfigProperty(name = "app.csv.path")
    String csvPath;

    @Inject
    DataSource dataSource;

    @Produces
    @ApplicationScoped
    public SensorDataRepository createRepository() {
        // TODO: storageTypeがJDBCならJdbcSensorDataRepository、CSVならCsvSensorDataRepositoryを返す。
        // switch式を使うとStorageTypeごとの分岐を整理できる。
        return new CsvSensorDataRepository(Path.of(csvPath));
    }
}
