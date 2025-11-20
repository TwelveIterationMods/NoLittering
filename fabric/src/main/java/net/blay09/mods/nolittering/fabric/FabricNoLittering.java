package net.blay09.mods.nolittering.fabric;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.blay09.mods.nolittering.NoLittering;
import net.fabricmc.api.ModInitializer;

public class FabricNoLittering implements ModInitializer {
    @Override
    public void onInitialize() {
        Balm.initializeMod(NoLittering.MOD_ID, FabricLoadContext.INSTANCE, new NoLittering());
    }
}
