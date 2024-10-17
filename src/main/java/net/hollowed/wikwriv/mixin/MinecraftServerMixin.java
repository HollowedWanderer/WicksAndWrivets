package net.hollowed.wikwriv.mixin;

import de.xyndra.wikwriv.ResourceUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.LevelStorage;
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
    protected LevelStorage.Session session;
    @Inject(method = "runServer", at = @At("HEAD"))
    protected void runServer(CallbackInfo ci) {
        Path worldPath = Path.of("").resolve("saves").resolve(session.getDirectory().path());
        // Copy dimension data from resources to world folder
        Path dimensionDataPath = worldPath.resolve("dimensions").resolve("wikwriv").resolve("wikwriv");
        if (!dimensionDataPath.toFile().exists()) {
            dimensionDataPath.toFile().mkdirs();
        }
        String resourcePath = "/dimension_data/wikwriv";
        List<String> resources = ResourceUtil.INSTANCE.getFilesInResourceDir(resourcePath);
        for (String resource : resources) {
            System.out.println("Copying " + resource + " to " + dimensionDataPath);
            ResourceUtil.INSTANCE.copyResourceToDir(resource, resourcePath, dimensionDataPath);
        }
    }
}
