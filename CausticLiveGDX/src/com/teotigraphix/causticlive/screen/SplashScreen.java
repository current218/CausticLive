
package com.teotigraphix.causticlive.screen;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.teotigraphix.libgdx.screen.ScreenBase;

public class SplashScreen extends ScreenBase {

    private Image splashImage;

    public SplashScreen() {
    }

    @Override
    public void show() {
        super.show();

        AtlasRegion splashRegion = getAtlas().findRegion("splash");
        Drawable splashDrawable = new TextureRegionDrawable(splashRegion);

        // here we create the splash image actor; its size is set when the
        // resize() method gets called
        splashImage = new Image(splashDrawable, Scaling.stretch);
        splashImage.setFillParent(true);
        //splashImage.getColor().a = 0f;

        stage.addActor(splashImage);
    }

}
