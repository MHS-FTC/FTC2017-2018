package org.firstinspires.ftc.teamcode.Robot;

import org.firstinspires.ftc.teamcode.FTC_API.Robot.RobotBase;
import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.DriveSystemTemplate;
import org.firstinspires.ftc.teamcode.Robot.SubSystems.Forklift;
import org.firstinspires.ftc.teamcode.Robot.SubSystems.JewelPusher;
import org.firstinspires.ftc.teamcode.Robot.SubSystems.MecanumWheelDrive;
import org.firstinspires.ftc.teamcode.Robot.SubSystems.RelicGrabber;

/**
 * Created by ethan.hampton on 9/19/2017.
 * <p>
 * A mecanum robot that includes four wheels
 */

public class Robot extends RobotBase {
    public MecanumWheelDrive drive = new MecanumWheelDrive()
            .setMotorNames("leftFront", "rightFront", "leftBack", "rightBack");//Drives the robot
    public Forklift forklift = new Forklift().setMotorNames("lift",
            "leftTopClaw", "rightTopClaw",
            "leftBotClaw", "rightBotClaw");
    public JewelPusher jewel = new JewelPusher().setHardwareNames("hit", "drop", "color","led");
    public RelicGrabber relicGrabber = new RelicGrabber().setMotorNames("extender", "claw", "rotate");

    public Robot() {
        addSubSystem(drive);
        addSubSystem(forklift);
        addSubSystem(jewel);
        addSubSystem(relicGrabber);
    }

    /**
     * @return the implementation of the drive system this robot uses
     */
    @Override
    public DriveSystemTemplate getDriveSystem() {
        return drive;
    }
}
