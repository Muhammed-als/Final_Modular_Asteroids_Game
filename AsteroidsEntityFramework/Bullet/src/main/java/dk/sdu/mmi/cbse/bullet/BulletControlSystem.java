package main.java.dk.sdu.mmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.playersystem.Player;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;
import java.util.Optional;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

@ServiceProviders(value = {
        @ServiceProvider(service = IEntityProcessingService.class)})
@SuppressWarnings("unchecked")
public class BulletControlSystem implements IEntityProcessingService {
    private boolean showBullet;
    private boolean isActivated;
//    private float x;
//    private float y;
    int bulletDistanceTraveled = 0;
    @Override
    public void process(GameData gameData, World world) {
        Optional<Entity> player = world.getEntities(Player.class).stream().findFirst();
        Optional<Entity> bullet = world.getEntities(Bullet.class).stream().findFirst();
        LifePart lifePart = bullet.get().getPart(LifePart.class);
        if(player.isPresent()) {
            PositionPart bulletPosition = bullet.get().getPart(PositionPart.class);
            MovingPart bulletmovingPart = bullet.get().getPart(MovingPart.class);
            PositionPart playerPosition = player.get().getPart(PositionPart.class);
            float x = playerPosition.getX();
            float y = playerPosition.getY();
            if(lifePart.isIsHit()){
                showBullet = false;
                lifePart.setIsHit(false);
                bulletDistanceTraveled = 0;
            }
            if((gameData.getKeys().isPressed(GameKeys.SPACE) && bulletDistanceTraveled == 0)){
                showBullet = true;


                bulletPosition.setRadians(playerPosition.getRadians());

            }

            if (showBullet) {
                x = bulletPosition.getX();
                y = bulletPosition.getY();
                x += (float) (3 * cos(bulletPosition.getRadians()));
                y += (float) (3 * sin(bulletPosition.getRadians()));
                bulletDistanceTraveled += 3;
            }
            if (bulletDistanceTraveled >= 200) {
                showBullet = false;
                bulletDistanceTraveled = 0;
                x = playerPosition.getX();
                y = playerPosition.getY();
            }
            bulletPosition.setPosition(x, y);
            bulletmovingPart.process(gameData, bullet.get());
            bulletPosition.process(gameData, bullet.get());
            updateShape(bullet.get());
        }
        else{
            showBullet = false;
            bullet.ifPresent(this::updateShape);
        }
    }

    private void updateShape(Entity entity) {
            float[] shapeX = new float[10];
            float[] shapeY = new float[10];
            PositionPart positionPart = entity.getPart(PositionPart.class);
            float radians = positionPart.getRadians();
            for (int i = 0; i < shapeX.length; i++) {
                shapeX[i] = (float) (positionPart.getX() + entity.getRadius() * cos(radians + i * 4 * Math.PI/16));
                shapeY[i] = (float) (positionPart.getY() + entity.getRadius() * sin(radians + i * 4 *  Math.PI/16));
                entity.setShapeX(shapeX);
                entity.setShapeY(shapeY);
            }
        if(!showBullet){
            entity.setShapeX(new float[0]);
            entity.setShapeY(new float[0]);
        }

    }
}
