package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Modules.DriveTime;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.EncoderDrive;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Created by ethan.hampton on 12/4/2017.
 * Straight autonomous that simply drives straight and parks
 */

@Autonomous(name = "ALL: Straight AND BACK", group = "production")
public class StraightBack extends OpMode {
    private AutonomousBase auto = new AutonomousBase();
    private Robot bot;
    private final Module[][] steps = new Module[][]{
            {new EncoderDrive().setSpeed(0.3).setDistances(33, 33)},
            {new DriveTime().setSpeeds(-0.3,0,0).setTime(300)},
            {new Wait()},
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
