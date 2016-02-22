package com.theironyard.minicraft;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Minicraft extends ApplicationAdapter {
    final int WIDTH = 100;
    final int HEIGHT = 100;
    float xv, yv, time;
    static final float MAX_VELOCITY = 100;
    //Animation walkRight;
    //Animation walkLeft;
    Animation walkUp;
    Animation walkDown;
    Animation walkRight;
    Animation walkLeft;
    //float zombiexv, zombieyv;
    SpriteBatch batch;
    TextureRegion down, up, right, left, stand; //zombieStand;


    float x = 0;
    float y = 0;

    @Override
    public void create () {
        batch = new SpriteBatch();


        Texture tiles = new Texture("tiles.png");
        TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);
        down = grid[6][0];
        up = grid[6][1];
        right = grid[6][3];
        stand = grid[6][2];
        //zombieStand = grid[6][6];
        left = new TextureRegion(right);
        left.flip(true, false);
        TextureRegion upFlip = new TextureRegion(up);
        upFlip.flip(true, false); //shifts on x axis
        TextureRegion downFlip = new TextureRegion(down);
        walkUp = new Animation (0.1f,up, upFlip);
        downFlip.flip(true, false);
        walkDown = new Animation(0.1f, down, downFlip);
       TextureRegion standFlip = new TextureRegion(stand);
       standFlip.flip (true, false);
        walkRight = new Animation(0.1f, right, stand);
        walkLeft = new Animation(0.1f, left, standFlip);






    }

    @Override
    public void render () {
        time += Gdx.graphics.getDeltaTime();

        float oldX = x;
        float oldY = y;

        move();
        //to get dude to stop at edge:
        //if (x < 0 || x > (Gdx.graphics.getWidth()-WIDTH)) {
        // x = oldX;
        //}
        //if (y < 0 || y > (Gdx.graphics.getHeight()-HEIGHT)){
        //y = oldY;
        //}
        //to get dude to teleport:
        if (x < 0) {
            x = Gdx.graphics.getWidth() - WIDTH;
        } else if (x > Gdx.graphics.getWidth() - WIDTH) {
            x = 0;
        }
        if (y < 0) {
            y = Gdx.graphics.getHeight() - HEIGHT;
        } else if (y > Gdx.graphics.getHeight() - HEIGHT) {
            y = 0;
        }


        //if the dude's x is > Gdx.graphics.getWidth, stop him
        //same for y


        TextureRegion img;
        img = down;
        if (Math.abs(yv) > Math.abs(xv)) {
            if (yv > 0) {
                img = walkUp.getKeyFrame(time, true);
            } else {
                img = walkDown.getKeyFrame(time, true);
            }
        } else {
            if (xv > 0) {
                img = walkRight.getKeyFrame(time, true);
            } else if (xv == 0) {
                img = stand;
            } else {
                img = walkLeft.getKeyFrame(time, true);
            }


        }

        //change background color
        Gdx.gl.glClearColor(0.5f, 1.0f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(img, x, y, WIDTH, HEIGHT);
        //batch.draw(zombieStand, 100, 200, WIDTH, HEIGHT);
        batch.end();
    }

    void move() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yv = MAX_VELOCITY;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            yv = MAX_VELOCITY * -1;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xv = MAX_VELOCITY;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            xv = MAX_VELOCITY * -1;
        }


        y += yv *Gdx.graphics.getDeltaTime(); //delta time - amt of time since last frame ran. yv= # pixels movein 1 second
        x += xv * Gdx.graphics.getDeltaTime();
        xv = xv * 0.3f;
        yv = yv * 0.3f;


        //yv = decelerate(yv);
        //xv = decelerate(xv);
    }

    //if (Gdx.input.isKeyPressed(Input.Keys.N)) {
    //zombiexv = MAX_VELOCITY * -1;
}




