package org.firstinspires.ftc.teamcode.Robot.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.FTC_API.Options;
import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SubSystem;

/**
 * Created by Ethan Hampton on 8/19/17.
 * <p>
 * Simple TwoWheelDrive class that can be implemented and used
 */


public class MecanumWheelDrive extends SubSystem {
    private Options options = new Options(ID);
    protected DcMotor leftFrontMotor;
    protected DcMotor rightFrontMotor;
    protected DcMotor leftBackMotor;
    protected DcMotor rightBackMotor;

    public static final String ID = "TwoWheelDrive";

    @Override
    public boolean init(HardwareMap hardwareDevices) {
        leftFrontMotor = hardwareDevices.dcMotor.get(options.get("leftFrontMotor"));
        rightFrontMotor = hardwareDevices.dcMotor.get(options.get("rightFrontMotor"));
        leftBackMotor = hardwareDevices.dcMotor.get(options.get("leftBackMotor"));
        rightBackMotor = hardwareDevices.dcMotor.get(options.get("rightBackMotor"));

        leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        return true;
    }

    public MecanumWheelDrive setMotorNames(String leftFront, String rightFront, String leftBack, String rightBack) {
        options.add("leftFrontMotor", leftFront);
        options.add("rightFrontMotor", rightFront);
        options.add("leftBackMotor", leftBack);
        options.add("rightBackMotor", rightBack);
        return this;
    }

    public MecanumWheelDrive setMotorType(MotorConfigurationType type) {
        leftFrontMotor.setMotorType(type);
        rightFrontMotor.setMotorType(type);
        leftBackMotor.setMotorType(type);
        rightBackMotor.setMotorType(type);
        return this;
    }

    /**
     * @param leftPower  how fast the left motors should go
     * @param rightPower how fast the right motors should go
     */
    public void driveTank(double leftPower, double rightPower) {
        leftFrontMotor.setPower(leftPower);
        leftBackMotor.setPower(leftPower);
        rightFrontMotor.setPower(rightPower);
        rightBackMotor.setPower(rightPower);
    }

    /**
     * TODO: should dead zone joystick to insure we are not burning out motors
     *
     * @param strafe  how much to strafe, from -1 to 1, 1 is full right. left x = strafe
     * @param forward how much to go forward and backwards, from -1 to 1, 1 is full forwards
     * @param rotate  how much to rotate, from -1 to 1, 1 is full right
     */
    public void drive(double strafe, double forward, double rotate) {
        double frontLeft = forward + rotate + strafe;
        double rearLeft = forward + rotate - strafe;
        double frontRight = forward - rotate - strafe;
        double rearRight = forward - rotate + strafe;

        leftFrontMotor.setPower(frontLeft);
        leftBackMotor.setPower(rearLeft);
        rightFrontMotor.setPower(frontRight);
        rightBackMotor.setPower(rearRight);

        // Where:
        // Ch1 = Right joystick X-axis
        // Ch3 = Left joystick Y-axis
        // Ch4 = Left joystick X-axis
    }


    @Override
    public Options options() {
        return options;
    }

}
