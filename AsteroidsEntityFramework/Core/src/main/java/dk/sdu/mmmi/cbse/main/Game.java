package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmi.cbse.collisionSystem.*;
import dk.sdu.mmi.cbse.enemysystem.Enemy;
import dk.sdu.mmi.cbse.enemysystem.EnemyControlSystem;
import dk.sdu.mmi.cbse.enemysystem.EnemyPlugin;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocater;
import dk.sdu.mmmi.cbse.managers.GameInputProcessor;
import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
import dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
import dk.sdu.mmi.cbse.asteroid.AsteroidControlSystem;
import dk.sdu.mmi.cbse.asteroid.AsteroidPlugin;
import main.java.dk.sdu.mmi.cbse.bullet.BulletControlSystem;
import main.java.dk.sdu.mmi.cbse.bullet.BulletPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Game
        implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;

    private final GameData gameData = new GameData();
    private List<IEntityProcessingService> entityProcessors = new ArrayList<>();
    private List<IGamePluginService> entityPlugins = new ArrayList<>();
    private List<IPostEntityProcessingService> iPostEntityProcessingServices = new ArrayList<>();
    private World world = new World();

    @Override
    public void create() {
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );
//        for (IGamePluginService pluginService : getPluginServices() ){
//            pluginService.start(gameData,world);
//        }
        IGamePluginService playerPlugin = new PlayerPlugin();
        IGamePluginService enemyPlugin = new EnemyPlugin();
        IGamePluginService asteroidPlugin = new AsteroidPlugin();
        IGamePluginService bulletPlugin = new BulletPlugin();

        IEntityProcessingService playerProcess = new PlayerControlSystem();
        IEntityProcessingService enemyProcess = new EnemyControlSystem();
        IEntityProcessingService asteroidProcess = new AsteroidControlSystem();
        IEntityProcessingService bulletProcess = new BulletControlSystem();
        IPostEntityProcessingService collisionProcess = new CollisionDetector();
        entityPlugins.add(playerPlugin);
        entityPlugins.add(enemyPlugin);
        entityPlugins.add(asteroidPlugin);
        entityPlugins.add(bulletPlugin);
        entityProcessors.add(playerProcess);
        entityProcessors.add(enemyProcess);
        entityProcessors.add(asteroidProcess);
        entityProcessors.add(bulletProcess);
        iPostEntityProcessingServices.add(collisionProcess);



        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : entityPlugins) {
            iGamePlugin.start(gameData, world);
        }
//        for(IPostEntityProcessingService iPostEntityProcessingService : iPostEntityProcessingServices){
//            iPostEntityProcessingService.process(gameData,world);
//        }

    }

    @Override
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        draw();

        gameData.getKeys().update();
    }

    private void update() {
        // Update
        for (IEntityProcessingService entityProcessorService : entityProcessors) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessingService : iPostEntityProcessingServices) {
            postEntityProcessingService.process(gameData, world);
        }
    }

    private void draw() {

        for (Entity entity : world.getEntities()) {
            sr.setColor(Color.WHITE);
            if (entity.getClass().equals(Enemy.class)){
                sr.setColor(Color.RED);
            }


            sr.begin(ShapeRenderer.ShapeType.Line);

            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                    i < shapex.length;
                    j = i++) {

                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
            }

            sr.end();
        }
    }
    private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return SPILocater.locateAll(IPostEntityProcessingService.class);
    }
    private Collection<? extends IGamePluginService> getPluginServices() {
        return SPILocater.locateAll(IGamePluginService.class);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
