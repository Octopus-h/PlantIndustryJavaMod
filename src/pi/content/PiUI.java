package pi.content;

import arc.scene.ui.layout.Table;
import pi.classes.ui.PlotCharacterUI;

import java.util.ArrayList;
import java.util.List;

public class PiUI {
    public static List<PlotCharacterUI> conversationList = new ArrayList<>();
    public static List<PlotCharacterUI> old_conversationList = new ArrayList<>();
    public static Table conversationTable = new Table();

    public static void load() {
        conversationTable.update(() -> {
            for (PlotCharacterUI P:conversationList) {
                if(!old_conversationList.contains(P)) {
                    old_conversationList.add(P);
                    conversationTable.add(P);
                }
            }
            for (PlotCharacterUI P:old_conversationList) {
                if(P.finish()) {
                    old_conversationList.remove(P);
                    P.visible = false;
                    P.remove();
                }
            }
        });
    }
}
