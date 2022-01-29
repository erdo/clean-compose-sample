package foo.bar.clean.data.api

import foo.bar.clean.domain.DomainError
import foo.bar.clean.domain.DomainError.*
import kotlinx.serialization.Serializable

@Serializable
sealed class DataError(val resolution: DomainError) {
    object Misc: DataError(RETRY_LATER)
    object Network: DataError(CHECK_NETWORK_THEN_RETRY)
    object SecurityUnknown: DataError(CHECK_NETWORK_THEN_RETRY)
    object Server: DataError(RETRY_LATER)
    object AlreadyExecuted: DataError(RETRY_LATER)
    object Client: DataError(RETRY_LATER)
    object RateLimited: DataError(RETRY_LATER)
    object SessionTimedOut: DataError(LOGIN_THEN_RETRY)
    object Busy: DataError(RETRY_LATER)
    object AutoPlayerNotAvailable: DataError(RETRY_LATER)
    object AutoPlayerAsleep: DataError(RETRY_LATER)
    object AutoPlayerRequiresPayment: DataError(CHECK_ACCOUNT)
}
