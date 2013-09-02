
package com.teotigraphix.causticlive.view.main;

import org.androidtransfuse.event.EventObserver;

import com.google.inject.Inject;
import com.teotigraphix.causticlive.model.ISequencerModel;
import com.teotigraphix.causticlive.model.ISequencerModel.OnSequencerModelPropertyChange;
import com.teotigraphix.libgdx.controller.MediatorBase;
import com.teotigraphix.libgdx.screen.IScreen;
import com.teotigraphix.libgdx.ui.ButtonBar;
import com.teotigraphix.libgdx.ui.ButtonBar.OnButtonBarListener;

public class BankBarMediator extends MediatorBase {

    @Inject
    ISequencerModel sequencerModel;

    private ButtonBar view;

    private String[] items = {
            "A", "B", "C", "D"
    };

    public BankBarMediator() {
    }

    @Override
    public void create(IScreen screen) {
        view = new ButtonBar(screen.getSkin(), items, true);
        view.setOnButtonBarListener(new OnButtonBarListener() {
            @Override
            public void onChange(int index) {
                sequencerModel.setSelectedBank(index);
            }
        });
        view.setPosition(540f, 365f);
        view.setSize(50f, 250f);
        screen.getStage().addActor(view);
    }

    @Override
    protected void registerObservers() {
        super.registerObservers();

        register(sequencerModel.getDispatcher(), OnSequencerModelPropertyChange.class,
                new EventObserver<OnSequencerModelPropertyChange>() {
                    @Override
                    public void trigger(OnSequencerModelPropertyChange object) {
                        view.select(sequencerModel.getSelectedBank());
                    }
                });
    }

    @Override
    public void onRegister() {

    }

}