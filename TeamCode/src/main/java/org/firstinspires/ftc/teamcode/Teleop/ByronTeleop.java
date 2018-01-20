package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot.SimpleRobot;

/*
 * Created by ethan.hampton on 9/27/2017.
 *
 * This is a test
 */
@Disabled
@TeleOp(name = "Byron", group = "Test")
public class ByronTeleop extends OpMode {
    private SimpleRobot robot = new SimpleRobot();

    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.start();
    }

    @Override
    public void loop() {
        robot.tick();

        robot.drive.driveTank(gamepad1.left_stick_y, gamepad1.right_stick_y);


        if (robot.getTimeMilliseconds() > 9000) {
            robot.drive.driveTank(0, 0);
        }

        if (gamepad2.a && robot.getTimeMilliseconds() > 8000) {
            robot.drive.driveTank(1, 1);
        } else {
            robot.drive.driveTank(0, 0);
        }


    }
}
