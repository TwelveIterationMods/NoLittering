package net.blay09.mods.nolittering.mixin;

import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(WeightedStateProvider.class)
public interface WeightedStateProviderAccessor {
    @Accessor
    WeightedList<BlockState> getWeightedList();
}
