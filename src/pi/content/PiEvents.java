package pi.content;

import arc.Events;
import mindustry.content.UnitTypes;
import mindustry.game.EventType;
import mindustry.game.Gamemode;
import mindustry.game.Team;
import pi.PlantIndustry.UnitSpawn;
import pi.classes.events.Event;
import pi.classes.events.RaidEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static mindustry.Vars.state;

public class PiEvents {
    public static List<Event> eventList;//new UnitSpawn<UnitType>(UnitTypes.flare, 1)

    public static void init() {
        eventList.add(new RaidEvent(Team.crux, new ArrayList<>(
                Arrays.asList(
                        new UnitSpawn<>(UnitTypes.flare, 1),
                        new UnitSpawn<>(UnitTypes.mega, 2),
                        new UnitSpawn<>(UnitTypes.poly, 3)
                )
        )));
    }

    public static void load() {
        init();
        Events.on(EventType.PlayEvent.class, event -> {
            if (state.rules.mode() == Gamemode.sandbox || state.rules.mode() == Gamemode.pvp) return;

            for (Event e:eventList) {
                e.update();
            }
        });
    }
}
