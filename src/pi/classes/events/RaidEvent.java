package pi.classes.events;

import mindustry.game.Team;
import mindustry.type.UnitType;
import pi.PlantIndustry.UnitSpawn;

import java.util.ArrayList;

public class RaidEvent extends Event{
    public float targetX = 0, targetY = 0;
    public ArrayList<UnitSpawn<UnitType>> callUnit;

    public RaidEvent(Team t, ArrayList<UnitSpawn<UnitType>> ut) {
        this.team = t;
        this.callUnit = ut;
    }

    public RaidEvent(Team t, float x, float y, ArrayList<UnitSpawn<UnitType>> ut) {
        this.team = t;
        this.callUnit = ut;
        this.targetX = x;
        this.targetY = y;
    }

    @Override
    public void update() {
        this.showUI();
        this.draw();
        for (UnitSpawn<UnitType> u:this.callUnit) {
            for(int i = 1;i <= u.amount; i++) {
                u.unit.spawn(this.team, this.targetX, this.targetY);
            }
        }
    }

    @Override
    public void showUI() {}

    @Override
    public void draw() {}
}
