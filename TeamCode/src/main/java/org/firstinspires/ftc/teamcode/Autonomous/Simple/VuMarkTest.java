
package org.firstinspires.ftc.teamcode.Autonomous.Simple;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Modules.DetectVuMark;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.DriveTime;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Created by byron.nice on 1/1/2018.
 * ByronAuto follows set steps to complete program.
 */
@Disabled
@Autonomous(name = "VuMark Test", group = "production")
public class VuMarkTest extends OpMode
{
    private AutonomousBase auto = new AutonomousBase();
    private Robot bot;

    private final DriveTime defaultDrive = new DriveTime().setSpeeds(0.30, 0d, 0d).setTime(500);
    private final Module[][] steps = new Module[][]{
            {new DetectVuMark()},
            {defaultDrive.setSpeeds(-0.3, 0, 0), defaultDrive.setSpeeds(0.3, 0.5, 0), defaultDrive}, //Move forward
    };

    @Override
    public void init()
    {
        bot = new Robot();
        auto.init(hardwareMap, bot, steps);
    }

    @Override
    public void loop()
    {
        auto.loop();

        telemetry.addData("Test", bot.drive.isFunctioning());//Add telemetry
    }
}
