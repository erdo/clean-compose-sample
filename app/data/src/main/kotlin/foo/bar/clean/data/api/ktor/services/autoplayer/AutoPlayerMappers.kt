package foo.bar.clean.data.api.ktor.services.autoplayer

import foo.bar.clean.domain.tictactoe.NextTurn

fun NextTurnPojo.toDomain(): NextTurn {
    return NextTurn(xPos, yPos)
}