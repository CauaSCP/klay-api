package net.klayil.klay_api.fabric;

import net.fabricmc.api.ModInitializer;
import net.klayil.klay_api.fabric.block.KlayApiModBlocks;
import net.klayil.klay_api.fabric.item.KlayApiModItems;
import net.klayil.klay_api.KlayApi;

public final class KlayApiFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        net.klayil.klay_api.block.KlayApiModBlocks._createBlocksRegisterValue = KlayApiModBlocks::createBlocksRegister;
        net.klayil.klay_api.block.KlayApiModBlocks._createBlockValue = KlayApiModBlocks::createBlock;
        net.klayil.klay_api.block.KlayApiModBlocks._basePropertiesValue = KlayApiModBlocks::baseProperties;

        net.klayil.klay_api.item.KlayApiModItems._createItemsRegisterValue = KlayApiModItems::createItemsRegister;
        net.klayil.klay_api.item.KlayApiModItems._createItemValue = KlayApiModItems::createItem;
        net.klayil.klay_api.item.KlayApiModItems._basePropertiesValue = KlayApiModItems::baseProperties;

        KlayApi.init();
    }
}
