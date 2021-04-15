package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BitmapFontScreen extends BaseScreen {

    BitmapFontScreen(MyGdxGame game) {
        super(game);
    }

    SpriteBatch spriteBatch;
    BitmapFont bitmapFont;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        bitmapFont = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.5f, 0.7f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        bitmapFont.getData().setScale(1);
        bitmapFont.setColor(new Color(1f, 0.2f, 0.2f, 1));
        bitmapFont.draw(spriteBatch, "lib", 160, 300);

        bitmapFont.getData().setScale(2);
        bitmapFont.setColor(new Color(0.2f, 1f, 0.2f, 1));
        bitmapFont.draw(spriteBatch, "GDX", 200f, 300f);

        bitmapFont.setColor(new Color(0.2f, 0.2f, 1f, 1));
        bitmapFont.getData().setScale(3);
        bitmapFont.draw(spriteBatch, String.valueOf(2021), 300f, 300f);

        spriteBatch.end();
    }
}