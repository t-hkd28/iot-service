package jp.ac.funagi.iot.mqtt;

import java.util.concurrent.ExecutorService;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import jp.ac.funagi.iot.service.SensorDataService;

/** 【訓練生実装】MQTT受信と非同期保存処理への引き渡しを担当する。 */
public class MqttReceiver {

    private final String brokerUrl;
    private final String topic;
    private final String clientId;
    private final int qos;
    private final SensorDataService service;
    private MqttClient client;
    private ExecutorService executor;

    public MqttReceiver(String brokerUrl, String topic, String clientId, int qos, SensorDataService service) {
        this.brokerUrl = brokerUrl;
        this.topic = topic;
        this.clientId = clientId;
        this.qos = qos;
        this.service = service;
    }

    public void start() throws MqttException {
        // TODO 1: newSingleThreadExecutorで保存処理用Executorを用意する。
        // TODO 2: MqttClientを生成し、ブローカーへ接続する。
        // TODO 3: topicをsubscribeし、受信コールバックを登録する。
        // TODO 4: コールバック内ではJSON文字列化とexecutor.submitだけを行う。
        // TODO 5: submitされたタスクからservice.register(json)を呼び出す。
        throw new UnsupportedOperationException("TODO: MQTT接続・購読・非同期保存を実装してください");
    }

    public void stop() throws MqttException {
        // TODO: 接続中ならdisconnect、clientをclose、executorをshutdownする。
        throw new UnsupportedOperationException("TODO: MQTTとExecutorの終了処理を実装してください");
    }
}
