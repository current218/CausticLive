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

package com.teotigraphix.causticlive;

import java.util.List;

import javafx.application.Application;

import com.google.inject.Module;
import com.teotigraphix.caustic.application.JavaFXApplication;
import com.teotigraphix.caustic.mediator.DesktopMediatorBase;
import com.teotigraphix.causticlive.config.ApplicationConstants;
import com.teotigraphix.causticlive.config.ApplicationModule;

public class CausticLiveApplication extends JavaFXApplication {

    @Override
    protected String getRootPane() {
        return ApplicationConstants.PANE_ROOT;
    }

    @Override
    protected void initMediators(List<DesktopMediatorBase> mediators) {

    }

    @Override
    public void init(List<Module> modules) throws Exception {
        super.init(modules);
        modules.add(new ApplicationModule());
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
