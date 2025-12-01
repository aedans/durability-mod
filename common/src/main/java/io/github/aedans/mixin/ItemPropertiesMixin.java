package io.github.aedans.mixin;

import io.github.aedans.DurabilityMod;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.Properties.class)
public abstract class ItemPropertiesMixin {
  @Shadow
  private DataComponentMap.Builder components;

  @Inject(at = @At("TAIL"), method = "component")
  public <T> void component(DataComponentType<T> dataComponentType, T object, CallbackInfoReturnable<Item.Properties> cir) {
    if (dataComponentType.equals(DataComponents.MAX_DAMAGE)) {
      var config = DurabilityMod.getConfig();
      this.components.set(DataComponents.MAX_DAMAGE, ((int) object * config.durability().multiply()) / config.durability().divide());
    }
  }
}
