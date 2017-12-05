package org.firstinspires.ftc.teamcode.Robot.SubSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FTC_API.Options;
import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SubSystem;
import org.firstinspires.ftc.teamcode.Utilitys.Direction;

/**
 * Created by ethan.hampton on 10/18/2017.
 * <p>
 * Subsystem for jewel hitter that will include two servos and color sensor
 */

public class JewelPusher extends SubSystem {
    public static final String ID = "JewelPusher";
    private Options options = new Options(ID);

    private Servo rotate;
    private Servo hitter;
    // TODO: 10/18/2017 Implement color sensors

    @Override
    public boolean init(HardwareMap hardwareDevices) {
        rotate = hardwareDevices.servo.get(options.get("rotate"));
        hitter = hardwareDevices.servo.get(options.get("hitter"));
        return true;
    }

    public JewelPusher setServoNames(String rotate, String hitter) {
        options.add("rotate", rotate);
        options.add("hitter", hitter);
        return this;
    }

    public void dropArm() {
        hitter.setPosition(0.5);
    }

    public void liftArm() {
        hitter.setPosition(0);
    }


    public void hit(Direction direction) {
        switch (direction) {
            case LEFT:
                rotate.setPosition(0);
                return;
            case RIGHT:
                rotate.setPosition(1);
                return;
            case MIDDLE:
                rotate.setPosition(0.5);
                return;
            case UNKNOWN:
                return;
            default:
        }
    }

    public void reset() {
        hit(Direction.MIDDLE);
        liftArm();
    }
    @Override
    public Options options() {
        return options;
    }
}
