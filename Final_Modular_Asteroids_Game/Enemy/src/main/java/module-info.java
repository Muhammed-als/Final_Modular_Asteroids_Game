import dk.sdu.mmmi.cbse.enemysystem.EnemyControlSystem;
import dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;

module Enemy {
    requires Common;
    requires com.badlogic.gdx;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;

    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with EnemyControlSystem;
    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with EnemyPlugin;
}