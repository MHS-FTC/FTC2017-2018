package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Robot.Robot;


/**
 * Created by ethan.hampton on 9/19/2017.
 * <p>
 * Simple teleop drive
 */

@TeleOp(name = "Teleop", group = "production")
public class MainTeleop extends OpMode {
    private Robot robot = new Robot();

    private double relicClawPosition;//stores the position of the relic claw

    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.start();
    }

    @Override
    public void loop() {
        robot.tick();

        //drive the robot (in order, forward backward, rotate)
        //NOTE: Y direction of joysticks needs to be reversed
        robot.drive.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);


        //control forklift from controller 1
        if (gamepad1.dpad_up) {
            robot.forklift.raise(-0.6);
        } else if (gamepad1.dpad_down) {
            robot.forklift.raise(0.6);
        } else {
            robot.forklift.raise(0);
        }

        robot.relicGrabber.extendClaw(-gamepad2.left_stick_y);//extend the claw out based on left joystick. This sets speed

        relicClawPosition += Range.scale(-gamepad2.right_stick_y, -1, 1, -0.1, 0.1);//scales the joystick to something reasonable for controlling the joystick.

        //if the claws position is out of bounds then prevent that from happening
        //Note that I purposely am not going to the limits of the servo(0.95 instead of 1) so we don't break the servo
        if (relicClawPosition > 0.95) {
            relicClawPosition = 0.9;
        }
        if (relicClawPosition < 0.05) {
            relicClawPosition = 0.05;
        }

        robot.relicGrabber.setClawPosition(relicClawPosition);//sets the actual claw position

        robot.relicGrabber.rotate(gamepad2.left_trigger);


        //control claws for forklift from both controllers
        if (gamepad1.right_bumper || gamepad1.left_bumper || gamepad2.right_bumper || gamepad2.left_bumper) {
            robot.forklift.open();
        } else {
            robot.forklift.close();
        }
    }
}
