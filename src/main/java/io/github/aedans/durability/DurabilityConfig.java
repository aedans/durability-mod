package io.github.aedans.durability;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Durability.MOD_ID)
@Config.LangKey(Durability.MOD_ID + ".config.title")
public class DurabilityConfig {
    @Config.Comment({
            "The factor to multiply the durability by.",
            "Can be used with divide to provide a fraction."})
    public static int multiply = 1;

    @Config.Comment({
            "The factor to divide the durability by.",
            "Can be used with multiply to provide a fraction."})
    public static int divide = 1;

    @Mod.EventBusSubscriber(modid = Durability.MOD_ID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Durability.MOD_ID)) {
                ConfigManager.sync(Durability.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}
