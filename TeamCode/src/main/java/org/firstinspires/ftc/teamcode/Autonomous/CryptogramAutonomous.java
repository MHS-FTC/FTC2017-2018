package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Modules.Cryptograms;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.DriveTime;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Utilitys.Constants;

/**
 * Created by ethan.hampton on 12/4/2017.
 * Straight autonomous that simply drives straight and parks
 */

@Autonomous(name = "Cryptogram", group = "testing")
public class CryptogramAutonomous extends OpMode {
    private AutonomousBase auto = new AutonomousBase();
    private Robot bot;
    private final Module[][] steps = new Module[][]{
            {new Cryptograms()},
            {new DriveTime().setTime(1000), new DriveTime().setTime(1500), new DriveTime().setTime(2000)},//the three possible positions for the cryptobox
            {new DriveTime().setTime(500).setSpeeds(0, Constants.DEFAULT_SPEED)},//turn 90 degrees to line up
            {new DriveTime().setTime(500)},//drive forward into cryptobox
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
