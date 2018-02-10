package org.firstinspires.ftc.teamcode.Robot.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.FTC_API.Options;
import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SubSystem;

/**
 * Created by Ethan Hampton on 1/1/18.
 * <p>
 * Grabs the relic and controls it
 */

public class RelicGrabber extends SubSystem {
    private static final String ID = "RelicGrabber";
    private Options options = new Options(ID);


    private DcMotor extender;
    private Servo claw;
    private Servo rotate;

    @Override
    public boolean init(HardwareMap hardwareDevices) {
        extender = hardwareDevices.dcMotor.get(options.get("extender"));
        claw = hardwareDevices.servo.get(options.get("claw"));
        rotate = hardwareDevices.servo.get(options.get("rotate"));
        setClawPosition(0.1);
        rotate(0.99);
        return true;
    }

    /**
     * @param extender The servo used to extend the relic grabber
     * @param claw     The actual claw
     * @param rotate   The servo used to rotate the claw
     * @return this object for construction
     */
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
        extender.setPower(speed);
    }

    public void rotate(double position) {
        rotate.setPosition(position);
    }

    public void setClawPosition(double position) {
        claw.setPosition(position);
    }

    @Override
    public Options options() {
        return options;
    }

}
