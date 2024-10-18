package net.hollowed.wikwriv.mixin;

import de.xyndra.wikwriv.cutscenes.CutSceneStore;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    public void onKey(long window, int key, int scancode, int action, int mods, CallbackInfo ci) {
        if (CutSceneStore.INSTANCE.isInputBlocked()) {
            ci.cancel();
        }
    }
}
