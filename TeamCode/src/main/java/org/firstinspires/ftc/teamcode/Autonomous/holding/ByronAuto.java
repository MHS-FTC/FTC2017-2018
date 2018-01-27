package org.firstinspires.ftc.teamcode.Autonomous.holding;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.DriveTime;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.CallFunction;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Created by byron.nice on 1/1/2018.
 * ByronAuto follows set steps to complete program.
 */

@Autonomous(name = "ByronAuto", group = "production")
@Disabled
public class ByronAuto extends OpMode {
    private AutonomousBase auto = new AutonomousBase();
    private Robot bot;
    private final Module[][] steps = new Module[][]{
            {new CallFunction().setFunction(()-> bot.forklift.close())}, //Close claws
            {new CallFunction().setFunction(()-> bot.forklift.raise(0.4))}, //Raise Forklift
            {new Wait().setWaitTime(100)},
            {new CallFunction().setFunction(()-> bot.forklift.raise(0.0))}, //Stop Forklift
            {new DriveTime().setSpeeds(0.30, 0, 0).setTime(700)}, //Move forward
            {new Wait().setWaitTime(1000)}, //Wait
            {new DriveTime().setSpeeds(0.00, 0, 0.8).setTime(500)}, //Strafe right
            {new Wait()}


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
