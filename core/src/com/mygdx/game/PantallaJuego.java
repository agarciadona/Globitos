package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PantallaJuego extends BaseScreen {


    PantallaJuego(MyGdxGame game) {
        super(game);
    }

    Random rng = new Random();
    float tiempo;
    BitmapFont bitmapFont,bitmapFont2,bitmapFont3;
    SpriteBatch spriteBatch;
    Texture progressBar;
    Texture background;
    Color colorBien;
    String textoBien, cambio;
    List<Globo> globoList;
    float gameTime,alarmTime, driftTime,colorTime;
    float alarmDuration = 1;
    int score = 0;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        background = new Texture("background.png");
        progressBar = new Texture("Pbar.png");
        globoList = new ArrayList<>();
        colorBien = RngColor();
        textoBien = RngTexto();
        cambio = cambio();
        tiempo = 60;
        bitmapFont = new BitmapFont();
        bitmapFont2 = new BitmapFont();
        bitmapFont3 = new BitmapFont();

    }

    public void update(float delta){
        tiempo -= 0.02f;
        for(Globo globo: globoList) globo.update(delta);
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
            for (Globo globo : globoList) {

                if (distancia(Gdx.input.getX(), mouseY, globo.getX(), globo.getY()) < globo.size/2) {
                    globo.delete = true;
                    if (cambio.equals("Clr")) {
                        if (colorBien == Color.RED && globo.color == Globo.ColorGlobo.ROJO) {
                            score++;
                            tiempo+=2;
                        } else if (colorBien == Color.BLUE && globo.color == Globo.ColorGlobo.AZUL) {
                            score++;
                            tiempo+=2;
                        } else if (colorBien == Color.GREEN && globo.color == Globo.ColorGlobo.VERDE) {
                            score++;
                            tiempo+=2;
                        } else {
                            score--;
                            tiempo-=3;

                        }
                        cambio = cambio();
                        colorBien = RngColor();
                        textoBien = RngTexto();
                        break;
                    } else {
                        if (textoBien.equals("ROJO") && globo.color == Globo.ColorGlobo.ROJO) {
                            score++;
                            tiempo++;
                        } else if (textoBien.equals("AZUL") && globo.color == Globo.ColorGlobo.AZUL) {
                            score++;
                            tiempo++;
                        } else if (textoBien.equals("VERDE") && globo.color == Globo.ColorGlobo.VERDE) {
                            score++;
                            tiempo++;
                        } else {
                            score--;
                            tiempo-=3;
                        }
                    }
                    cambio = cambio();
                    colorBien = RngColor();
                    textoBien = RngTexto();
                    break;
                }

            }


        }
        globoList.removeIf(globo -> globo.delete);


        if(gameTime > alarmTime){
            alarmTime = gameTime + alarmDuration;
            globoList.add(new Globo());
        }
        if(driftTime > 0.20f){
            for(Globo globo: globoList) globo.drift();
            driftTime = 0;
        }

        gameTime += delta;
        driftTime += delta;
        colorTime += delta;
        if(tiempo <= 0){
            setScreen(new GameOverScreen(game));
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, 640, 480);
        spriteBatch.draw(progressBar,300f,420f,tiempo,20);


        bitmapFont3.getData().setScale(3);
        bitmapFont3.setColor(new Color(Color.WHITE));
        bitmapFont3.draw(spriteBatch,cambio, 20, 450);


        bitmapFont.getData().setScale(3);
        bitmapFont.setColor(new Color(Color.WHITE));
        bitmapFont.draw(spriteBatch, String.valueOf(score), 575, 450);

        bitmapFont2.getData().setScale(3);
        bitmapFont2.setColor(new Color(colorBien));
        bitmapFont2.draw(spriteBatch,textoBien, 100, 450);



        for(Globo globo: globoList) globo.render(spriteBatch);
        spriteBatch.end();
        update(delta);
    }




    float distancia(float mx, float my, float gx, float gy){
        return (float) Math.sqrt((Math.pow((mx - gx),2)) + (Math.pow((my-gy),2)));
    }

    public Color RngColor(){
        int r = rng.nextInt(3)+1;

        if(r == 1){
            return Color.RED;
        }
        if(r == 2) {
            return Color.BLUE;
        }
        else return Color.GREEN;

    }

    public String RngTexto(){
        int r = rng.nextInt(3)+1;

        if(r == 1){
            return "ROJO";
        }
        if(r == 2) {
            return "AZUL";
        }
        else return "VERDE";

    }

    public String cambio(){
        int r = rng.nextInt(2);
     if(r == 0){
         return "Txt";
     }
     else{
         return "Clr";
     }

    }

}