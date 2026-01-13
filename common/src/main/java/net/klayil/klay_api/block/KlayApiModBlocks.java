package net.klayil.klay_api.block;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
//import net.klayil.klay_api.KlayApi;
import net.klayil.klay_api.KlayApi;
import net.klayil.klay_api.item.KlayApiModItems;
//import net.klayil.klay_api.tabs.KlayApiModTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
//import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import net.minecraft.world.item.Items;

public class KlayApiModBlocks {
    private static final Map<String, DeferredRegister<Block>> KlayApiBlocksRegisters = new HashMap<>();
    public static Map<String, RegistrySupplier<Block>> AllKlayApiBlocks = new HashMap<>();

    public static Map<ResourceKey<CreativeModeTab>, ArrayList<String>> blockItemCreativeModeTabs = new HashMap<>();

    public static final DeferredRegister<Block> createBlocksRegister(String mod_id) {
        KlayApiBlocksRegisters.put(mod_id, DeferredRegister.create(mod_id, Registries.BLOCK));

        return KlayApiBlocksRegisters.get(mod_id);
    }

    public static void initBlocks() {
    }

    private static RegistrySupplier<Block> registerBlock(String name, Supplier<Block> block, String mod_id) {
        ResourceLocation blockLocation = ResourceLocation.fromNamespaceAndPath(mod_id, name);

        DeferredRegister<Block> blocks = KlayApiBlocksRegisters.get(mod_id);
        AllKlayApiBlocks.put(blockLocation.toString(), blocks.register(blockLocation, block));

        return AllKlayApiBlocks.get(blockLocation.toString());
    }

    public static BlockBehaviour.Properties baseProperties(String name, String mod_id) {
        return BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(mod_id, name)));
    }

    private static void _createBlockBefore(ResourceKey<CreativeModeTab> creativeModeTab, String mod_id, String name) {
        if (!blockItemCreativeModeTabs.containsKey(creativeModeTab)) blockItemCreativeModeTabs.put(creativeModeTab, new ArrayList<>());

        blockItemCreativeModeTabs.get(creativeModeTab).add(ResourceLocation.fromNamespaceAndPath(mod_id, name).toString());
    }
    public static RegistrySupplier<Block> createBlock(String name, ResourceKey<CreativeModeTab> creativeModeTab, BlockBehaviour.Properties properties, String mod_id) {
        _createBlockBefore(creativeModeTab, mod_id, name);

        return registerBlock(name, () -> new Block(properties), mod_id);
    }

    public static RegistrySupplier<Block> createBlock(String name, ResourceKey<CreativeModeTab> creativeModeTab, BlockBehaviour.Properties _properties, String mod_id, boolean doesRequireToolForDrops) {
        _createBlockBefore(creativeModeTab, mod_id, name);

//        BlockBehaviour.Properties _properties = propsFunction.apply(name, mod_id);

        if (doesRequireToolForDrops) {
            _properties = _properties.requiresCorrectToolForDrops();
        }

        BlockBehaviour.Properties properties = _properties;
        return registerBlock(name, () -> new Block(properties), mod_id);
    }

    public static RegistrySupplier<Block> createBlock(String name, ResourceKey<CreativeModeTab> creativeModeTab, BlockBehaviour.Properties _properties, String mod_id, boolean doesRequireToolForDrops, float destroyTimeStrength) {
        _createBlockBefore(creativeModeTab, mod_id, name);

//        BlockBehaviour.Properties _properties = propsFunction.apply(name, mod_id);

        if (doesRequireToolForDrops) {
            _properties = _properties.requiresCorrectToolForDrops();
        }

        BlockBehaviour.Properties properties = _properties.strength(destroyTimeStrength);
        return registerBlock(name, () -> new Block(properties), mod_id);
    }

    public static RegistrySupplier<Block> createBlock(String name, @Nullable ResourceKey<CreativeModeTab> creativeModeTab, BlockBehaviour.Properties _properties, String mod_id, boolean doesRequireToolForDrops, float destroyTimeStrength, float explosionResistance) {
        _createBlockBefore(creativeModeTab, mod_id, name);

//        BlockBehaviour.Properties _properties = propsFunction.apply(name, mod_id);

        if (doesRequireToolForDrops) {
            _properties = _properties.requiresCorrectToolForDrops();
        }

        BlockBehaviour.Properties properties = _properties.strength(destroyTimeStrength, explosionResistance);
        return registerBlock(name, () -> new Block(properties), mod_id);
    }
}
