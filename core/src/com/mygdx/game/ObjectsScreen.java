package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class ObjectsScreen extends BaseScreen {

    ObjectsScreen(MyGdxGame game) {
        super(game);
    }

    SpriteBatch spriteBatch;
    Texture background;
    List<Globo> globoList;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();

        background = new Texture("background.png");
        globoList = new ArrayList<>();
        globoList.add(new Globo());
        globoList.add(new Globo());
        globoList.add(new Globo());
        globoList.add(new Globo());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, 640, 480);

        for(Globo globo: globoList) globo.update(delta);

        for(Globo globo: globoList) globo.render(spriteBatch);
        spriteBatch.end();
    }
}