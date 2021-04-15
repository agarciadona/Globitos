package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Globo {

    enum ColorGlobo {ROJO, VERDE, AZUL}

    static Texture globoRed = new Texture("ballon_red.png");
    static Texture globoGreen = new Texture("ballon_green.png");
    static Texture globoBlue = new Texture("ballon_blue.png");
    float x, y, size;
    float vy,vx;
    ColorGlobo color;
    boolean mov = true;
    boolean delete = false;
    Random rng = new Random();

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSize() {
        return size;
    }

    public float getVy() {
        return vy;
    }

    public float getVx() {
        return vx;
    }

    Globo() {
        this.x = rng.nextInt(440-20)+20;
        this.y = -100;
        this.size = rng.nextInt(60-30)+30;
        this.vy = rng.nextInt(60-40)+40;
        this.vx = rng.nextInt(20)-10;
        this.color = RngColor();
    }

    public void update(float delta) {
            y += vy * delta;

    }

    public ColorGlobo RngColor(){
        int r = rng.nextInt(3)+1;

        if(r == 1){
            return Globo.ColorGlobo.ROJO;
        }
        if(r == 2) {
            return Globo.ColorGlobo.AZUL;
        }
        else return Globo.ColorGlobo.VERDE;

    }

    public void drift (){

        if(mov){
            x += 2.5f;
            y += 1.5f;
            mov = false;
        }else {
            x -= 2.5f;
            y += 1.5f;
            mov = true;
        }
    }

    public void render(SpriteBatch spriteBatch) {
        Texture texture;
        switch (color) {
            case ROJO:
            default:
                texture = globoRed;
                break;
            case VERDE:
                texture = globoGreen;
                break;
            case AZUL:
                texture = globoBlue;
                break;
        }
        spriteBatch.draw(texture, x-size/2, y-size/2, size, size);
    }
}