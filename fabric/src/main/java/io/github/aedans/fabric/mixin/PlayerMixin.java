package io.github.aedans.fabric.mixin;

import io.github.aedans.DurabilityMod;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
  @Inject(at = @At("RETURN"), method = { "getDestroySpeed" }, cancellable = true)
  public void getDestroySpeed(BlockState blockState, CallbackInfoReturnable<Float> cir) {
    var config = DurabilityMod.getConfig();
    cir.setReturnValue((cir.getReturnValue() * (float) config.speed().multiply()) / (float) config.speed().divide());
  }
}
