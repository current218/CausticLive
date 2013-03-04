
package com.teotigraphix.causticlive.internal.controller;

import roboguice.activity.event.OnActivityResultEvent;
import roboguice.activity.event.OnContentChangedEvent;
import roboguice.activity.event.OnCreateEvent;
import roboguice.activity.event.OnDestroyEvent;
import roboguice.activity.event.OnNewIntentEvent;
import roboguice.activity.event.OnPauseEvent;
import roboguice.activity.event.OnRestartEvent;
import roboguice.activity.event.OnResumeEvent;
import roboguice.activity.event.OnStartEvent;
import roboguice.activity.event.OnStopEvent;
import roboguice.event.EventManager;
import roboguice.event.Observes;
import roboguice.inject.ContextSingleton;
import android.util.Log;

import com.google.inject.Inject;
import com.teotigraphix.caustic.activity.IApplicationPreferences;
import com.teotigraphix.caustic.controller.IApplicationController;
import com.teotigraphix.caustic.core.CausticException;
import com.teotigraphix.caustic.router.IRouter;
import com.teotigraphix.caustic.song.IWorkspace;
import com.teotigraphix.caustic.view.Mediator.OnAttachMediatorEvent;
import com.teotigraphix.causticlive.R;
import com.teotigraphix.causticlive.model.IMainHandlers;
import com.teotigraphix.causticlive.view.ControlBarMediator;

/*
> Startup from dashboard | Startup after Back button from Recent menu
OnContentChangedEvent
!!! OnCreateEvent
OnStartEvent
OnResumeEvent

> Back button
OnPauseEvent
OnStopEvent
!!! OnDestroyEvent

> Recent Menu Out of App
OnPauseEvent
OnStopEvent

> Recent Menu In to App
!!! OnRestartEvent
OnStartEvent
OnResumeEvent


[OnCreateEvent|OnRestartEvent]
OnStartEvent
OnResumeEvent

[OnDestroyEvent|Recent Menu]
OnPauseEvent
OnStopEvent


*/
@ContextSingleton
// should be ApplicationInstrumentation, the facade template for all majors states and phases
public class MainActivityHandlers implements IMainHandlers {

    @Inject
    IWorkspace workspace;

    @Inject
    EventManager eventManager;

    @Inject
    IRouter router;

    @Inject
    IApplicationController controller;

    @Inject
    IApplicationPreferences preferences;

    @Inject
    ControlBarMediator controlBarMediator;

    private String TAG;

    public MainActivityHandlers() {
        TAG = "MainActivityHandlers";
    }

    void onCreateEvent(@Observes OnCreateEvent event) throws CausticException {

        // register all module client commands [OnRegisterRouterCommandsEvent]
        router.initialize();

        controller.sendCommand(IApplicationController.REGISTER_MAIN_LAYOUT, R.id.main_layout);

        // all models, sound system and rack are restored, notify the mediators
        eventManager.fire(new OnAttachMediatorEvent());

        // load the last project
        controller.sendCommand(IApplicationController.LOAD_PROJECT,
                preferences.getLastProjectFile());
    }

    void onContentChangedEvent(@Observes OnContentChangedEvent event) {

        // register the layout
        //controller.sendCommand(IApplicationController.REGISTER_MAIN_LAYOUT, R.id.main_layout);
        // OnSetupMediatorEvent ?

    }

    void onStartEvent(@Observes OnStartEvent event) {
        Log.d(TAG, "OnStartEvent");
    }

    void onStopEvent(@Observes OnStopEvent event) {
        Log.d(TAG, "onStopEvent");
        //workspace.getRack().getOutputPanel().stop();
    }

    void onDestroyEvent(@Observes OnDestroyEvent event) {
        Log.d(TAG, "OnDestroyEvent");
    }

    void onNewIntentEvent(@Observes OnNewIntentEvent event) {
        Log.d(TAG, "OnNewIntentEvent");
    }

    void onPauseEvent(@Observes OnPauseEvent event) {
        Log.d(TAG, "OnPauseEvent");
    }

    void onRestartEvent(@Observes OnRestartEvent event) {
        Log.d(TAG, "OnRestartEvent");
    }

    void onResumeEvent(@Observes OnResumeEvent event) {
        Log.d(TAG, "OnResumeEvent");
    }

    void onActivityResultEvent(@Observes OnActivityResultEvent event) {
        Log.d(TAG, "onActivityResultEvent");
    }
}
