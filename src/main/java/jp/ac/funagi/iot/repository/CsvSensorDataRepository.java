package jp.ac.funagi.iot.repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.ac.funagi.iot.model.SensorData;
import jp.ac.funagi.iot.model.SensorType;

/**
 * センサーデータをCSVファイルに保存・取得するRepository実装。
 * <p>
 * 1行を1件のデータとして扱い、項目はカンマ区切りで記録する。
 * </p>
 */
public class CsvSensorDataRepository extends AbstractSensorDataRepository {

	private final Path path;

	/**
	 * 保存対象のCSVファイルを指定する。
	 *
	 * @param path CSVファイルのパス
	 * @throws RepositoryException ファイルの作成に失敗した場合
	 */
	public CsvSensorDataRepository(Path path) {
		this.path = path;
		try {
			Path parent = path.toAbsolutePath().getParent();
			if (parent != null) {
				Files.createDirectories(parent);
			}
			if (Files.notExists(path)) {
				Files.createFile(path);
			}
		} catch (IOException e) {
			throw new RepositoryException("CSVファイルの作成に失敗しました。", e);
		}
	}

	/**
	 * CSVファイルの末尾にセンサーデータを1行追加する。
	 *
	 * @param data 保存するセンサーデータ
	 * @throws RepositoryException ファイルへの書き込みに失敗した場合
	 */
	@Override
	public synchronized void doSave(SensorData data) {

		try {
			String row = String.join(",",
					data.sensorId(),
					data.sensorType().name(),
					String.valueOf(data.value()),
					data.unit(),
					data.measuredAt().toString());

			Files.writeString(path, row + System.lineSeparator(), StandardCharsets.UTF_8,
					StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			throw new RepositoryException("CSVへのセンサーデータ登録に失敗しました。", e);
		}
	}

	/**
	 * CSVファイルの末尾から測定日時の降順で最大limit件を読み込み、SensorDataに復元する。
	 *
	 * @param limit 取得する最大件数
	 * @return 復元したセンサーデータの一覧
	 * @throws RepositoryException ファイル読み込みまたはCSV形式の解釈に失敗した場合
	 */
	@Override
	public synchronized List<SensorData> doFindAll(int limit) {
		try {
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			List<SensorData> dataList = new ArrayList<SensorData>();
			for (String line : lines) {
				if (line.isBlank()) {
					continue;
				}
				// 1行を5項目に分割し、列数を確認してから型変換する。
				String[] cols = line.split(",");
				if (cols.length != 5) {
					throw new RepositoryException("CSVの項目数が不正です。", null);
				}
				dataList.add(new SensorData(
						cols[0],
						SensorType.valueOf(cols[1]),
						Double.parseDouble(cols[2]),
						cols[3],
						LocalDateTime.parse(cols[4])));
			}

			Collections.reverse(dataList);
			dataList.sort((left, right) -> right.measuredAt().compareTo(left.measuredAt()));
			return dataList.stream().limit(limit).toList();
		} catch (IOException | RuntimeException e) {
			throw new RepositoryException("CSVからのセンサーデータ取得に失敗しました。", e);
		}
	}

}
