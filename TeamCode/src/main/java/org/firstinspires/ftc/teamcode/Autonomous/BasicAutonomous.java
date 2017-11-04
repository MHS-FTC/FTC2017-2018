package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Modules.DriveTime;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.CallFunction;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Created by ethan.hampton on 11/3/2017.
 * <p>
 * A simple autonomous that simply moves forward
 */

@Autonomous(name = "Basic Autonomous")
public class BasicAutonomous extends OpMode {
    private AutonomousBase auto = new AutonomousBase();
    private Robot bot = new Robot();
    private final Module[][] steps = new Module[][]{
            {new DriveTime().setSpeeds(0.85, 0).setTime(3500)},
            {new CallFunction().setFunction(() -> bot.drive.drive(0, 0, 0))},
    };

    @Override
    public void init() {
        auto.init(hardwareMap, bot, steps);
    }

    @Override
    public void loop() {
        auto.loop();

        telemetry.addData("Test", "Test");//Add telemetry
    }
}