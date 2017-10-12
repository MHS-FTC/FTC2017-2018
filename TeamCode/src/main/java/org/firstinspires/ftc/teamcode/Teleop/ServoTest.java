package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by ethan.hampton on 10/11/2017.
 * <p>
 * Tests two servos
 */
@TeleOp(name = "ServoTest", group = "test")
public class ServoTest extends OpMode {

    private Servo one;
    private Servo two;

    @Override
    public void init() {
        one = hardwareMap.servo.get("one");
        two = hardwareMap.servo.get("two");
    }

    @Override
    public void loop() {
        one.setPosition(gamepad1.left_stick_y);
        two.setPosition(gamepad1.left_stick_y);
    }
}
