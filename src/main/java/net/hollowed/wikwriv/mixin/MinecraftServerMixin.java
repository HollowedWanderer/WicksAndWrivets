package net.hollowed.wikwriv.mixin;

import de.xyndra.wikwriv.ResourceUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.SaveProperties;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.file.Path;
import java.util.List;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Final
    @Shadow
    protected SaveProperties saveProperties;
    @Inject(method = "loadWorld", at = @At("TAIL"))
    protected void loadWorld(CallbackInfo ci) {
        Path worldPath = Path.of("").resolve("saves").resolve(saveProperties.getLevelName());
        // Copy dimension data from resources to world folder
        Path dimensionDataPath = worldPath.resolve("dimensions").resolve("wikwriv");
        if (!dimensionDataPath.toFile().exists()) {
            dimensionDataPath.toFile().mkdirs();
        }
        List<String> resources = ResourceUtil.INSTANCE.getFilesInResourceDir("/dimension_data/wikwriv");
        for (String resource : resources) {
            ResourceUtil.INSTANCE.copyResourceToDir("/dimension_data/wikwriv/" + resource, dimensionDataPath);
        }
    }
}
