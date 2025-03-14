package net.blay09.mods.nolittering.fabric;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.EmptyLoadContext;
import net.fabricmc.api.ModInitializer;
import net.blay09.mods.nolittering.NoLittering;

public class FabricNoLittering implements ModInitializer {
    @Override
    public void onInitialize() {
        Balm.initialize(NoLittering.MOD_ID, EmptyLoadContext.INSTANCE, NoLittering::initialize);
    }
}
