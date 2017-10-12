package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot.Robot;


/**
 * Created by ethan.hampton on 9/19/2017.
 * <p>
 * Simple teleop drive
 */

@TeleOp(name = "ForkliftTeleop", group = "test")
public class ForkliftTeleop extends OpMode {
    private Robot robot = new Robot();

    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.start();
    }

    @Override
    public void loop() {
        robot.tick();

        robot.forklift.raise(gamepad1.left_stick_y);
        if (gamepad1.a) {
            robot.forklift.open();
        }
        if (gamepad1.b) {
            robot.forklift.close();
        }
    }
}
