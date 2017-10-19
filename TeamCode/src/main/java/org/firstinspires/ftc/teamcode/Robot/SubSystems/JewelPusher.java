package org.firstinspires.ftc.teamcode.Robot.SubSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FTC_API.Options;
import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SubSystem;
import org.firstinspires.ftc.teamcode.Utilitys.Direction;
import org.firstinspires.ftc.teamcode.Utilitys.Team;

/**
 * Created by ethan.hampton on 10/18/2017.
 * <p>
 * Subsystem for jewel hitter that will include two servos and color sensor
 */

public class JewelPusher extends SubSystem {
    private Options options = new Options("JewelPusher");

    private Servo drop;
    private Servo hitter;
    // TODO: 10/18/2017 Implement color sensors

    @Override
    public boolean init(HardwareMap hardwareDevices) {
        drop = hardwareDevices.servo.get(options.get("drop"));
        hitter = hardwareDevices.servo.get(options.get("hitter"));
        return true;
    }

    public JewelPusher setServoNames(String drop, String hitter) {
        options.add("drop", drop);
        options.add("hitter", hitter);
        return this;
    }

    public void dropArm() {
        drop.setPosition(0.5);
    }

    public void liftArm() {
        drop.setPosition(0);
    }


    public boolean hit(Direction direction) {
        switch (direction) {
            case LEFT:
                hitter.setPosition(0);
                return true;
            case RIGHT:
                hitter.setPosition(1);
                return true;
            case MIDDLE:
                hitter.setPosition(0.5);
                return true;
            case UNKNOWN:
                return false;
            default:
                return false;
        }
    }


    /**
     * @param team what team we are on
     */
    public void hitCorrectBall(Team team) {
        Team leftBall = Team.UNKNOWN;
        // TODO: 10/18/2017 Find correct color to hit and hit it
        if (!(team == Team.BLUE_TEAM || team == Team.RED_TEAM) || !(leftBall == Team.BLUE_TEAM || leftBall == Team.RED_TEAM)) {
            return;
        }
        dropArm();
        if (leftBall.equals(team)) {
            hit(Direction.LEFT);
        } else {
            hit(Direction.RIGHT);
        }
        hit(Direction.MIDDLE);
        liftArm();

    }

    @Override
    public Options options() {
        return options;
    }
}
