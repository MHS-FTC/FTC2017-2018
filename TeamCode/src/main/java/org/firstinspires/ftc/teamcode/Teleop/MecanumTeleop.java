package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot.MecanumRobot;


/**
 * Created by ethan.hampton on 9/19/2017.
 * <p>
 * Simple teleop drive
 */

@TeleOp(name = "MecanumTeleop", group = "test")
public class MecanumTeleop extends OpMode {
    private MecanumRobot robot = new MecanumRobot();

    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.start();
    }

    @Override
    public void loop() {
        robot.tick();

        robot.drive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
    }
}
