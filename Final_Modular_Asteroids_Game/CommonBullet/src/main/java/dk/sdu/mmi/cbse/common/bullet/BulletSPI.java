package dk.sdu.mmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public interface BulletSPI {
    Entity createBullet(Entity e, GameData gameData);
}