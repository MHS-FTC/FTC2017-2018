package org.firstinspires.ftc.teamcode.Autonomous.holding;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Cryptograms;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.DriveTime;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Created by ethan.hampton on 12/4/2017.
 * Straight autonomous that simply drives straight and parks
 */

@Autonomous(name = "Cryptogram", group = "testing")
@Disabled
public class CryptogramAutonomous extends OpMode {
    private AutonomousBase auto = new AutonomousBase();
    private Robot bot;
    private final Module[][] steps = new Module[][]{
         {new Cryptograms()},
          //  {new EncoderDrive().setDistances(12,12).setSpeed(0.3)},
            {new DriveTime().setSpeeds(.5,0,0).setTime(1000), new DriveTime().setSpeeds(.5,0,0).setTime(1000), new DriveTime().setSpeeds(.5,0,0).setTime(1000)},
            {new Wait().setWaitTime(1000)},
            {new DriveTime().setSpeeds(0,0,0.6).setTime(600), new DriveTime().setSpeeds(0,0,0.6).setTime(800), new DriveTime().setSpeeds(0,0,0.6).setTime(1000)},//the three possible positions for the cryptobox
            {new DriveTime().setSpeeds(0.5,0,0).setTime(200)},// TODO: 1/25/2018 Download and test.
            //{new EncoderDrive().setSpeed(Constants.DEFAULT_SPEED).setDistances(6,6)},
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
