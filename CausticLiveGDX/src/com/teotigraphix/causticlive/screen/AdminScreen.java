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

package com.teotigraphix.causticlive.screen;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.teotigraphix.causticlive.view.admin.OptionsPaneMediator;
import com.teotigraphix.libgdx.application.IGame;
import com.teotigraphix.libgdx.screen.ScreenBase;

@Singleton
public class AdminScreen extends ScreenBase {

    @Inject
    OptionsPaneMediator optionsPaneMediator;

    public AdminScreen() {
    }

    @Override
    public void initialize(IGame game) {
        super.initialize(game);
        SkinRegistry.register(getSkin());
        addMediator(optionsPaneMediator);
    }
}
