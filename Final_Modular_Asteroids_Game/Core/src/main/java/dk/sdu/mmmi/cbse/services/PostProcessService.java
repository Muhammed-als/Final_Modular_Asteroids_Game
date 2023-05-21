package dk.sdu.mmmi.cbse.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("PostProcessService")
public class PostProcessService {
    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return SPILocator.locateAll(IPostEntityProcessingService.class);
    }
    public void processServices(GameData gameData, World world){
        for (IPostEntityProcessingService iPostEntityProcessingService : getPostEntityProcessingServices()) {
            iPostEntityProcessingService.process(gameData,world);
        }
    }

}
