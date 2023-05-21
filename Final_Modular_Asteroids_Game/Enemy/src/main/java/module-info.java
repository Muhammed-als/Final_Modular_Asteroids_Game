module Enemy {
    requires Common;
    requires com.badlogic.gdx;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;

    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmi.cbse.enemysystem.EnemyControlSystem;
    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmi.cbse.enemysystem.EnemyPlugin;
}