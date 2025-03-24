package net.blay09.mods.nolittering;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.config.reflection.Comment;
import net.blay09.mods.balm.api.config.reflection.Config;

@Config(NoLittering.MOD_ID)
public class NoLitteringConfig {

    @Comment("Whether to prevent leaf litter from generating in the world.")
    public boolean disableLitterInWorldGeneration = true;

    @Comment("Whether punching trees should spawn litter around it.")
    public boolean punchingTreesCreatesLitter = true;

    @Comment("The chance to spawn litter while punching trees, per second.")
    public float punchingLitterChance = 1f;

    public static NoLitteringConfig getActive() {
        return Balm.getConfig().getActiveConfig(NoLitteringConfig.class);
    }
}
