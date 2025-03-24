package net.blay09.mods.nolittering.neoforge;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.neoforge.NeoForgeLoadContext;
import net.blay09.mods.nolittering.NoLittering;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NoLittering.MOD_ID)
public class NeoForgeNoLittering {

    public NeoForgeNoLittering(IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modEventBus);
        Balm.initializeMod(NoLittering.MOD_ID, context, new NoLittering());
    }
}
