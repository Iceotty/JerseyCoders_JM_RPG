package backend;

import frontend.Action;

/**
 * Created by Joseph on 21/09/2016.
 */
public abstract class ActionHandler {
    protected Game game;
    public abstract Outcome execute(Action action);
}
