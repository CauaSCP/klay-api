package net.klayil.klay_api.fabric.item;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.BiFunction;

public class KlayApiModItems {
    public static Item.Properties baseProperties(String name, String mod_id) {
        return net.klayil.klay_api.item.KlayApiModItems._baseProperties(name, mod_id);
    }

    public static RegistrySupplier<Item> createItem(String name, ResourceKey<CreativeModeTab> creativeModeTab, BiFunction<String, String, Item.Properties> propsFunction, String mod_id) {
        return net.klayil.klay_api.item.KlayApiModItems._createItem(name, creativeModeTab, propsFunction, mod_id);
    }

    public static final DeferredRegister<Item> createItemsRegister(String mod_id) {
        return net.klayil.klay_api.item.KlayApiModItems._createItemsRegister(mod_id);
    }
}
