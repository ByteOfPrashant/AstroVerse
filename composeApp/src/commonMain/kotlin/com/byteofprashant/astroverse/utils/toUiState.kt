import com.byteofprashant.data.utils.ErrorHandler

fun <T> Result<T>.toUiState(): UiState<T> {
    return fold(
        onSuccess = { UiState.Success(it) },
        onFailure = { UiState.Error(ErrorHandler.handleError(it)) }
    )
}
