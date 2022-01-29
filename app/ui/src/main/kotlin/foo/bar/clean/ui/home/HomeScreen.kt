package foo.bar.clean.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import co.early.fore.kt.core.delegate.Fore
import co.early.fore.kt.core.ui.observeAsState
import foo.bar.clean.domain.wallet.Wallet
import foo.bar.clean.ui.R
import org.koin.androidx.compose.get

@Preview
@Composable
fun HomeScreen(
    wallet: Wallet = get(),
) {

    val walletState by wallet.observeAsState { wallet.state }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Fore.getLogger().v("Main")

        Text(modifier = Modifier
            .padding(dimensionResource(id = R.dimen.common_space_small)),
            text = stringResource(id = R.string.wallet_dashboard))

        if (walletState.loading) {
            CircularProgressIndicator()
        } else {

            Row(
                modifier = Modifier
                    .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Fore.getLogger().v("Wallet DashBoard")

                Button(
                    onClick = { wallet.increaseMobileWallet() },
                    enabled = walletState.canIncrease()
                ) {
                    Text(text = stringResource(id = R.string.increase))
                }

                Text(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.common_space_small)),
                    text = walletState.mobileWalletAmount.toString()
                )

                Button(
                    onClick = { wallet.decreaseMobileWallet() },
                    enabled = walletState.canDecrease()
                ) {
                    Text(text = stringResource(id = R.string.decrease))
                }
            }
        }
    }
}
