package jp.ac.funagi.iot.model;

import java.time.LocalDateTime;

/**
 * MQTTで受信し、アプリケーション内で扱う1件分のセンサーデータ。
 *
 * @param sensorId   センサーを識別するID
 * @param sensorType センサーの種類
 * @param value      測定値
 * @param unit       測定値の単位
 * @param measuredAt 測定日時
 */
public record SensorData(
                String sensorId,
                SensorType sensorType,
                double value,
                String unit,
                LocalDateTime measuredAt) {
}
