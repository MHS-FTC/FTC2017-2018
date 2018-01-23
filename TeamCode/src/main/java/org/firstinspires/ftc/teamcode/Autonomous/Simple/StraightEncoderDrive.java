
package org.firstinspires.ftc.teamcode.Autonomous.Simple;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Modules.EncoderDrive;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Created by byron.nice on 1/1/2018.
 * ByronAuto follows set steps to complete program.
 */

@Autonomous(name = "StraightEncoderDrive", group = "production")
public class StraightEncoderDrive extends OpMode {
    private AutonomousBase auto = new AutonomousBase();
    private Robot bot;
    private final Module[][] steps = new Module[][]{
            {new EncoderDrive().setSpeed(0.30).setDistances(12,12)}, //Move forward
            {new Wait().setWaitTime(1000)},
            {new EncoderDrive().setSpeed(0.30).setDistances(12,12)}, //Move forward


    };

    @Override
    public void init() {
        bot = new Robot();
        auto.init(hardwareMap, bot, steps);
    }

    @Override
    public void loop() {
        auto.loop();

        telemetry.addData("Test", bot.drive.isFunctioning());//Add telemetry
    }
}
