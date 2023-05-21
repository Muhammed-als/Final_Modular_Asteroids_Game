package dk.sdu.mmmi.cbse.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("ProcessService")
public class ProcessService {
    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return SPILocator.locateAll(IEntityProcessingService.class);
    }
    public void processServices(GameData gameData, World world){
        for (IEntityProcessingService iEntityProcessingService : getEntityProcessingServices()) {
            iEntityProcessingService.process(gameData,world);
        }
    }
}
