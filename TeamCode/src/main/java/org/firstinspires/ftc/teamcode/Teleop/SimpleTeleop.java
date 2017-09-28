package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot.SimpleRobot;


/**
 * Created by ethan.hampton on 9/19/2017.
 *
 * Simple teleop drive
 */

@TeleOp(name = "SimpleTeleop", group = "test")
public class SimpleTeleop extends OpMode {
    private SimpleRobot robot = new SimpleRobot();
    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.start();
    }

    @Override
    public void loop() {
        robot.tick();

        robot.drive.drive(gamepad1.left_stick_y,gamepad1.right_stick_y);
    }
}
