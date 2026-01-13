package net.klayil.klay_api.item;

//import dev.architectury.registry.CreativeTabRegistry;

import dev.architectury.registry.CreativeTabRegistry;
import net.klayil.klay_api.KlayApi;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
//import net.klayil.klay_api.tabs.KlayApiModTabs;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;
//import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
//import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static net.klayil.klay_api.block.KlayApiModBlocks.AllKlayApiBlocks;
import static net.klayil.klay_api.block.KlayApiModBlocks.blockItemCreativeModeTabs;

public class KlayApiModItems {
    private static final Map<String, DeferredRegister<Item>> KlayApiItemsRegisters = new HashMap<>();
    public static final Map<String, RegistrySupplier<Item>> AllKlayApiItems = new HashMap<>();

    public static DeferredRegister<Item> createItemsRegister(String mod_id) {
        KlayApiItemsRegisters.put(mod_id, DeferredRegister.create(mod_id, Registries.ITEM));

        return KlayApiItemsRegisters.get(mod_id);
    }


    static int masterIndex;
    static ArrayList<int[]> toRemove;
    static ArrayList<ResourceKey<CreativeModeTab>> keys;
    public static void initItems() {
    }

    private static RegistrySupplier<Item> registerItem(String itemName, Supplier<Item> item, String mod_id) {
        ResourceLocation itemLocation = ResourceLocation.fromNamespaceAndPath(mod_id, itemName);

        DeferredRegister<Item> items = KlayApiItemsRegisters.get(mod_id);
        AllKlayApiItems.put(itemLocation.toString(), items.register(itemLocation, item));

        return AllKlayApiItems.get(itemLocation.toString());
    }

    public static Item.Properties baseProperties(String name, String mod_id) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(mod_id, name)));
    }

    public static RegistrySupplier<Item> createItem(String name, @Nullable ResourceKey<CreativeModeTab> creativeModeTab, Supplier<Item.Properties> propsSupplier, String mod_id) {
        return registerItem(name, () -> {
            Item.Properties properties = propsSupplier.get();

            Item item = new Item(properties);

            if (creativeModeTab != null) {
                CreativeTabRegistry.append(creativeModeTab, item);
            }

            return item;
        }, mod_id);
    }

    public static RegistrySupplier<Item> createItem(String name, @Nullable ResourceKey<CreativeModeTab> creativeModeTab, String mod_id) {
        RegistrySupplier<Item> item = registerItem(name, () -> new Item(baseProperties(name, mod_id)), mod_id);

        if (creativeModeTab != null) {
            CreativeTabRegistry.append(creativeModeTab, item);
        }

        return item;
    }

    public static RegistrySupplier<Item> createItem(String name, ResourceKey<CreativeModeTab> creativeModeTab, BiFunction<String, String, Item.Properties> propsFunction, String mod_id) {
        RegistrySupplier<Item> item = registerItem(name, () -> new Item(propsFunction.apply(name, mod_id)), mod_id);

        if (creativeModeTab != null) {
            CreativeTabRegistry.append(creativeModeTab, item);
        }

        return item;
    }

    public static void createItemsOfBlocks() {
        for ( ResourceKey<CreativeModeTab> curCreativeModeTab : blockItemCreativeModeTabs.keySet() ) {
//            KlayApi.LOGGER.info("@Size >> %d".formatted(blockItemCreativeModeTabs.get(curCreativeModeTab).size()));

            if (blockItemCreativeModeTabs.get(curCreativeModeTab).isEmpty()) continue;

            ArrayList<String> arr = blockItemCreativeModeTabs.get(curCreativeModeTab);

            for (int index = 0; index < arr.size(); index++) {
                ResourceLocation itemLocation = ResourceLocation.parse( arr.get(index) );

                RegistrySupplier<Item> curBlockItem = registerItem(itemLocation.getPath(), () -> new BlockItem(AllKlayApiBlocks.get(itemLocation.toString()).get(), baseProperties(itemLocation.getPath(), itemLocation.getNamespace())), itemLocation.getNamespace());

                if (curCreativeModeTab != null) {
                    CreativeTabRegistry.append(curCreativeModeTab, curBlockItem);
                }

//                CreativeTabRegistry.append(curCreativeModeTab, blockItem);

                KlayApi.LOGGER.info("@ITEM >> %s".formatted(itemLocation.toString()));
            }
        }
    }
}
