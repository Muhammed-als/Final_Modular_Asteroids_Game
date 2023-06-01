package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;


@SuppressWarnings("unchecked")
public class EnemyControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for(Entity enemy: world.getEntities(Enemy.class)){
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);
            float x = positionPart.getX();
            x++;
            positionPart.setX(x);
            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);
            lifePart.process(gameData, enemy);
            updateShape(enemy);
        }
    }
    private void updateShape(Entity entity) {
        float[] shapex = new float[8];
        float[] shapey = new float[8];
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians));
        shapey[0] = (float) (y + Math.sin(radians));

        shapex[1] = (float) (x + Math.cos(radians - 0.5f));
        shapey[1] = (float) (y + Math.sin(radians - 0.5f));

        shapex[2] = (float) (x + Math.cos(radians - 1.5f) * 15);
        shapey[2] = (float) (y + Math.sin(radians - 1.5f) * 15);

        shapex[3] = (float) (x + Math.cos(radians - 3.0f) * 20);
        shapey[3] = (float) (y + Math.sin(radians - 3.0f) * 20);

        shapex[4] = (float) (x + Math.cos(radians + 3.0f) * 20);
        shapey[4] = (float) (y + Math.sin(radians + 3.0f) * 20);

        shapex[5] = (float) (x + Math.cos(radians + 1.5f) * 15);
        shapey[5] = (float) (y + Math.sin(radians + 1.5f) * 15);

        shapex[6] = (float) (x + Math.cos(radians + 0.5f));
        shapey[6] = (float) (y + Math.sin(radians + 0.5f));

        shapex[7] = (float) (x + Math.cos(radians));
        shapey[7] = (float) (y + Math.sin(radians));

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
