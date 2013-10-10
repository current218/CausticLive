////////////////////////////////////////////////////////////////////////////////
// Copyright 2013 Michael Schmalle - Teoti Graphix, LLC
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
// http://www.apache.org/licenses/LICENSE-2.0 
// 
// Unless required by applicable law or agreed to in writing, software 
// distributed under the License is distributed on an "AS IS" BASIS, 
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and 
// limitations under the License
// 
// Author: Michael Schmalle, Principal Architect
// mschmalle at teotigraphix dot com
////////////////////////////////////////////////////////////////////////////////

package com.teotigraphix.causticlive.model.state;

import java.io.Serializable;

import com.teotigraphix.caustk.controller.ICaustkController;
import com.teotigraphix.caustk.sequencer.queue.QueueData;
import com.teotigraphix.caustk.sequencer.queue.QueueSequencer;

public class SequencerModelState implements Serializable {

    private static final long serialVersionUID = -4629791250494306755L;

    private QueueSequencer queueSequencer;

    public final QueueSequencer getQueueSequencer() {
        return queueSequencer;
    }

    private int selectedBank = 0;

    public int getSelectedBank() {
        return selectedBank;
    }

    public void setSelectedBank(int value) {
        selectedBank = value;
    }

    //----------------------------------

    private QueueData activeData;

    public QueueData getActiveData() {
        return activeData;
    }

    public void setActiveData(QueueData value) {
        activeData = value;
    }

    public SequencerModelState(ICaustkController controller) {
        queueSequencer = new QueueSequencer(controller.getRack());
        queueSequencer.create(controller.getRack().getTrackSequencer().getTrackSong());
    }

}