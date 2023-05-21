package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.EntityType;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class CollisionDetectorTest {

    private CollisionDetector collisionDetector;
    private GameData gameData;
    private World world;
    @Before
    public void setUp() {
        collisionDetector = Mockito.mock(CollisionDetector.class);
        gameData = mock(GameData.class);
        world = mock(World.class);
        Entity mockEntity1 = mock(Entity.class);
        PositionPart mockEntity1PositionPart = mock(PositionPart.class);
        LifePart mockEntity1LifePart = mock(LifePart.class);

        when(mockEntity1.getType()).thenReturn(EntityType.PLAYER);
        when(mockEntity1.getRadius()).thenReturn(1.0f);
        when(mockEntity1PositionPart.getX()).thenReturn(1.0f);
        when(mockEntity1PositionPart.getY()).thenReturn(1.0f);
        when(mockEntity1.getPart(PositionPart.class)).thenReturn(mockEntity1PositionPart);
        when(mockEntity1LifePart.getLife()).thenReturn(1);
        when(mockEntity1.getPart(LifePart.class)).thenReturn(mockEntity1LifePart);

        Entity mockEntity2 = mock(Entity.class);
        PositionPart mockEntity2PositionPart = mock(PositionPart.class);
        LifePart mockEntity2LifePart = mock(LifePart.class);

        when(mockEntity2.getType()).thenReturn(EntityType.ASTEROID);
        when(mockEntity2.getRadius()).thenReturn(1.0f);
        when(mockEntity2PositionPart.getX()).thenReturn(1.0f);
        when(mockEntity2PositionPart.getY()).thenReturn(1.0f);
        when(mockEntity1.getPart(PositionPart.class)).thenReturn(mockEntity2PositionPart);
        when(mockEntity2LifePart.getLife()).thenReturn(1);
        when(mockEntity1.getPart(LifePart.class)).thenReturn(mockEntity2LifePart);

        world.addEntity(mockEntity1);
        world.addEntity(mockEntity2);
    }
    @Test
    public void TestProcess() {
        collisionDetector.process(gameData, world);
        verify(collisionDetector);
    }
//    @Test
//    public void testCollides() {
//        collisionDetector.collides(mockEntity1,mockEntity2);
//    }
}