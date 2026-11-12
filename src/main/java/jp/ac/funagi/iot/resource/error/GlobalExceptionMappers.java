package jp.ac.funagi.iot.resource.error;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jp.ac.funagi.iot.repository.SensorDataRepository.RepositoryException;

/**
 * REST APIで発生した例外をHTTPレスポンスに変換するためのExceptionMapper群。
 * <p>
 * 例外の種類ごとに適切なHTTPステータスコードとエラーメッセージを返す。
 * </p>
 */
public class GlobalExceptionMappers {

	/**
	 * RepositoryExceptionをHTTP 500に変換するExceptionMapper。
	 */
	@Provider
	@Produces(MediaType.APPLICATION_JSON)
	public static class RepositoryExceptionMapper implements ExceptionMapper<RepositoryException> {

		/**
		 * RepositoryExceptionをHTTPレスポンスに変換する。
		 *
		 * @param exception 変換対象の例外
		 * @return HTTPレスポンス
		 */
		@Override
		public Response toResponse(RepositoryException exception) {
			// リポジトリ例外のスタックトレースを標準エラー出力に出力する。
			exception.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(new ErrorResponse("データアクセス時にエラーが発生しました"))
					.build();
		}
	}

	/**
	 * 予期しない例外をHTTP 500に変換するExceptionMapper。
	 */
	@Provider
	@Produces(MediaType.APPLICATION_JSON)
	public static class FallbackExceptionMapper implements ExceptionMapper<Throwable> {

		/**
		 * 予期しない例外をHTTPレスポンスに変換する。
		 *
		 * @param exception 変換対象の例外
		 * @return HTTPレスポンス
		 */
		@Override
		public Response toResponse(Throwable exception) {
			// Webアプリケーション例外の場合は、設定済みのレスポンスをそのまま返す。
			if (exception instanceof WebApplicationException webEx) {
				return webEx.getResponse();
			}

			// 予期しない例外のスタックトレースを標準エラー出力に出力する。
			exception.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(new ErrorResponse("予期しないエラーが発生しました"))
					.build();
		}
	}
}
