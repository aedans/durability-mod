package io.github.aedans.durability;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(
        modid = Durability.MOD_ID,
        name = Durability.MOD_NAME,
        version = Durability.VERSION
)
public class Durability {
    public static final String MOD_ID = "durability";
    public static final String MOD_NAME = "Durability Mod";
    public static final String VERSION = "1.0";

    @Mod.Instance(MOD_ID)
    public static Durability INSTANCE;

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        ForgeRegistries.ITEMS.getValuesCollection()
                .stream()
                .filter(x -> {
                    try {
                        return x.getMaxDamage(null) != 0;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .forEach(x -> {
                    int maxDamage = x.getMaxDamage(null);
                    int newDamage = Math.max(1, (maxDamage * DurabilityConfig.multiply) / DurabilityConfig.divide);
                    x.setMaxDamage(newDamage);
                });
    }
}
