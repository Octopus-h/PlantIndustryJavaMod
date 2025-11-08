package pi.classes.bullets;

import arc.math.Angles;
import arc.util.Time;
import mindustry.entities.bullet.EmpBulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Groups;

public class EmpInterceptBulletType extends EmpBulletType {

    public EmpInterceptBulletType() {
        super();
    }

    @Override
    public void updateHoming(Bullet b){
        var target = Groups.bullet.intersect(b.x - range, b.y - range, range * 2.0f, range * 2.0f)
                .min(tgt -> tgt.team != b.team && tgt.type.hittable, b::dst2);

        if(target == null){
            super.updateHoming(b);
        }

        // 计算子弹当前角度到目标角度的转向角度，每帧最多转动“homingPower * Time.delta * 50”度
        if (target != null) { // 添加非空判断
            b.vel.setAngle(Angles.moveToward(
                    b.rotation(),
                    b.angleTo(target), // 只有 target 非空时才调用
                    this.homingPower * Time.delta * 50.0F
            ));
        }
    }

    @Override
    public void hit(Bullet b, float x, float y) {
        super.hit(b, x, y);
        if(b.absorbed) return;

        Groups.bullet.intersect(x - radius, y - radius, radius * 2.0f, radius * 2.0f, other -> {
            if(b.team != other.team && b.type.hittable){
                float v = splashDamage;
                if(other.damage > v){
                    other.damage -= v;
                }else{
                    other.remove();
                }
            }
        });
    }
}
