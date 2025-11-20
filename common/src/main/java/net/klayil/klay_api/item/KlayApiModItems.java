package net.klayil.klay_api.item;

//import dev.architectury.registry.CreativeTabRegistry;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.klayil.klay_api.block.KlayApiModBlocks.AllKlayApiBlocks;
import static net.klayil.klay_api.block.KlayApiModBlocks.blockItemCreativeModeTabs;

public class KlayApiModItems {
    private static final Map<String, DeferredRegister<Item>> KlayApiItemsRegisters = new HashMap<>();
    private static final Map<String, RegistrySupplier<Item>> AllKlayApiItems = new HashMap<>();


    /// # CHANGABLE FUNCS
    @Nullable
    public static Function<String, DeferredRegister<Item>> _createItemsRegisterValue;
    public static DeferredRegister<Item> createItemsRegister(String mod_id) {
        assert _createItemsRegisterValue != null;
        return _createItemsRegisterValue.apply(mod_id);
    }


    @FunctionalInterface
    public interface CreateThingFunction<T1, T2, T3, T4, R> {
        R apply(T1 t1, T2 t2, T3 t3, T4 t4);
    }
    @Nullable public static CreateThingFunction<String, ResourceKey<CreativeModeTab>, BiFunction<String, String, Item.Properties>, String, RegistrySupplier<Item>> _createItemValue;
    public static RegistrySupplier<Item> createItem(String name, ResourceKey<CreativeModeTab> creativeModeTab, BiFunction<String, String, Item.Properties> propsFunction, String mod_id) {
        assert _createItemValue != null;
        return _createItemValue.apply(name, creativeModeTab, propsFunction, mod_id);
    }


    @Nullable public static BiFunction<String, String, Item.Properties> _basePropertiesValue;
    public static Item.Properties baseProperties(String name, String mod_id) {
        assert _basePropertiesValue != null;
        return  _basePropertiesValue.apply(name, mod_id);
    }
    /// #################

    public static DeferredRegister<Item> _createItemsRegister(String mod_id) {
        KlayApiItemsRegisters.put(mod_id, DeferredRegister.create(mod_id, Registries.ITEM));

        return KlayApiItemsRegisters.get(mod_id);
    }


    public static void initItems() {

        for ( ResourceKey<CreativeModeTab> curCreativeModeTab : blockItemCreativeModeTabs.keySet() ) {
//            KlayApi.LOGGER.info("@Size >> %d".formatted(blockItemCreativeModeTabs.get(curCreativeModeTab).size()));

            if (blockItemCreativeModeTabs.get(curCreativeModeTab).isEmpty()) continue;

            ArrayList<String> arr = blockItemCreativeModeTabs.get(curCreativeModeTab);

            for (int index = 0; index < arr.size(); index++) {
                ResourceLocation itemLocation = ResourceLocation.parse( arr.get(index) );

//                RegistrySupplier<Item> blockItem =
                registerItem(itemLocation.getPath(), () -> new BlockItem(AllKlayApiBlocks.get(itemLocation.toString()).get(), baseProperties(itemLocation.getPath(), itemLocation.getNamespace()).arch$tab(curCreativeModeTab)), itemLocation.getNamespace());

//                CreativeTabRegistry.append(curCreativeModeTab, blockItem);

                KlayApi.LOGGER.info("@ITEM >> %s".formatted(itemLocation.toString()));
            }
        }
//        }

//        KlayApi.LOGGER.info("isFabric: %s".formatted(KlayApi.isFabric));
    }

    private static RegistrySupplier<Item> registerItem(String itemName, Supplier<Item> item, String mod_id) {
        ResourceLocation itemLocation = ResourceLocation.fromNamespaceAndPath(mod_id, itemName);

        DeferredRegister<Item> items = KlayApiItemsRegisters.get(mod_id);
        AllKlayApiItems.put(itemLocation.toString(), items.register(itemLocation, item));

        return AllKlayApiItems.get(itemLocation.toString());
    }

    public static Item.Properties _baseProperties(String name, String mod_id) {
        return new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(mod_id, name)));
    }

    public static RegistrySupplier<Item> _createItem(String name, ResourceKey<CreativeModeTab> creativeModeTab, BiFunction<String, String, Item.Properties> propsFunction, String mod_id) {
//        CreativeTabRegistry.append(creativeModeTab, item);

        return registerItem(name, () -> new Item(propsFunction.apply(name, mod_id).arch$tab(creativeModeTab)), mod_id);
    }
}
