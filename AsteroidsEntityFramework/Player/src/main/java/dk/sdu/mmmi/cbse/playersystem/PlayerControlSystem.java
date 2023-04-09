package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;

/**
 *
 * @author jcs
 */
@SuppressWarnings("unchecked")
public class PlayerControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);
            LifePart lifePart = player.getPart(LifePart.class);

            movingPart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT));
            movingPart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT));
            movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP));

            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                for (BulletSPI bullet : SPILocator.locateAll(BulletSPI.class)) {
                    world.addEntity(bullet.createBullet(player, gameData));
                    gameData.getKeys().setKey(GameKeys.SPACE,false);
                }
            }

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);
            lifePart.process(gameData, player);

            updateShape(player);

        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = new float[4];
        float[] shapey = new float[4];
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * entity.getRadius());
        shapey[0] = (float) (y + Math.sin(radians) * entity.getRadius());

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * entity.getRadius());
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * entity.getRadius());

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * entity.getRadius() * 0.5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * entity.getRadius() * 0.5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * entity.getRadius());
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * entity.getRadius());

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
//public class PlayerControlSystem implements IEntityProcessingService {
//    private boolean shoot;
//
//    @Override
//    public void process(GameData gameData, World world) {
//
//        for (Entity player : world.getEntities(Player.class)) {
//            PositionPart positionPart = player.getPart(PositionPart.class);
//            MovingPart movingPart = player.getPart(MovingPart.class);
//            LifePart lifePart = player.getPart(LifePart.class);
//
//            movingPart.setLeft(gameData.getKeys().isDown(LEFT));
//            movingPart.setRight(gameData.getKeys().isDown(RIGHT));
//            movingPart.setUp(gameData.getKeys().isDown(UP));
//
//            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
//                Collection<BulletSPI> bulletPlugins = SPILocater.locateAll(BulletSPI.class);
//
//                for (BulletSPI bulletPlugin : bulletPlugins) {
//                    System.out.println(true);
//
//                    world.addEntity(bulletPlugin.createBullet(player, gameData));
//                }
//            }
//
//            movingPart.process(gameData, player);
//            positionPart.process(gameData, player);
//            lifePart.process(gameData,player);
//
//            updateShape(player);
//        }
//    }
//
//
//    private void updateShape(Entity entity) {
//        float[] shapex = entity.getShapeX();
//        float[] shapey = entity.getShapeY();
//        PositionPart positionPart = entity.getPart(PositionPart.class);
//        float x = positionPart.getX();
//        float y = positionPart.getY();
//        float radians = positionPart.getRadians();
//
//        shapex[0] = (float) (x + Math.cos(radians) * 8);
//        shapey[0] = (float) (y + Math.sin(radians) * 8);
//
//        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
//        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);
//
//        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
//        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);
//
//        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
//        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);
//
//        entity.setShapeX(shapex);
//        entity.setShapeY(shapey);
//    }
//
//}
