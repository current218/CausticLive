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

package com.teotigraphix.causticlive.config;

import roboguice.inject.ContextSingleton;

import com.google.inject.Binder;
import com.google.inject.Singleton;
import com.teotigraphix.caustic.activity.IApplicationConfiguration;
import com.teotigraphix.caustic.activity.IApplicationPreferences;
import com.teotigraphix.caustic.application.CausticUIModule;
import com.teotigraphix.causticlive.internal.model.BrowserModel;
import com.teotigraphix.causticlive.internal.service.ArchiveService;
import com.teotigraphix.causticlive.model.IBrowserModel;
import com.teotigraphix.causticlive.service.IArchiveService;

public class CausticLiveModule extends CausticUIModule {
    @Override
    public void configure(Binder binder) {
        super.configure(binder);

        //------------------------------
        // Injected in Workspace constructor
        // Application
        binder.bind(IApplicationConfiguration.class).to(CausticLiveApplicationConfiguration.class)
                .in(Singleton.class);
        binder.bind(IApplicationPreferences.class).to(CausticLiveApplicationPreferences.class)
                .in(Singleton.class);

        binder.bind(IArchiveService.class).to(ArchiveService.class).in(ContextSingleton.class);
        binder.bind(IBrowserModel.class).to(BrowserModel.class).in(ContextSingleton.class);
    }
}
