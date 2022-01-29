package foo.bar.clean.domain.wallet

import kotlinx.serialization.*

/**
 * Copyright © 2015-2021 early.co. All rights reserved.
 */

@Serializable
data class WalletState(
    val mobileWalletAmount: Int,
    val totalDollarsAvailable: Int = 10,
    @Transient
    val loading: Boolean = false
) {

    val savingsWalletAmount: Int
        get() = totalDollarsAvailable - mobileWalletAmount

    fun canIncrease(): Boolean = mobileWalletAmount < totalDollarsAvailable
    fun canDecrease(): Boolean = mobileWalletAmount > 0
}
