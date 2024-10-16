package net.hollowed.wikwriv.mixin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGenerators;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import de.xyndra.wikwriv.worldgen.WikWrivLevelSource;

@Mixin(ChunkGenerators.class)
public class ChunkGeneratorsMixin {
    @Inject(method = "registerAndGetDefault", at = @At("HEAD"))
    private static void bootstrap(Registry<MapCodec<? extends ChunkGenerator>> registry, CallbackInfoReturnable<Codec<? extends ChunkGenerator>> cir) {
        Registry.register(registry, "wikwriv", WikWrivLevelSource.CODEC);
    }
}
