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

package com.teotigraphix.causticlive.view.main.components;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.teotigraphix.causticlive.view.main.components.PartMixerControls.OnPartMixerControlsListener;
import com.teotigraphix.libgdx.ui.ButtonBar;
import com.teotigraphix.libgdx.ui.ButtonBar.OnButtonBarListener;
import com.teotigraphix.libgdx.ui.ControlTable;
import com.teotigraphix.libgdx.ui.Knob;
import com.teotigraphix.libgdx.ui.TextSlider;

public class PartMixer extends ControlTable {

    private String[] items = new String[] {
            "Volume", "Reverb", "Delay", "Stereo"
    };

    private ButtonBar buttonBar;

    public PartMixer(Skin skin) {
        super(skin);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    private List<PartMixerControls> partControls = new ArrayList<PartMixerControls>();

    private OnPartMixerListener listener;

    private int selectedIndex;

    protected boolean blockEvents;

    public void setSelectedIndex(int index) {
        buttonBar.select(index, true);
    }

    @Override
    protected void createChildren() {
        super.createChildren();
        //debug();
        // column 1 volume, pan, reverb, delay, stereo

        buttonBar = new ButtonBar(getSkin(), items, true, "default");
        buttonBar.setOnButtonBarListener(new OnButtonBarListener() {
            @Override
            public void onChange(int index) {
                blockEvents = true;
                selectedIndex = index;
                listener.onButtonChange(index);
                blockEvents = false;
            }
        });
        add(buttonBar).width(75f).fillY().expandY();

        for (int i = 0; i < 6; i++) {
            final int sliderIndex = i;
            PartMixerControls controls = new PartMixerControls(i, getSkin());
            controls.setOnPartMixerControlsListener(new OnPartMixerControlsListener() {
                @Override
                public void onSoloChange(boolean selected) {
                    listener.onSoloChange(selectedIndex, sliderIndex, selected);
                }

                @Override
                public void onSliderChange(float value) {
                    listener.onSliderChange(selectedIndex, sliderIndex, value);
                }

                @Override
                public void onMuteChange(boolean selected) {
                    listener.onMuteChange(selectedIndex, sliderIndex, selected);
                }

                @Override
                public void onKnobChange(float value) {
                    listener.onKnobChange(selectedIndex, sliderIndex, value);
                }
            });

            add(controls).fillY().expand().align(Align.right);
            partControls.add(controls);
        }

        setWidth(getPrefWidth());
        setHeight(getPrefHeight());
    }

    public TextSlider getSliderAt(int index) {
        return partControls.get(index).getVolumeSlider();
    }

    public Knob getKnobAt(int index) {
        return partControls.get(index).getPanKnob();
    }

    public void setOnPartMixerListener(OnPartMixerListener l) {
        this.listener = l;
    }

    public interface OnPartMixerListener {
        void onButtonChange(int index);

        void onSliderChange(int buttonIndex, int sliderIndex, float sliderValue);

        void onKnobChange(int buttonIndex, int sliderIndex, float value);

        void onMuteChange(int buttonIndex, int sliderIndex, boolean selected);

        void onSoloChange(int buttonIndex, int sliderIndex, boolean selected);
    }

}
