package net.klayil.klay_api.neoforge;

import net.klayil.klay_api.neoforge.block.KlayApiModBlocks;
import net.klayil.klay_api.neoforge.item.KlayApiModItems;
import net.neoforged.fml.common.Mod;

import net.klayil.klay_api.KlayApi;

@Mod(KlayApi.MOD_ID)
public final class KlayApiNeoForge {
    public KlayApiNeoForge() {
        net.klayil.klay_api.block.KlayApiModBlocks._createBlocksRegisterValue = KlayApiModBlocks::createBlocksRegister;
        net.klayil.klay_api.block.KlayApiModBlocks._createBlockValue = KlayApiModBlocks::createBlock;
        net.klayil.klay_api.block.KlayApiModBlocks._basePropertiesValue = KlayApiModBlocks::baseProperties;

        net.klayil.klay_api.item.KlayApiModItems._createItemsRegisterValue = KlayApiModItems::createItemsRegister;
        net.klayil.klay_api.item.KlayApiModItems._createItemValue = KlayApiModItems::createItem;
        net.klayil.klay_api.item.KlayApiModItems._basePropertiesValue = KlayApiModItems::baseProperties;

        KlayApi.init();
    }
}
