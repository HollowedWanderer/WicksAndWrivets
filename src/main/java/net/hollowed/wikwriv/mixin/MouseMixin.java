package net.hollowed.wikwriv.mixin;

import de.xyndra.wikwriv.cutscenes.CutSceneStore;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {
    @Inject(method = "onMouseButton", at = @At("HEAD"), cancellable = true)
    public void onMouseButton(long window, int button, int action, int mods, CallbackInfo ci) {
        if (CutSceneStore.INSTANCE.isInputBlocked()) {
            ci.cancel();
        }
    }

    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    public void onMouseScroll(long window, double xoffset, double yoffset, CallbackInfo ci) {
        if (CutSceneStore.INSTANCE.isInputBlocked()) {
            ci.cancel();
        }
    }

    @Inject(method = "onCursorPos", at = @At("HEAD"), cancellable = true)
    public void onCursorPos(long window, double xpos, double ypos, CallbackInfo ci) {
        if (CutSceneStore.INSTANCE.isCameraBlocked()) {
            ci.cancel();
        }
    }
}
