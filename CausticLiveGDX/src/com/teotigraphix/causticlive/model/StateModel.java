
package com.teotigraphix.causticlive.model;

import java.util.UUID;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.teotigraphix.causticlive.model.state.SequencerModelState;
import com.teotigraphix.libgdx.model.ApplicationModel;
import com.teotigraphix.libgdx.model.CaustkModelBase;
import com.teotigraphix.libgdx.model.IApplicationModel;

@Singleton
public class StateModel extends CaustkModelBase implements IStateModel {

    @Inject
    IApplicationModel applicationModel;

    protected final CausticLiveApplicationState getState() {
        return ((CausticLiveApplicationState)((ApplicationModel)applicationModel).getState());
    }

    @Override
    public final SequencerModelState getSequencerModelState() {
        return getState().getSequencerModelState();
    }

    private UUID selectedSceneId;

    @Override
    public UUID getSelectedSceneId() {
        return selectedSceneId;
    }

    @Override
    public void setSelectedSceneId(UUID value) {
        selectedSceneId = value;
    }

    public StateModel() {
    }

}