package net.klayil.klay_api.fabric;

import net.fabricmc.api.ModInitializer;
import net.klayil.klay_api.KlayApi;

public final class KlayApiFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        KlayApi.init();
    }
}
