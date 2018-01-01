package org.firstinspires.ftc.teamcode.Robot.SubSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.FTC_API.Options;
import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SubSystem;
import org.firstinspires.ftc.teamcode.Utilitys.Constants;

/**
 * Created by Ethan Hampton on 1/1/18.
 * <p>
 * Grabs the relic and controls it
 */

public class RelicGrabber extends SubSystem {
    private static final String ID = "RelicGrabber";
    private Options options = new Options(ID);


    private Servo extender;
    private Servo claw;
    private Servo rotate;

    @Override
    public boolean init(HardwareMap hardwareDevices) {
        extender = hardwareDevices.servo.get(options.get("extender"));
        claw = hardwareDevices.servo.get(options.get("claw"));
        rotate = hardwareDevices.servo.get(options.get("rotate"));
        return true;
    }

    public RelicGrabber setMotorNames(String extender, String claw, String rotate) {
        options.add("extender", extender);
        options.add("claw", claw);
        options.add("rotate", rotate);
        return this;
    }

    /**
     * @param speed the speed from -1 to 1 inclusive, 0 to stop
     */
    public void extendClaw(double speed) {
        if (speed == 0) {//if we want to stop moving, then set position to 0.5 which stops servo
            extender.setPosition(0.5);
        } else if (speed > 0) {//This code scales the values from -1 to 1 not including 0 to something the servo can understand (0 to 1)
            speed = Range.scale(speed, 0, 1, 0.5, 1);
            extender.setPosition(speed);
        } else if (speed < 0) {
            speed = Range.scale(speed, -1, 0, 0, 0.5);
            extender.setPosition(speed);
        }
    }

    public void rotate(double position) {
        rotate.setPosition(position);
    }

    public void closeClaw() {
        claw.setPosition(Constants.RELIC_GRABBER_CLOSE_POSITION);
    }

    public void openClaw() {
        claw.setPosition(Constants.RELIC_GRABBER_OPEN_POSITION);
    }

    @Override
    public Options options() {
        return options;
    }

}
