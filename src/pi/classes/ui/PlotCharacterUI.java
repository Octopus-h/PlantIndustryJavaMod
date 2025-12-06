package pi.classes.ui;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.Texture;
import arc.scene.ui.Image;
import arc.scene.ui.layout.Table;
import arc.util.Log;
import arc.util.Time;
import mindustry.ui.Styles;

public class PlotCharacterUI extends Table {
    public Texture texture;
    public String characterName;
    public String conversation;
    public Color nameColor;
    private float time = 0;

    public PlotCharacterUI(Texture png, String name, String text, Color color) {
        super();
        this.fillParent = true;
        this.height = 256f;
        this.width = 1024f;
        texture = png;
        characterName =name;
        conversation = text;
        nameColor = color;
        this.init();
    }

    public void init() {
        Image character = new Image(texture) {{
            this.width = this.height = PlotCharacterUI.this.height;
        }};
        this.add(character);
        this.table(Styles.black5, t -> {
            t.labelWrap(characterName).color(nameColor).fontScale(3.5f).row();
            t.labelWrap(conversation).fontScale(3f).row();
        });
        this.update(() -> {
            Log.infoTag("Debug", this.x+","+this.y);
            this.time += Time.delta;
            if(this.time >= 3 && this.y <= Core.graphics.getHeight()*0.85) {
                this.y += Core.graphics.getHeight()*0.01;
                this.time = 0;
            }
        });
    }

    public boolean finish() {
        return this.y >= Core.graphics.getHeight()*0.85 && this.time >= 120;
    }
}
