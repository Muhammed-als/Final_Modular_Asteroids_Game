package dk.sdu.mmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

//public interface IBulletCreator {
//    Entity createBullet(Entity shooter, GameData gameData);
//}

public interface BulletSPI {
    Entity createBullet(Entity e, GameData gameData);
}