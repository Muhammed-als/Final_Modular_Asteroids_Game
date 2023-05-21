package dk.sdu.mmmi.cbse;

import dk.sdu.mmi.cbse.common.bullet.Bullet;
import dk.sdu.mmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
//import dk.sdu.mmmi.cbse.playersystem.Player;
@SuppressWarnings("unchecked")
public class BulletPlugin implements IGamePluginService {

    private Entity bullet;

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities(Bullet.class)) {
                world.removeEntity(e);
        }
    }
}
