package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Utilitys.Direction;

/**
 * Created by ethan.hampton on 10/11/2017.
 * <p>
 * Tests the jewel servos
 */
@TeleOp(name = "JewelTest", group = "test")
public class JewelTest extends OpMode {
    private Robot robot = new Robot();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.left_bumper) {
            robot.pusher.hit(Direction.LEFT);
        } else if (gamepad1.right_bumper) {
            robot.pusher.hit(Direction.RIGHT);
        } else {
            robot.pusher.hit(Direction.MIDDLE);
        }

        if (gamepad1.dpad_up) {
            robot.pusher.liftArm();
        }
        if (gamepad1.dpad_down) {
            robot.pusher.dropArm();
        }
    }
}
