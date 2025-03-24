package net.blay09.mods.nolittering.fabric.client;

import net.blay09.mods.balm.api.EmptyLoadContext;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.nolittering.NoLittering;
import net.blay09.mods.nolittering.client.NoLitteringClient;
import net.fabricmc.api.ClientModInitializer;

public class FabricNoLitteringClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BalmClient.initializeMod(NoLittering.MOD_ID, EmptyLoadContext.INSTANCE, new NoLitteringClient());
    }
}
