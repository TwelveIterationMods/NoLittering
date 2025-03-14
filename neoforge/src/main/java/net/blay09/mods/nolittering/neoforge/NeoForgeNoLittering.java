package net.blay09.mods.nolittering.neoforge;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.neoforge.NeoForgeLoadContext;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.blay09.mods.nolittering.NoLittering;

@Mod(NoLittering.MOD_ID)
public class NeoForgeNoLittering {

    public NeoForgeNoLittering(IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modEventBus);
        Balm.initialize(NoLittering.MOD_ID, context, NoLittering::initialize);
    }
}
