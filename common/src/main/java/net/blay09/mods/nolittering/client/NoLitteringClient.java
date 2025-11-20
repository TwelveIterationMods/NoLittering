package net.blay09.mods.nolittering.client;

import net.blay09.mods.balm.client.platform.module.BalmClientModule;
import net.blay09.mods.nolittering.NoLittering;
import net.minecraft.resources.Identifier;

public class NoLitteringClient implements BalmClientModule {
    @Override
    public Identifier getId() {
        return Identifier.fromNamespaceAndPath(NoLittering.MOD_ID, "client");
    }
}
