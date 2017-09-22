package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.hardware.motors.NeveRest20Gearmotor;
import com.qualcomm.robotcore.hardware.configuration.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.FTC_API.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.SubSystems.TwoWheelDrive;

/**
 * Created by ethan.hampton on 9/19/2017.
 * <p>
 * A simple robot that only includes two wheels
 */

public class SimpleRobot extends Robot {
    public TwoWheelDrive drive = new TwoWheelDrive()
            .setMotorNames("Left_Motor", "Right_Motor")
            .setMotorType(MotorConfigurationType.getMotorType(NeveRest20Gearmotor.class));

    public SimpleRobot() {
        addSubSystem(drive);
    }
}
