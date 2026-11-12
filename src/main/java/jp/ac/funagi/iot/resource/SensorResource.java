package jp.ac.funagi.iot.resource;

import jakarta.ws.rs.core.Response;
import jp.ac.funagi.iot.service.SensorDataService;

/** 【訓練生実装】GET /api/sensors?limit=100 を提供するRESTリソース。 */
public class SensorResource {

    private final SensorDataService service;

    public SensorResource(SensorDataService service) {
        this.service = service;
    }

    public Response getSensors(int limit) {
        // TODO 1: limitが1～1000の範囲外ならHTTP 400を返す。
        // TODO 2: service.findAll(limit)を呼び出し、正常時はHTTP 200とJSONを返す。
        throw new UnsupportedOperationException("TODO: REST APIを実装してください");
    }
}
