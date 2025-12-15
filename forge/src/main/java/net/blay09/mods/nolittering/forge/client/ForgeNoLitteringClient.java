package net.blay09.mods.nolittering.forge.client;

import net.blay09.mods.balm.client.BalmClientRegistrars;
import net.blay09.mods.nolittering.client.NoLitteringClient;

public class ForgeNoLitteringClient {

    public static void initialize(BalmClientRegistrars registrars) {
        registrars.registerModule(new NoLitteringClient());
    }

}
