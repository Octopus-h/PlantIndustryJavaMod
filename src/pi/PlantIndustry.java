package pi;

import arc.Events;
import arc.util.Log;
import mindustry.game.EventType.ClientLoadEvent;
import mindustry.mod.Mod;
import pi.classes.entities.EntityRegister;
import pi.content.*;

public class PlantIndustry extends Mod{

    public static final String MOD_NAME = "plantindustry";//
    public static String name(String name){
        return MOD_NAME + "-" + name;
    }

    public PlantIndustry() {
        Events.on(ClientLoadEvent.class, e -> {
            PiUI.load();
            Log.infoTag("Debug", String.valueOf(PiBlockTypes.BaseCore));
            Log.infoTag("Debug", String.valueOf(PiItemTypes.Crystal));
        });
    }

    @Override
    public void loadContent() {
        PiItemTypes.load();
        EntityRegister.load();
        PiUnitTypes.load();
        PiStatuses.load();
        PiBlockTypes.load();
        PiPlanets.load();
        PiSectors.load();
        PiTechTree.Load();
    }

    public static class UnitSpawn<U> {
        public U unit;
        public int amount;
        public UnitSpawn(U u, int num) {
            this.unit = u;
            this.amount = num;
        }
    }
}
