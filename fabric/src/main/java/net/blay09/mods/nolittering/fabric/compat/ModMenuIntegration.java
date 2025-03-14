package net.blay09.mods.nolittering.fabric.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.blay09.mods.balm.fabric.compat.ModMenuUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.blay09.mods.nolittering.NoLitteringConfig;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ModMenuUtils.getConfigScreen(NoLitteringConfig.class);
    }
}
