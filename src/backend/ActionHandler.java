package backend;

import frontend.Action;

import java.util.ArrayList;

/**
 * Created by Joseph on 21/09/2016.
 */
public abstract class ActionHandler {
    protected Game game;
    public abstract ArrayList<Outcome> execute(Action action);
}
