package pi.content;

import arc.Core;
import arc.graphics.Color;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Log;
import mindustry.Vars;
import pi.classes.ui.PlotCharacterUI;

public class PiUI {
    public static Seq<PlotCharacterUI> conversationList = new Seq<>();
    public static Seq<PlotCharacterUI> old_conversationList = new Seq<>();
    public static Table conversationTable = new Table();

    public static void load() {
        conversationList.add(new PlotCharacterUI(
                Core.atlas.find("mega").texture,
                "mega",
                "这是一个测试",
                Color.green
                ));
        conversationTable.update(() -> {
            // 处理新对话项
            Log.infoTag("Debug", old_conversationList.toString());
            conversationList.each(p -> {
                if (!old_conversationList.contains(p)) {
                    old_conversationList.add(p);
                    conversationTable.add(p);
                    conversationList.remove(p); // Seq 的 remove 支持遍历中删除（内部做了处理）
                }
            });

            // 处理已完成的对话项
            old_conversationList.each(p -> {
                if (p.finish()) {
                    old_conversationList.remove(p);
                    p.visible = false;
                    p.remove();
                }
            });
        });
        conversationTable.visibility = () -> {
            if(!Vars.ui.hudfrag.shown) return false;
            if(Vars.ui.minimapfrag.shown()) return false;
            if(!Vars.mobile) return true;
            if(Vars.player.unit().isBuilding()) return false;
            return Vars.control.input.block == null;
        };

        Vars.ui.hudGroup.addChild(conversationTable);
    }
}
