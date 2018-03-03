package org.firstinspires.ftc.teamcode.Robot.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FTC_API.Options;
import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SubSystem;
import org.firstinspires.ftc.teamcode.Utilitys.Constants;
import org.firstinspires.ftc.teamcode.Utilitys.Direction;

/**
 * Created by Ethan Hampton on 8/19/17.
 * <p>
 * Forklift class that is used to move and manipulate the blocks
 */

public class Forklift extends SubSystem {
    private static final String ID = "ForkLift";
    private Options options = new Options(ID);


    private DcMotor lift;
    private Servo rightTopClaw;
    private Servo leftTopClaw;
    private Servo rightBottomClaw;
    private Servo leftBottomClaw;

    @Override
    public boolean init(HardwareMap hardwareDevices) {
        lift = hardwareDevices.dcMotor.get(options.get("lift"));
        rightTopClaw = hardwareDevices.servo.get(options.get("rightTopClaw"));
        leftTopClaw = hardwareDevices.servo.get(options.get("leftTopClaw"));
        leftTopClaw.setDirection(Servo.Direction.REVERSE);

        rightBottomClaw = hardwareDevices.servo.get(options.get("rightBottomClaw"));
        leftBottomClaw = hardwareDevices.servo.get(options.get("leftBottomClaw"));
        leftBottomClaw.setDirection(Servo.Direction.REVERSE);
        openAll();
        return true;
    }

    public Forklift setMotorNames(String lift, String leftTopClaw, String rightTopClaw, String leftBottomClaw, String rightBottomClaw) {
        options.add("lift", lift);
        options.add("rightTopClaw", rightTopClaw);
        options.add("leftTopClaw", leftTopClaw);

        options.add("rightBottomClaw", rightBottomClaw);
        options.add("leftBottomClaw", leftBottomClaw);
        return this;
    }


    public void raise(double power) {
        lift.setPower(power);
    }

    public void closeAll() {
        setForkPosition(Direction.TOP, Position.NOT_OPEN);
        setForkPosition(Direction.BOTTOM, Position.NOT_OPEN);
    }

    public void openAll() {
        setForkPosition(Direction.TOP, Position.FULL_OPEN);
        setForkPosition(Direction.BOTTOM, Position.FULL_OPEN);
    }

    public void openHalfAll() {
        setForkPosition(Direction.TOP, Position.HALF_OPEN);
        setForkPosition(Direction.BOTTOM, Position.HALF_OPEN);
    }

    public boolean setForkPosition(Direction claw, Position position) {
        switch (claw) {
            case TOP:
                if (position.equals(Position.FULL_OPEN)) {
                    rightTopClaw.setPosition(Constants.FORKLIFT_OPEN_POSITION);
                    leftTopClaw.setPosition(Constants.FORKLIFT_OPEN_POSITION);
                } else if (position.equals(Position.HALF_OPEN)) {
                    rightTopClaw.setPosition(Constants.FORKLIFT_HALF_OPEN_POSITION);
                    leftTopClaw.setPosition(Constants.FORKLIFT_HALF_OPEN_POSITION);
                } else if (position.equals(Position.NOT_OPEN)) {
                    rightTopClaw.setPosition(Constants.FORKLIFT_CLOSE_POSITION);
                    leftTopClaw.setPosition(Constants.FORKLIFT_CLOSE_POSITION);
                } else {
                    return false;
                }
                return true;
            case BOTTOM:
                if (position.equals(Position.FULL_OPEN)) {
                    rightBottomClaw.setPosition(Constants.FORKLIFT_OPEN_POSITION);
                    leftBottomClaw.setPosition(Constants.FORKLIFT_OPEN_POSITION);
                } else if (position.equals(Position.HALF_OPEN)) {
                    rightBottomClaw.setPosition(Constants.FORKLIFT_HALF_OPEN_POSITION);
                    leftBottomClaw.setPosition(Constants.FORKLIFT_HALF_OPEN_POSITION);
                } else if (position.equals(Position.NOT_OPEN)) {
                    rightBottomClaw.setPosition(Constants.FORKLIFT_CLOSE_POSITION);
                    leftBottomClaw.setPosition(Constants.FORKLIFT_CLOSE_POSITION);
                } else {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }
    
    

    @Override
    public Options options() {
        return options;
    }

    public enum Position {FULL_OPEN, HALF_OPEN, NOT_OPEN}
}
