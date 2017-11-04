package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.FTC_API.Examples.Drive;
import org.firstinspires.ftc.teamcode.FTC_API.Options;
import org.firstinspires.ftc.teamcode.Robot.SubSystems.MecanumWheelDrive;

/**
 * Created by Ethan Hampton on 8/19/17.
 * <p>
 * Simple drive for time module
 */

public class DriveTime extends Module {
    private Options options = new Options("Drive For Time");
    private MecanumWheelDrive drive;
    private boolean isDone = false;
    private double startTime;

    @Override
    public void start() {
        drive = (MecanumWheelDrive) robot.getSubSystem(MecanumWheelDrive.ID);
        startTime = robot.getTimeMilliseconds();

        drive.drive(0, Double.valueOf(options.get("forward_speed")), Double.valueOf(options.get("turn_speed")));
    }

    @Override
    public void tick() {
        if ((robot.getTimeMilliseconds() - startTime) > Integer.getInteger(options.get("drive_time"))) {
            drive.drive(0, 0, 0);
            isDone = true;
        }
    }

    @Override
    public Options options() {
        return options;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String[] requiredSubSystems() {
        return new String[]{Drive.ID};
    }


    public DriveTime setSpeeds(double forwardSpeed, double turnSpeed) {
        options.add("forward_speed", String.valueOf(forwardSpeed));
        options.add("turn_speed", String.valueOf(turnSpeed));
        return this;
    }

    public DriveTime setTime(int time) {
        options.add("drive_time", String.valueOf(time));
        return this;
    }
}
