package net.blay09.mods.nolittering.mixin;

import net.blay09.mods.nolittering.NoLitteringConfig;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RandomPatchFeature.class)
public class RandomPatchFeatureMixin {
    @Inject(method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z", at = @At("HEAD"), cancellable = true)
    public void place(FeaturePlaceContext<RandomPatchConfiguration> context, CallbackInfoReturnable<Boolean> cir) {
        if (!NoLitteringConfig.getActive().disableLitterInWorldGeneration) {
            return;
        }

        final var randomPatchFeature = context.config().feature().value();
        final var innerFeature = randomPatchFeature.feature().value();
        final var innerFeatureConfig = innerFeature.config();
        if (innerFeatureConfig instanceof SimpleBlockConfiguration simpleBlockConfig) {
            if (simpleBlockConfig.toPlace() instanceof WeightedStateProviderAccessor weightedStateProvider) {
                final var weightedStates = weightedStateProvider.getWeightedList().unwrap();
                for (final var weightedState : weightedStates) {
                    if (weightedState.value().is(Blocks.LEAF_LITTER)) {
                        cir.setReturnValue(false);
                        return;
                    }
                }
            }
        }
    }
}
