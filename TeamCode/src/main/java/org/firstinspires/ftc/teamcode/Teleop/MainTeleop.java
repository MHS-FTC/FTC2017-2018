package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Robot.SubSystems.Forklift;
import org.firstinspires.ftc.teamcode.Utilitys.Direction;
import org.firstinspires.ftc.teamcode.Utilitys.MathUtils;


/**
 * Created by ethan.hampton on 9/19/2017.
 * <p>
 * Simple teleop drive
 */

@TeleOp(name = "Teleop", group = "production")
public class MainTeleop extends OpMode {
    private Robot robot = new Robot();

    //private double relicClawPosition = 0.07;//stores the position of the relic claw
    private MathUtils.DeltaTracker relicClawPositionTracker = new MathUtils.DeltaTracker(0.02, 0.9, 0.07, 0.05);
    private double relicClawRotation = 1;//1 is the downward position

    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.start();
        //robot.jewel.rotate.setPosition(0.38);
    }

    @Override
    public void loop() {
        robot.tick();

        //drive the robot (in order, forward backward, rotate)
        //NOTE: Y direction of joysticks needs to be reversed
        robot.drive.driveMecanum(-gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);


        //control forklift from controller 1
        if (gamepad1.dpad_up || gamepad2.dpad_up) {
            robot.forklift.raise(0.9);
        } else if (gamepad1.dpad_down || gamepad2.dpad_down) {
            robot.forklift.raise(-0.9);
        } else {
            robot.forklift.raise(0);
        }


        double extendInput = -gamepad2.left_stick_y;
        robot.relicGrabber.extendClaw(MathUtils.scaleThoughZero(extendInput, -1, 1, -0.70, 1));
        /*
        //robot.relicGrabber.extendClaw(Range.scale(extendInput, -1, 1, -0.85, 0.85));//extend the claw out based on left joystick. This sets speed
        if (extendInput >= 0) {//should extend
            robot.relicGrabber.extendClaw(Range.scale(extendInput, 0, 1, 0, 0.85));//extend the claw
        } else {//must be less than zero, should retract
            robot.relicGrabber.extendClaw(Range.scale(extendInput, -1, 0, -0.55, 0));//extend the claw
        }*/


        relicClawPositionTracker.delta(-gamepad2.right_stick_y, -1, 1);
        //relicClawPosition += Range.scale(-gamepad2.right_stick_y, -1, 1, -0.05, 0.05);//scales the joystick to something reasonable for controlling the joystick.

        //if the claws position is out of bounds then prevent that from happening
        //Note that I purposely am not going to the limits of the servo(0.95 instead of 1) so we don't break the servo
        //relicClawPosition = Range.clip(relicClawPosition,0.02,0.9);
        /*
        if (relicClawPosition > 0.95) {
            relicClawPosition = 0.9;
        }
        if (relicClawPosition < 0.02) {
            relicClawPosition = 0.02;
        }
        */
        // TODO: 1/5/2018 Make this follow a square(?) scale for more precision
        robot.relicGrabber.setClawPosition(relicClawPositionTracker.getCurrent());//sets the actual claw position

        //TODO: Replace this with DeltaTracker
        if (gamepad2.left_bumper) {//up
            relicClawRotation -= 0.007;
        }
        if (gamepad2.right_bumper) {//down
            relicClawRotation += 0.007;
        }
        //clip the rotation of the claw
        relicClawRotation = Range.clip(relicClawRotation, 0.02, 1);
        /*
        if (relicClawRotation > 1) {
            relicClawRotation = 1;
        }
        if (relicClawRotation < 0.02) {
            relicClawRotation = 0.02;
        }*/
        robot.relicGrabber.rotate(relicClawRotation);//Rotates the claw so it begins down and goes from there


        //controls both sets of claws with first controller
        if (gamepad1.right_bumper) {
            robot.forklift.setForkPosition(Direction.TOP, Forklift.Position.FULL_OPEN);
        } else if (gamepad1.left_bumper) {
            robot.forklift.setForkPosition(Direction.BOTTOM, Forklift.Position.FULL_OPEN);
        } else if (gamepad1.b) {
            robot.forklift.openHalfAll();
        }


        robot.jewel.hit(Direction.RIGHT);//store servo
    }
}
