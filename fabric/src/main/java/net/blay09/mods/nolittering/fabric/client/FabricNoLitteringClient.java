package net.blay09.mods.nolittering.fabric.client;

import net.blay09.mods.balm.client.BalmClient;
import net.blay09.mods.balm.fabric.platform.runtime.FabricLoadContext;
import net.blay09.mods.nolittering.NoLittering;
import net.blay09.mods.nolittering.client.NoLitteringClient;
import net.fabricmc.api.ClientModInitializer;

public class FabricNoLitteringClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BalmClient.initializeMod(NoLittering.MOD_ID, FabricLoadContext.INSTANCE, new NoLitteringClient());
    }
}
