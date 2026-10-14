/*
 * ======================================================================
 *  VD GOOGLE  ::  your Google services, read on-device
 *  RCS, Google Wallet and Play Integrity attestations - local, no internet.
 *
 *  Copyright (C) 2026  VD171
 *  SPDX-License-Identifier: AGPL-3.0-or-later
 *
 *  Free software under the GNU AGPL v3 or later. NETWORK COPYLEFT: run a
 *  modified version, even as a service, and you MUST offer its source.
 *
 *  Site           : https://vd171.ru
 *  Site           : https://vd.priv8.ru
 *  Source         : https://github.com/VD171/VD-Google
 *  GitHub         : @VD171 https://github.com/VD171
 *  XDA-Developers : @VD171 https://xdaforums.com/m/vd171.4699873/
 *  Telegram       : @VD_Priv8 https://t.me/VD_Priv8
 *  Discord        : @VD.Priv8 https://discord.com/users/1296831918989639721
 *  E-mail         : vd.priv8@pm.me
 * ======================================================================
 */
package ru.vd171.vdgoogle.ui

import android.app.Application
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.vd171.vdgoogle.data.AttApp
import ru.vd171.vdgoogle.data.AttReader
import ru.vd171.vdgoogle.data.RcsData
import ru.vd171.vdgoogle.data.RcsReader
import ru.vd171.vdgoogle.data.WalletData
import ru.vd171.vdgoogle.data.WalletReader
import ru.vd171.vdgoogle.root.RootRepo

enum class RootGate { CHECKING, GRANTED, DENIED }

data class UiState(
    val gate: RootGate = RootGate.CHECKING,
    val loading: Boolean = false,
    val rcs: RcsData? = null,
    val wallet: WalletData? = null,
    val att: List<AttApp>? = null,
)

class AppViewModel(app: Application) : AndroidViewModel(app) {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    private val hasNfc: Boolean =
        app.packageManager.hasSystemFeature(PackageManager.FEATURE_NFC)

    init {
        openGate()
    }

    fun openGate() {
        _state.value = UiState(gate = RootGate.CHECKING)
        viewModelScope.launch {
            val ok = RootRepo.bind(getApplication())
            if (!ok) {
                _state.value = UiState(gate = RootGate.DENIED)
                return@launch
            }
            _state.value = _state.value.copy(gate = RootGate.GRANTED, loading = true)
            refresh()
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val rcs = RcsReader.load()
            val wallet = WalletReader.load(hasNfc)
            val att = AttReader.load()
            _state.value = _state.value.copy(loading = false, rcs = rcs, wallet = wallet, att = att)
        }
    }

    fun reloadAtt() {
        viewModelScope.launch {
            _state.value = _state.value.copy(att = null)
            _state.value = _state.value.copy(att = AttReader.load())
        }
    }

    fun resetWalletCheck() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val ok = WalletReader.resetAttestation()
            ru.vd171.vdgoogle.util.ActionLog.record(getApplication(), "clear_wallet_last_checked", ok)
            _state.value = _state.value.copy(loading = false, wallet = WalletReader.load(hasNfc))
        }
    }
}
