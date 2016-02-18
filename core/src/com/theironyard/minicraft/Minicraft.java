package com.theironyard.minicraft;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Minicraft extends ApplicationAdapter {
    final int WIDTH = 100;
    final int HEIGHT = 100;
    float xv, yv;
    static final float MAX_VELOCITY = 100;


    SpriteBatch batch;
    TextureRegion down, up, right, left;

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
        left = new TextureRegion(right);
        left.flip(true, false);




    }

    @Override
    public void render () {

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
            x = Gdx.graphics.getWidth()-WIDTH;
        }
        else if (x > Gdx.graphics.getWidth()-WIDTH) {
            x = 0;
        }
        if (y < 0) {
            y = Gdx.graphics.getHeight()-HEIGHT;
        } else if (y > Gdx.graphics.getHeight()-HEIGHT) {
           y = 0;
        }


        //if the dude's x is > Gdx.graphics.getWidth, stop him
        //same for y
        //you could stop his velocity
        // we have his x and y at all times





        TextureRegion img;
        img = down;
    if (Math.abs(yv) > Math.abs(xv)) {
        if (yv >= 0) {
            img = up;
        } else {
            img = down;
        }
    } else {
        if (xv >= 0) {
            img = right;
        } else {
            img = left;
        }
    }


        Gdx.gl.glClearColor(0, 0.5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(img, x, y, WIDTH, HEIGHT);
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

}
