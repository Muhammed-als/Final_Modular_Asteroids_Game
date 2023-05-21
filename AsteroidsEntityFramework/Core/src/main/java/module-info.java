import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Core {
    requires Common;
    requires java.desktop;
    requires com.badlogic.gdx;
    requires spring.context;

    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;

    exports dk.sdu.mmmi.cbse.services;
    exports dk.sdu.mmmi.cbse.main;
}