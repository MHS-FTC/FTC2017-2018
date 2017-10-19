package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot.Robot;


/**
 * Created by ethan.hampton on 9/19/2017.
 * <p>
 * Simple teleop drive
 */

@TeleOp(name = "Teleop", group = "production")
public class MainTeleop extends OpMode {
    private Robot robot = new Robot();

    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.start();
    }

    @Override
    public void loop() {
        robot.tick();

        //drive the robot (in order, strafe, forward backward, rotate)
        //NOTE: Y direction of joysticks needs to be reversed
        robot.drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);


        //control forklift
        if (gamepad1.dpad_up) {
            robot.forklift.raise(0.9);
        } else if (gamepad1.dpad_down) {
            robot.forklift.raise(-0.9);
        } else {
            robot.forklift.raise(0);
        }


        //control claws for forklift
        if (gamepad1.right_bumper || gamepad1.left_bumper) {
            robot.forklift.open();
        } else {
            robot.forklift.close();
        }
    }
}
