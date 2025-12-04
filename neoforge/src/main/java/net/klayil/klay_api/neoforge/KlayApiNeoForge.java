package net.klayil.klay_api.neoforge;

import net.neoforged.fml.common.Mod;

import net.klayil.klay_api.KlayApi;

@Mod(KlayApi.MOD_ID)
public final class KlayApiNeoForge {
    public KlayApiNeoForge() {
        KlayApi.init();
    }
}
