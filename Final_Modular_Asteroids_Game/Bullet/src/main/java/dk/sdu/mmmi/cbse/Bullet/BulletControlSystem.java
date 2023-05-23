package dk.sdu.mmmi.cbse.Bullet;
//
//import dk.sdu.mmi.cbse;
import com.badlogic.gdx.graphics.Color;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.commonBullet.Bullet;
import dk.sdu.mmmi.cbse.commonBullet.BulletSPI;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

@SuppressWarnings("unchecked")
public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {

            PositionPart positionPart = bullet.getPart(PositionPart.class);
            MovingPart movingPart = bullet.getPart(MovingPart.class);
            TimerPart timerPart = bullet.getPart(TimerPart.class);
            LifePart lifePart = bullet.getPart(LifePart.class);
            movingPart.setUp(true);
            if (timerPart.getElapsedTime() > timerPart.getRenderTime()) {
                world.removeEntity(bullet);
            }
            if(lifePart.isHit()){
                world.removeEntity(bullet);
            }
            timerPart.process(gameData, bullet);
            movingPart.process(gameData, bullet);
            positionPart.process(gameData, bullet);

            setShape(bullet);
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        PositionPart shooterPos = shooter.getPart(PositionPart.class);
        float x = shooterPos.getX();
        float y = shooterPos.getY();
        float radians = shooterPos.getRadians();
        float dt = gameData.getDelta();
        float speed = 350;

        Entity bullet = new Bullet();

        bullet.setRadius(5);

        float bx = (float) cos(radians) * shooter.getRadius() * bullet.getRadius();
        float by = (float) sin(radians) * shooter.getRadius() * bullet.getRadius();

        bullet.add(new PositionPart(bx + x, by + y, radians));
        bullet.add(new LifePart(1));
        bullet.add(new MovingPart(0, 5000000, speed, 5));
        bullet.add(new TimerPart(0,1));
        bullet.setType(EntityType.BULLET);
        bullet.setShapeX(new float[2]);
        bullet.setShapeY(new float[2]);
        bullet.setColor(Color.GREEN);
        return bullet;
    }

    private void setShape(Entity bullet) {
        PositionPart positionPart = bullet.getPart(PositionPart.class);
        float[] shapeX = bullet.getShapeX() ;
        float[] shapeY = bullet.getShapeY();
        float radians = positionPart.getRadians();
        for (int i = 0; i < shapeX.length; i++) {
            shapeX[i] = (float) (positionPart.getX() + bullet.getRadius() * cos(radians + i * 4 * Math.PI/16));
            shapeY[i] = (float) (positionPart.getY() + bullet.getRadius() * sin(radians + i * 4 *  Math.PI/16));
            bullet.setShapeX(shapeX);
            bullet.setShapeY(shapeY);
        }
        bullet.setShapeX(shapeX);
        bullet.setShapeY(shapeY);
    }

}
