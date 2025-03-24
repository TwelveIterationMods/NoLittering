package net.blay09.mods.nolittering;

import net.blay09.mods.balm.api.config.BalmConfig;
import net.blay09.mods.balm.api.event.BalmEvents;
import net.blay09.mods.balm.api.event.TickPhase;
import net.blay09.mods.balm.api.event.TickType;
import net.blay09.mods.balm.api.module.BalmModule;
import net.blay09.mods.balm.api.network.BalmNetworking;
import net.blay09.mods.nolittering.mixin.ServerPlayerGameModeAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeafLitterBlock;
import net.minecraft.world.level.block.SegmentableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Optional;

public class NoLittering implements BalmModule {
    public static final String MOD_ID = "nolittering";

    private static final RandomSource random = RandomSource.create();

    private static void trySpawnLitterAround(ServerLevel level, BlockPos pos) {
        final var range = 2;
        final var offsetX = random.nextInt(range * 2 + 1) - range;
        final var offsetZ = random.nextInt(range * 2 + 1) - range;
        final var offsetPos = pos.offset(offsetX, 0, offsetZ);
        findLocalGroundForLitter(level, offsetPos).ifPresent(groundPos -> getRandomLeafLitter(level, groundPos).ifPresent(leafLitter -> {
            level.sendParticles(ParticleTypes.POOF, groundPos.getX() + 0.5f, groundPos.getY() + 0.5f, groundPos.getZ() + 0.5f, 1, 0, 0, 0, 0);
            level.setBlockAndUpdate(groundPos, leafLitter);
        }));
    }

    private static Optional<BlockState> getRandomLeafLitter(Level level, BlockPos pos) {
        final var state = level.getBlockState(pos);
        if (state.is(Blocks.LEAF_LITTER)) {
            final int count = state.getValue(SegmentableBlock.AMOUNT);
            if (count < SegmentableBlock.MAX_SEGMENT) {
                return Optional.of(state.setValue(SegmentableBlock.AMOUNT, count + 1));
            } else {
                return Optional.empty();
            }
        } else if (state.isAir()) {
            final var facing = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            return Optional.of(Blocks.LEAF_LITTER.defaultBlockState().setValue(LeafLitterBlock.FACING, facing));
        }
        return Optional.empty();
    }

    private static Optional<BlockPos> findLocalGroundForLitter(Level level, BlockPos pos) {
        final var heightmapPos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos);
        final var verticalRange = 5;

        // If the heightmap position is close enough, use it directly
        if (Math.abs(heightmapPos.getY() - pos.getY()) < verticalRange) {
            return Optional.of(heightmapPos);
        }

        // Otherwise, we're likely underground or roofed
        var currentPos = pos.mutable();
        var currentState = level.getBlockState(currentPos);
        if (currentState.is(Blocks.LEAF_LITTER)) {
            return Optional.of(currentPos);
        }

        var belowPos = currentPos.mutable().setY(currentPos.getY() - 1);
        var belowState = level.getBlockState(belowPos);
        if (currentState.isAir()) {
            if (belowState.isFaceSturdy(level, belowPos, Direction.UP)) {
                return Optional.of(currentPos);
            }
            for (int i = 0; i < verticalRange; i++) {
                currentPos.setY(currentPos.getY() - 1);
                currentState = level.getBlockState(currentPos);
                if (currentState.is(Blocks.LEAF_LITTER)) {
                    return Optional.of(currentPos);
                } else if (!currentState.isAir()) {
                    return Optional.empty();
                } else {
                    belowPos.setY(currentPos.getY() - 1);
                    belowState = level.getBlockState(belowPos);
                    if (belowState.isFaceSturdy(level, belowPos, Direction.UP)) {
                        return Optional.of(currentPos);
                    }
                }
            }
        } else {
            for (int i = 0; i < verticalRange; i++) {
                currentPos.setY(currentPos.getY() + 1);
                currentState = level.getBlockState(currentPos);
                if (currentState.is(Blocks.LEAF_LITTER)) {
                    return Optional.of(currentPos);
                } else if (currentState.isAir()) {
                    belowPos.setY(currentPos.getY() - 1);
                    belowState = level.getBlockState(belowPos);
                    if (belowState.isFaceSturdy(level, belowPos, Direction.UP)) {
                        return Optional.of(currentPos);
                    } else {
                        return Optional.empty();
                    }
                }
            }
        }

        return Optional.empty();
    }

    private static boolean isTree(Level level, BlockPos pos) {
        final var rootState = level.getBlockState(pos);
        if (!rootState.is(BlockTags.LOGS)) {
            return false;
        }

        var current = pos.above();
        while (level.isInsideBuildHeight(current.getY())) {
            var state = level.getBlockState(current);
            if (state.is(BlockTags.LEAVES)) {
                return true;
            }
            if (!state.is(BlockTags.LOGS)) {
                return false;
            }
            current = current.above();
        }
        return false;
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    @Override
    public ResourceLocation getId() {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, "common");
    }

    @Override
    public void registerConfig(BalmConfig config) {
        config.registerConfig(NoLitteringConfig.class);
    }

    @Override
    public void registerEvents(BalmEvents events) {
        events.onTickEvent(TickType.ServerPlayer, TickPhase.Start, player -> {
            if (NoLitteringConfig.getActive().punchingTreesCreatesLitter) {
                if (player.gameMode instanceof ServerPlayerGameModeAccessor accessor) {
                    if (accessor.getIsDestroyingBlock()) {
                        final var level = player.level();
                        final var pos = accessor.getDestroyPos();
                        if (level instanceof ServerLevel serverLevel && isTree(level, pos)) {
                            final var chancePerSecond = NoLitteringConfig.getActive().punchingLitterChance;
                            final var chancePerTick = chancePerSecond / 20f;
                            if (random.nextFloat() < chancePerTick) {
                                trySpawnLitterAround(serverLevel, pos);
                            }
                        }
                    }
                }
            }
        });
    }

}
