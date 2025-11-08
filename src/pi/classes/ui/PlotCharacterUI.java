package pi.classes.ui;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.Texture;
import arc.scene.ui.Image;
import arc.scene.ui.layout.Table;
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
        this.height = 128f;
        this.width = 512f;
        texture = png;
        characterName =name;
        conversation = text;
        nameColor = color;
        this.init();
    }

    public void init() {
        float tableHeight = this.height;
        Image character = new Image(texture) {{
            this.scaleX = this.scaleY = tableHeight / this.imageHeight;
        }};
        this.add(character);
        this.table(Styles.black5, t -> {
            t.labelWrap(characterName).color(nameColor).row();
            t.labelWrap(conversation).row();
        });
        this.update(() -> {
            this.time += Time.delta;
            if(this.time >= 0.2 && this.y <= Core.graphics.getHeight()*0.85) {
                this.y += Core.graphics.getHeight()*0.05;
                this.time = 0;
            }
        });
    }

    public boolean finish() {
        return this.y >= Core.graphics.getHeight()*0.85;
    }
}
