package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.FTC_API.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.SubSystems.MecanumWheelDrive;

/**
 * Created by ethan.hampton on 9/19/2017.
 * <p>
 * A mecanum robot that includes four wheels
 */

public class MecanumRobot extends Robot {
    public MecanumWheelDrive drive = new MecanumWheelDrive()
            .setMotorNames("leftFront", "rightFront", "leftBack", "rightBack");

    public MecanumRobot() {
        addSubSystem(drive);
    }
}
