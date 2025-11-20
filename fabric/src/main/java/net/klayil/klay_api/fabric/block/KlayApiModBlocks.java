package net.klayil.klay_api.fabric.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.BiFunction;

public class KlayApiModBlocks {
    public static BlockBehaviour.Properties baseProperties(String name, String mod_id) {
        return net.klayil.klay_api.block.KlayApiModBlocks._baseProperties(name, mod_id);
    }

    public static RegistrySupplier<Block> createBlock(String name, ResourceKey<CreativeModeTab> creativeModeTab, BlockBehaviour.Properties properties, String mod_id) {
        return net.klayil.klay_api.block.KlayApiModBlocks._createBlock(name, creativeModeTab, properties, mod_id);
    }

    public static RegistrySupplier<Block> createBlock(String name, ResourceKey<CreativeModeTab> creativeModeTab, BlockBehaviour.Properties properties, String mod_id, boolean doesRequireToolForDrops) {
        return net.klayil.klay_api.block.KlayApiModBlocks._createBlock(name, creativeModeTab, properties, mod_id, doesRequireToolForDrops);
    }

    public static RegistrySupplier<Block> createBlock(String name, ResourceKey<CreativeModeTab> creativeModeTab, BlockBehaviour.Properties properties, String mod_id, boolean doesRequireToolForDrops, float destroyTimeStrength) {
        return net.klayil.klay_api.block.KlayApiModBlocks._createBlock(name, creativeModeTab, properties, mod_id, doesRequireToolForDrops, destroyTimeStrength);
    }

    public static RegistrySupplier<Block> createBlock(String name, ResourceKey<CreativeModeTab> creativeModeTab, BlockBehaviour.Properties properties, String mod_id, boolean doesRequireToolForDrops, float destroyTimeStrength, float explosionResistance) {
        return net.klayil.klay_api.block.KlayApiModBlocks._createBlock(name, creativeModeTab, properties, mod_id, doesRequireToolForDrops, destroyTimeStrength, explosionResistance);
    }

    public static final DeferredRegister<Block> createBlocksRegister(String mod_id) {
        return net.klayil.klay_api.block.KlayApiModBlocks._createBlocksRegister(mod_id);
    }
}
