package net.klayil.klay_api.tabs;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.klayil.klay_api.KlayApi;
import net.klayil.klay_api.item.KlayApiModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

//import static net.klayil.klay_api.item.KlayApiModItems.RUBY;

public class KlayApiModTabs {
    private static Map<String, DeferredRegister<CreativeModeTab>> KlayApiCreativeTabsRegisters = new HashMap<>();
    private static Map<String, RegistrySupplier<CreativeModeTab>> AllKlayApiCreativeTabs = new HashMap<>();


    public static final DeferredRegister<CreativeModeTab> createTabsRegister(String mod_id) {
        KlayApiCreativeTabsRegisters.put(mod_id, DeferredRegister.create(mod_id, Registries.CREATIVE_MODE_TAB));

        return KlayApiCreativeTabsRegisters.get(mod_id);
    }

    public static ResourceKey<CreativeModeTab> registerTab(String id_nam, Supplier<ItemStack> icon, String mod_id) {
        String id_full = "klay_api.%s.%s".formatted(mod_id, id_nam);

        AllKlayApiCreativeTabs.put( id_full,
                KlayApiCreativeTabsRegisters.get(mod_id).register(id_nam, () -> CreativeTabRegistry.create( Component.translatable(id_full),
                        icon
                )   )

        );

        return AllKlayApiCreativeTabs.get(id_full).getKey();
    }


    public static DeferredRegister<CreativeModeTab> _TABS;
    public static ResourceKey<CreativeModeTab> TEST_TAB;


    public static void initTabs() {
    }
}
