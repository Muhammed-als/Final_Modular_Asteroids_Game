package dk.sdu.mmmi.cbse.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("PluginService")
public class PluginService {
    private Collection<? extends IGamePluginService> getPluginServices() {
        return SPILocator.locateAll(IGamePluginService.class);
    }

    public void startPlugin(GameData gameData, World world){
        for (IGamePluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(gameData,world);
        }
    }

}
