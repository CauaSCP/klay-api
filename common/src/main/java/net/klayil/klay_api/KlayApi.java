package net.klayil.klay_api;

import net.klayil.klay_api.block.KlayApiModBlocks;
import net.klayil.klay_api.item.KlayApiModItems;
import net.klayil.klay_api.tabs.KlayApiModTabs;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public final class KlayApi {
    public static final String MOD_ID = "klay_api";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

//    public static boolean isFabric = false;

    public static void init() {
        LOGGER.info("Initializing KlayApi Mod");

//        isFabric = Platform.isFabric();

//        KlayApiModTabs.initTabs();

        KlayApiModBlocks.initBlocks();
        KlayApiModItems.initItems();

    }
}
