module Bullet {
    requires Common;
    requires CommonBullet;
    requires com.badlogic.gdx;

    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.commonBullet.BulletSPI;

    provides dk.sdu.mmmi.cbse.common.services.IGamePluginService with dk.sdu.mmmi.cbse.Bullet.BulletPlugin;
    provides dk.sdu.mmmi.cbse.common.services.IEntityProcessingService with dk.sdu.mmmi.cbse.Bullet.BulletControlSystem;
    provides dk.sdu.mmmi.cbse.commonBullet.BulletSPI with dk.sdu.mmmi.cbse.Bullet.BulletControlSystem;



}