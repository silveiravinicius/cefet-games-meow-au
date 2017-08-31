/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.games.minigames;

import br.cefetmg.games.Animals.Cat;
import br.cefetmg.games.Animals.Dog;
import br.cefetmg.games.minigames.util.DifficultyCurve;
import br.cefetmg.games.minigames.util.MiniGameStateObserver;
import br.cefetmg.games.minigames.util.TimeoutBehavior;
import br.cefetmg.games.screens.BaseScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer.Task;

/**
 *
 * @author Pedro e Arthur
 * ouçam https://www.youtube.com/watch?v=Gfw4yxn_kPQ
 */
public class DogBarksCatFlee extends MiniGame {
    private final int TILES_COUNT = 20;
    private Dog player;
    private Texture DogTexture;
    private Texture CatTexture;
    private Array<Cat> enemies;
    private Array<Vector2> tilesVector; // Tem que criar 1 tipo tile que tem Vector2 e um int para representar qual é o 
    private Vector2 PosicaoInicial;
    private Texture tileTexture[] = new Texture[5];
    private float spawnInterval;
    private float minimumEnemySpeed;
    private float maximumEnemySpeed;
    
    public DogBarksCatFlee(BaseScreen screen, MiniGameStateObserver observer, float difficulty) {
        super(screen, observer, difficulty, 10f, TimeoutBehavior.WINS_WHEN_MINIGAME_ENDS);
    }
    
    private void TilesDraw(){
        for (Vector2 tile : tilesVector) {
            batch.draw(tileTexture[0], tile.x, tile.y);
           // batch.draw(tileTexture[MathUtils.random(4)], tile.x, tile.y);
        }
    }
    
    private void UpdateDraw(float dt){
        super.update(dt);
        for (Vector2 tile : tilesVector) {
            tile.x += -0.5; 
            if(tile.x <= 0- tileTexture[0].getWidth()) // ainda não definido o num;
                tile.x = 500; // ainda não definido o num;
        }
    }
    

    
    @Override
    protected void onStart() {

        PosicaoInicial = new Vector2 (1,1);
        DogTexture = assets.get("DogBarksCatFlee/dog1.png", Texture.class);
        
        CatTexture = assets.get("DogBarksCatFlee/dog1.png", Texture.class);
        enemies = new Array<Cat>();
        //DogAnimation = assets.get(null);
//        //<editor-fold defaultstate="collapsed" desc="texturas tile">
        tileTexture[0] = assets.get("DogBarksCatFlee/tile0.png", Texture.class);
        tileTexture[1] = assets.get("DogBarksCatFlee/tile1.png", Texture.class);
        tileTexture[2] = assets.get("DogBarksCatFlee/tile2.png", Texture.class);
        tileTexture[3] = assets.get("DogBarksCatFlee/tile3.png", Texture.class);
        tileTexture[4] = assets.get("DogBarksCatFlee/tile4.png", Texture.class);
//        //</editor-fold>
        tilesVector = new Array<Vector2>();
        
        
        for (int i =0 ; i< TILES_COUNT ;i++ ) {
            tilesVector.add(new Vector2(PosicaoInicial.x + i * tileTexture[0].getWidth(), PosicaoInicial.y + 40));
        }
        inicializeDog();
        timer.scheduleTask(new Task() {
            @Override
            public void run() {
                spawnEnemy();
            }
        }, 0, this.spawnInterval);    
    }
    
    private void spawnEnemy () {

//        Vector2 CatPosition = new Vector2(viewport.getWorldWidth(), viewport.getWorldHeight());
//        Cat enemy = new Cat(ScareThereshold(), CatPosition, CatTexture);
//        enemies.add(enemy);

    }
    private void inicializeDog(){
        TextureRegion Dog = new TextureRegion(DogTexture);
        player = new Dog (3, PosicaoInicial, Dog);
    }
    
    private int ScareThereshold (){
        return MathUtils.random(10, 30);
    }

    @Override
    protected void configureDifficultyParameters(float difficulty) {
        this.minimumEnemySpeed = DifficultyCurve.LINEAR
                .getCurveValueBetween(difficulty, 120, 220);
        this.maximumEnemySpeed = DifficultyCurve.LINEAR
                .getCurveValueBetween(difficulty, 240, 340);
        this.spawnInterval = DifficultyCurve.LINEAR_NEGATIVE
                .getCurveValueBetween(difficulty, 0.25f, 1.5f);
  }

    @Override
    public void onHandlePlayingInput() {
    }

    @Override
    public void onUpdate(float dt) {
        UpdateDraw(dt);
        // player.update(dt);
    }

    
    @Override
    public void onDrawGame() {
        TilesDraw();
    }

    @Override
    public String getInstructions() {
        return "RAWR RAWR";
    }

    @Override
    public boolean shouldHideMousePointer() {
        return false;
    }
    
}
