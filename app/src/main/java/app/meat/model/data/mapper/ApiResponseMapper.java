package app.meat.model.data.mapper;

import app.meat.model.exception.ApiResponseException;
import app.meat.model.exception.NoInternetException;
import app.meat.util.connection.ConnectionUtilAbs;
import retrofit2.adapter.rxjava2.Result;

public class ApiResponseMapper {
    private ConnectionUtilAbs connectionUtilAbs;

    public ApiResponseMapper(ConnectionUtilAbs connectionUtilAbs) {
        this.connectionUtilAbs = connectionUtilAbs;
    }


    public <R> R map(Result<R> responseResult) {
        if (responseResult.isError()) {
            // No internet, etc.
            handleInternetException(responseResult.error());
        }
        return responseResult.response().body();
    }

    private void handleInternetException(Throwable throwable) {
        if (connectionUtilAbs.isOnline()) {
            throw new NoInternetException();
        } else {
            throw new ApiResponseException(throwable);
        }
    }

}
