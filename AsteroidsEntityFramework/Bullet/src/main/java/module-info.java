module Bullet {
    requires Common;
    requires CommonBullet;
    requires com.badlogic.gdx;

    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmi.cbse.common.bullet.BulletSPI;

    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmmi.cbse.BulletPlugin;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmmi.cbse.BulletControlSystem;
    provides dk.sdu.mmi.cbse.common.bullet.BulletSPI with dk.sdu.mmmi.cbse.BulletControlSystem;



}