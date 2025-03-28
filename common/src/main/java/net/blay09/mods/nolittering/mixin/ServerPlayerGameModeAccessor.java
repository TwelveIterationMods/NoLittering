package net.blay09.mods.nolittering.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayerGameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerPlayerGameMode.class)
public interface ServerPlayerGameModeAccessor {

    @Accessor
    boolean getIsDestroyingBlock();

    @Accessor
    BlockPos getDestroyPos();
}
