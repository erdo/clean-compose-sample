package foo.bar.clean.data.api.ktor.services.autoplayer

import co.early.fore.kt.core.type.Either
import co.early.fore.kt.core.logging.Logger
import co.early.fore.kt.net.ktor.CallWrapperKtor
import foo.bar.clean.data.api.DataError
import foo.bar.clean.data.api.toDomain
import foo.bar.clean.domain.DomainError
import foo.bar.clean.domain.tictactoe.AutoPlayerService
import foo.bar.clean.domain.tictactoe.NextTurn

class AutoPlayerServiceImp(
    private val client: AutoPlayerApi,
    private val wrapper: CallWrapperKtor<DataError>,
    private val logger: Logger,
) : AutoPlayerService {

    override suspend fun getAutoPlayersTurn(): Either<DomainError, NextTurn> {

        val dataResult = wrapper.processCallAwait(AutoPlayerError::class.java) {
            logger.i("processing call t:" + Thread.currentThread())
            client.getAutoPlayersTurn()
        }

        val domainResult = toDomain(dataResult){
            it.toDomain()
        }

        return domainResult
    }
}
