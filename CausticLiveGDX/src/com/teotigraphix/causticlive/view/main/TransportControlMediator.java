
package com.teotigraphix.causticlive.view.main;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.google.inject.Inject;
import com.teotigraphix.causticlive.model.ISequencerModel;
import com.teotigraphix.libgdx.controller.MediatorBase;
import com.teotigraphix.libgdx.screen.IScreen;
import com.teotigraphix.libgdx.ui.GDXToggleButton;

public class TransportControlMediator extends MediatorBase {

    @Inject
    ISequencerModel sequencerModel;

    private GDXToggleButton playButton;

    private GDXToggleButton recordButton;

    public TransportControlMediator() {
    }

    @Override
    public void create(IScreen screen) {

        playButton = new GDXToggleButton("Play", screen.getSkin());
        playButton.setPosition(66f, 90f);
        playButton.setSize(83f, 50f);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (playButton.isChecked()) {
                    sequencerModel.play();
                } else {
                    sequencerModel.stop();
                }
            }
        });

        screen.getStage().addActor(playButton);

        recordButton = new GDXToggleButton("Record", screen.getSkin());
        recordButton.setPosition(170f, 90f);
        recordButton.setSize(83f, 50f);
        recordButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (recordButton.isChecked()) {
                    sequencerModel.setRecordMode(true);
                } else {
                    sequencerModel.setRecordMode(false);
                }
            }
        });

        screen.getStage().addActor(recordButton);
    }

    @Override
    public void onRegister() {

    }

}