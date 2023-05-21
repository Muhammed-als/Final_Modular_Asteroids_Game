module Asteroid {
    requires Common;
    requires CommonAsteroids;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmi.cbse.asteroid.AsteroidPlugin;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmi.cbse.asteroid.AsteroidProcessor;
}