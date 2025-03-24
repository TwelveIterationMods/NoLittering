package net.blay09.mods.nolittering.neoforge.client;

import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.neoforge.NeoForgeLoadContext;
import net.blay09.mods.nolittering.NoLittering;
import net.blay09.mods.nolittering.client.NoLitteringClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = NoLittering.MOD_ID, dist = Dist.CLIENT)
public class NeoForgeNoLitteringClient {

    public NeoForgeNoLitteringClient(IEventBus modEventBus) {
        final var context = new NeoForgeLoadContext(modEventBus);
        BalmClient.initializeMod(NoLittering.MOD_ID, context, new NoLitteringClient());
    }
}
