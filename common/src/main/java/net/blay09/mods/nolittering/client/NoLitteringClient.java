package net.blay09.mods.nolittering.client;

import net.blay09.mods.balm.api.client.module.BalmClientModule;
import net.blay09.mods.nolittering.NoLittering;
import net.minecraft.resources.ResourceLocation;

public class NoLitteringClient implements BalmClientModule {
    @Override
    public ResourceLocation getId() {
        return ResourceLocation.fromNamespaceAndPath(NoLittering.MOD_ID, "client");
    }
}
