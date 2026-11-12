package jp.ac.funagi.iot.runtime;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jp.ac.funagi.iot.mqtt.MqttReceiver;
import jp.ac.funagi.iot.service.SensorDataService;

/**
 * Quarkusのライフサイクルに合わせてMQTT受信処理を開始・停止する。
 * <p>
 * 起動時に設定値とサービスを使ってMqttReceiverを生成し、終了時にリソースを解放する。
 * </p>
 */
@ApplicationScoped
public class SensorPipeline {

    @ConfigProperty(name = "mqtt.broker-url")
    String brokerUrl;
    @ConfigProperty(name = "mqtt.topic")
    String topic;
    @ConfigProperty(name = "mqtt.client-id")
    String clientId;
    @ConfigProperty(name = "mqtt.qos")
    int qos;

    @Inject
    SensorDataService service;

    private MqttReceiver receiver;

    /**
     * Quarkus起動イベントを受けてMQTT受信を開始する。
     * 
     * @param event Quarkusの起動イベント
     */
    void onStart(@Observes StartupEvent event) {

        // 設定値とCDI管理下のサービスを受信クラスに渡す。
        receiver = new MqttReceiver(brokerUrl, topic, clientId, qos, service);
        try {
            receiver.start();
        } catch (Exception e) {
            throw new IllegalStateException("MQTT受信の起動に失敗しました", e);
        }
    }

    /**
     * Quarkus終了イベントを受けてMQTT接続とExecutorを停止する。
     * 
     * @param event Quarkusの終了イベント
     */
    void onStop(@Observes ShutdownEvent event) {
        if (receiver != null) {
            try {
                receiver.stop();
            } catch (Exception e) {
                throw new IllegalStateException("MQTT受信の停止に失敗しました", e);
            }
        }
    }
}
