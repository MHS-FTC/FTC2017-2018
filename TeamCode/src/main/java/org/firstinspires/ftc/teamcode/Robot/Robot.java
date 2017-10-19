package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.FTC_API.Robot.RobotBase;
import org.firstinspires.ftc.teamcode.Robot.SubSystems.Forklift;
import org.firstinspires.ftc.teamcode.Robot.SubSystems.JewelPusher;
import org.firstinspires.ftc.teamcode.Robot.SubSystems.MecanumWheelDrive;

/**
 * Created by ethan.hampton on 9/19/2017.
 * <p>
 * A mecanum robot that includes four wheels
 */

public class Robot extends RobotBase {
    public MecanumWheelDrive drive = new MecanumWheelDrive()
            .setMotorNames("leftFront", "rightFront", "leftBack", "rightBack");
    public Forklift forklift = new Forklift().setMotorNames("lift", "leftClaw", "rightClaw");
    public JewelPusher pusher = new JewelPusher().setServoNames("drop", "hit");

    public Robot() {
        addSubSystem(drive);
        addSubSystem(forklift);
        addSubSystem(pusher);
    }
}
