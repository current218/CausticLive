
package com.teotigraphix.causticlive.view.components;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pools;
import com.teotigraphix.caustk.sequencer.queue.QueueData.QueueDataState;
import com.teotigraphix.libgdx.ui.GDXToggleButton;

public class PadButton extends GDXToggleButton {

    protected boolean longPressed;

    @Override
    public PadButtonStyle getStyle() {
        return (PadButtonStyle)super.getStyle();
    }

    //----------------------------------
    // state
    //----------------------------------

    // Idle, Queued, Playing, Plsying/Lock (last measure)
    private QueueDataState state;

    public QueueDataState getState() {
        return state;
    }

    public void setState(QueueDataState value) {
        state = value;
        invalidate();
    }

    //----------------------------------
    // checked
    //----------------------------------
    @Override
    public void setChecked(boolean value) {
    }

    @Override
    public boolean isChecked() {
        return false;
    }

    //----------------------------------
    // selected
    //----------------------------------

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean value) {
        setSelected(value, false);
    }

    public void setSelected(boolean value, boolean noEvent) {
        if (selected == value)
            return;
        selected = value;
        //CtkDebug.log("Button:" + selected);
        if (!noEvent && !isDisabled()) {
            ChangeEvent changeEvent = Pools.obtain(ChangeEvent.class);
            if (fire(changeEvent))
                selected = !value;
            Pools.free(changeEvent);
        }
    }

    //--------------------------------------------------------------------------
    // Constructors
    //--------------------------------------------------------------------------

    @Override
    protected Class<? extends ButtonStyle> getStyleType() {
        return PadButtonStyle.class;
    }

    public PadButton(String text, Skin skin) {
        super(text, skin);
    }

    public PadButton(String text, ButtonStyle style) {
        super(text, style);
    }

    public PadButton(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }

    @SuppressWarnings("unused")
    private ClickListener clickListener;

    private OnPadButtonListener listener;

    @Override
    protected void init() {
        addListener(clickListener = new ClickListener() {
            //            @Override
            //            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            //                if (isDisabled())
            //                    return false;
            //                setSelected(!selected);
            //                return true;
            //            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (longPressed) {
                    longPressed = false;
                    return;
                }
                if (isDisabled())
                    return;
                setSelected(!selected);
            }
        });

        addListener(new ActorGestureListener() {
            @Override
            public boolean longPress(Actor actor, float x, float y) {
                longPressed = true;
                listener.onLongPress((Integer)getProperties().get("index"), x, y);
                return true;
            }
        });
    }

    //--------------------------------------------------------------------------
    // Overridden Public :: Methods
    //--------------------------------------------------------------------------

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        Drawable queueOverlay = getStyle().queueOverlay;
        Drawable playOverlay = getStyle().playOverlay;
        @SuppressWarnings("unused")
        Drawable lockOverlay = getStyle().lockOverlay;

        if (getState() != null) {
            switch (getState()) {
                case Queued:
                    queueOverlay.draw(batch, getX(), getY(), getWidth(), getHeight());
                    break;
                case Selected:
                    playOverlay.draw(batch, getX(), getY(), getWidth(), getHeight());
                    break;
                case UnQueued:
                    //queueOverlay.draw(batch, getX(), getY(), getWidth(), getHeight());
                    break;
                default:
                    break;
            }
        }

    }

    public void setOnPadButtonListener(OnPadButtonListener l) {
        listener = l;

    }

    public interface OnPadButtonListener {
        void onLongPress(Integer index, float x, float y);
    }

    //--------------------------------------------------------------------------
    // Style
    //--------------------------------------------------------------------------

    public static class PadButtonStyle extends ButtonStyle {

        public Drawable queueOverlay;

        public Drawable playOverlay;

        public Drawable lockOverlay;

        public PadButtonStyle() {
        }

        public PadButtonStyle(Drawable up, Drawable down, Drawable checked, BitmapFont font) {
            super(up, down, checked, font);
        }

        public PadButtonStyle(TextButtonStyle style) {
            super(style);
        }

    }
}
