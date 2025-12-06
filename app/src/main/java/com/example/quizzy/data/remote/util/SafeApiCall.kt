package com.example.quizzy.data.remote.util

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Executes a safe API call with standardized success/error handling.
 *
 * @param apiCall A suspend function that returns a Retrofit Response<T>
 * @param onSuccess Called when the response is successful and body is non-null
 * @param onError Called on failure or empty body with an appropriate error message
 * @return The result of onSuccess or onError
 */
suspend fun <T : Any, R> safeApiCall(
    apiCall: suspend () -> Response<T>,
    onSuccess: (T) -> R,
    onError: (String) -> R
): R {
    return runCatching {
        apiCall()
    }.fold(
        onSuccess = { response ->
            if (response.isSuccessful) {
                response.body()?.let { onSuccess(it) }
                    ?: onError("Empty response body")
            } else {
                onError("Server error: ${response.code()} ${response.message()}")
            }
        },
        onFailure = { throwable ->
            val message = when (throwable) {
                is IOException -> "No internet connection"
                is HttpException -> "HTTP error: ${throwable.code()}"
                else -> "Unexpected error: ${throwable.message ?: "Unknown"}"
            }
            onError(message)
        }
    )
}
