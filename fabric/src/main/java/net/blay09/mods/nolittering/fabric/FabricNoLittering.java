package net.blay09.mods.nolittering.fabric;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.EmptyLoadContext;
import net.blay09.mods.nolittering.NoLittering;
import net.fabricmc.api.ModInitializer;

public class FabricNoLittering implements ModInitializer {
    @Override
    public void onInitialize() {
        Balm.initializeMod(NoLittering.MOD_ID, EmptyLoadContext.INSTANCE, new NoLittering());
    }
}
