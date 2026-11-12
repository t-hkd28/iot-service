package jp.ac.funagi.iot.model;

/**
 * センサーが測定する情報の種類。
 * MQTTメッセージおよび保存先では列挙定数名を使用する。
 */
public enum SensorType {
	/** 温度 */
	TEMPERATURE,
	/** 湿度 */
	HUMIDITY,
	/** 照度 */
	ILLUMINANCE
}
