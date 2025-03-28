package net.blay09.mods.nolittering.forge.client;

import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.nolittering.client.NoLitteringClient;

public class ForgeNoLitteringClient {

    public static void initialize() {
        BalmClient.registerModule(new NoLitteringClient());
    }

}
