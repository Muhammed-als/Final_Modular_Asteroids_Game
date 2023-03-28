package main.java.dk.sdu.mmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.Optional;
import java.util.Random;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class BulletPlugin implements IGamePluginService {
    private World world;
    private Entity bullet;


    @Override
    public void start(GameData gameData, World world) {

        this.world = world;
        bullet = createBullet(gameData);
        world.addEntity(bullet);




    }

    @Override
    public void stop(GameData gameData, World world) {
        for(Entity entity : world.getEntities(Bullet.class)){
            world.removeEntity(entity);
        }
    }

    private Entity createBullet(GameData gameData) {
        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 100;
        float rotationSpeed = 5;
        float x = 0;
        float y =0;
        Optional<Entity> positionPart = world.getEntities(Player.class).stream().findFirst();
        PositionPart part = positionPart.get().getPart(PositionPart.class);
        float dt = gameData.getDelta();
        float radians = part.getRadians();
        Entity bullet = new Bullet();
        bullet.setRadius(2);
        float bx = (float) cos(radians) * bullet.getRadius() * bullet.getRadius();
        float by = (float) sin(radians) * bullet.getRadius() * bullet.getRadius();
        bullet.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        bullet.add(new PositionPart(bx + x, by + y, radians));
        bullet.add(new LifePart(1));
        return bullet;
    }
}
