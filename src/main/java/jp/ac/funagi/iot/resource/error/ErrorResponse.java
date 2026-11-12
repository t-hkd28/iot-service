package jp.ac.funagi.iot.resource.error;

/**
 * REST APIのエラーレスポンスの内容を表すクラス。
 *
 * @param message エラーの概要を表す文字列
 */
public record ErrorResponse(String message) {}
