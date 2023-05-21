module Player {
    requires Common;
    requires CommonBullet;

    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmi.cbse.common.bullet.BulletSPI;

    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
}