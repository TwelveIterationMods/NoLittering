package net.blay09.mods.nolittering.neoforge;

import net.blay09.mods.balm.Balm;
import net.blay09.mods.balm.neoforge.platform.runtime.NeoForgeLoadContext;
import net.blay09.mods.nolittering.NoLittering;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(NoLittering.MOD_ID)
public class NeoForgeNoLittering {

    public NeoForgeNoLittering(ModContainer modContainer, IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modContainer, modEventBus);
        Balm.initializeMod(NoLittering.MOD_ID, context, new NoLittering());
    }
}
