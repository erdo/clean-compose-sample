package foo.bar.clean.data.api.ktor.services.autoplayer

import co.early.fore.net.MessageProvider
import foo.bar.clean.data.api.DataError
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * These stubs are hosted at https://www.mocky.io/
 *
 * success example response:
 * {
 * "xPos":0,
 * "yPos":2
 * }
 * https://run.mocky.io/v3/86532780-6bb7-483f-8881-33a21e6d3669
 *
 * error example response:
 * {
 * "errorCode":"AUTOPLAYER_REQUIRES_PAYMENT"
 * }
 * https://run.mocky.io/v3/ed17f6eb-48e1-41dd-a506-206e310ad39d
 */
data class AutoPlayerApi(
    val getAutoPlayersTurn: suspend () -> NextTurnPojo
) {
    companion object {
        fun create(httpClient: HttpClient): AutoPlayerApi {

            val baseUrl = "https://www.mocky.io/v3/"
            val delaySeconds = 3

            return AutoPlayerApi(
                // example error response
                // getAutoPlayersTurn = { httpClient.get("${baseUrl}ed17f6eb-48e1-41dd-a506-206e310ad39d/?mocky-delay=${delaySeconds}s") }
                getAutoPlayersTurn = { httpClient.get("${baseUrl}86532780-6bb7-483f-8881-33a21e6d3669/?mocky-delay=${delaySeconds}s") }
            )
        }
    }
}


/**
 * network DTOs / POJOs follow
*/

@Serializable
data class NextTurnPojo(var xPos: Int, var yPos: Int)

@Serializable
sealed class AutoPlayerError(private val dataError: DataError) : MessageProvider<DataError> {

    @SerialName("AUTOPLAYER_UNAVAILABLE")
    object NotAvailable : AutoPlayerError(DataError.AutoPlayerNotAvailable)

    @SerialName("AUTOPLAYER_ASLEEP")
    object Asleep : AutoPlayerError(DataError.AutoPlayerAsleep)

    @SerialName("AUTOPLAYER_REQUIRES_PAYMENT")
    object RequiresPayment : AutoPlayerError(DataError.AutoPlayerRequiresPayment)

    override fun getMessage(): DataError {
        return dataError
    }
}
