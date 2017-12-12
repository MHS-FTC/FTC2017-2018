package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.FTC_API.Examples.Drive;
import org.firstinspires.ftc.teamcode.FTC_API.Options;
import org.firstinspires.ftc.teamcode.Robot.SubSystems.MecanumWheelDrive;
import org.firstinspires.ftc.teamcode.Utilitys.Constants;

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

    private int driveTime;
    private double forwardSpeed = Constants.DEFAULT_SPEED;
    private double turnSpeed = 0;

    @Override
    public void start() {
        drive = (MecanumWheelDrive) robot.getSubSystem(MecanumWheelDrive.ID);
        startTime = robot.getTimeMilliseconds();

        drive.drive(0, forwardSpeed, turnSpeed);
    }

    @Override
    public void tick() {
        if ((robot.getTimeMilliseconds() - startTime) > driveTime) {
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
    public int stop() {
        //just pass through the position, this allows for "multithreaded" things that can be called
        return positionInArray;
    }

    /**
     * Resets the position in array number so you can changing it in the next step
     *
     * @return this object for building
     */
    public DriveTime resetPositionInArray() {
        positionInArray = 0;
        return this;
    }

    @Override
    public String[] requiredSubSystems() {
        return new String[]{Drive.ID};
    }


    public DriveTime setSpeeds(double forwardSpeed, double turnSpeed) {
        this.forwardSpeed = forwardSpeed;
        this.turnSpeed = turnSpeed;
        return this;
    }

    public DriveTime setTime(int time) {
        driveTime = time;
        return this;
    }
}
