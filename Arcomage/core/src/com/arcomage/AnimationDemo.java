package com.arcomage;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationDemo extends ApplicationAdapter {
    private static final int FRAME_COLS = 2, FRAME_ROWS = 2;

    Animation<TextureRegion> animation;
    Texture animationTexture;
    SpriteBatch spriteBatch;

    float elapsedTime;

    float x = 0;

    @Override
    public void create() {
        animationTexture = new Texture(Gdx.files.internal("fireball.png"));

        TextureRegion[][] tmp = TextureRegion.split(animationTexture,
            animationTexture.getWidth() / FRAME_COLS,
            animationTexture.getHeight() / FRAME_ROWS);

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        animation = new Animation<TextureRegion>(0.10f, walkFrames);
        spriteBatch = new SpriteBatch();
        elapsedTime = 0f;

        x = Gdx.graphics.getWidth() - animationTexture.getWidth() / FRAME_COLS;
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += Gdx.graphics.getDeltaTime();

        TextureRegion currentFrame = animation.getKeyFrame(elapsedTime, true);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, x, 50);
        spriteBatch.end();

        x -= 5;

        if (x < -animationTexture.getWidth()) {
            x = Gdx.graphics.getWidth();
        }
    }

    @Override
    public void dispose() {
        // SpriteBatches and Textures must always be disposed
        spriteBatch.dispose();
        animationTexture.dispose();
    }
}
