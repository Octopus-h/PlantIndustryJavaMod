package pi.classes.events;

import mindustry.game.Team;

public abstract class Event {
    public Team team = Team.derelict;

    public abstract void update();

    public abstract void showUI();

    public abstract void draw();
}
