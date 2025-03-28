package net.blay09.mods.nolittering.forge;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.balm.api.client.BalmClient;
import net.blay09.mods.balm.forge.ForgeLoadContext;
import net.blay09.mods.nolittering.NoLittering;
import net.blay09.mods.nolittering.forge.client.ForgeNoLitteringClient;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(NoLittering.MOD_ID)
public class ForgeNoLittering {

    public ForgeNoLittering(FMLJavaModLoadingContext context) {
        final var loadContext = new ForgeLoadContext(context.getModEventBus());
        Balm.initializeMod(NoLittering.MOD_ID, loadContext, new NoLittering());
        if (FMLEnvironment.dist.isClient()) {
            BalmClient.initializeMod(NoLittering.MOD_ID, loadContext, ForgeNoLitteringClient::initialize);
        }
    }

}
