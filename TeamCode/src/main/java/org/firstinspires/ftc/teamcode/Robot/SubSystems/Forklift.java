package org.firstinspires.ftc.teamcode.Robot.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FTC_API.Options;
import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SubSystem;
import org.firstinspires.ftc.teamcode.Utilitys.Constants;

/**
 * Created by Ethan Hampton on 8/19/17.
 * <p>
 * Forklift class that is used to move and manipulate the blocks
 */

public class Forklift extends SubSystem {
    private static final String ID = "ForkLift";
    private Options options = new Options(ID);


    private DcMotor lift;
    private Servo rightClaw;
    private Servo leftClaw;

    @Override
    public boolean init(HardwareMap hardwareDevices) {
        lift = hardwareDevices.dcMotor.get(options.get("lift"));
        rightClaw = hardwareDevices.servo.get(options.get("rightClaw"));
        leftClaw = hardwareDevices.servo.get(options.get("leftClaw"));
        leftClaw.setDirection(Servo.Direction.REVERSE);
        open();
        return true;
    }

    public Forklift setMotorNames(String lift, String leftClaw, String rightClaw) {
        options.add("lift", lift);
        options.add("rightClaw", rightClaw);
        options.add("leftClaw", leftClaw);
        return this;
    }


    public void raise(double power) {
        lift.setPower(power);
    }

    public void close() {
        rightClaw.setPosition(Constants.FORKLIFT_CLOSE_POSITION);
        leftClaw.setPosition(Constants.FORKLIFT_CLOSE_POSITION);
    }

    public void open() {
        rightClaw.setPosition(Constants.FORKLIFT_OPEN_POSITION);
        leftClaw.setPosition(Constants.FORKLIFT_OPEN_POSITION);
    }

    @Override
    public Options options() {
        return options;
    }

}
