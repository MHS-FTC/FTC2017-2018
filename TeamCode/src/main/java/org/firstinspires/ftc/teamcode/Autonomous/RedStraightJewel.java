
package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.DriveTime;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.JewelHitter;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Utilitys.Team;

/**
 * Created by byron.nice on 1/1/2018.
 * ByronAuto follows set steps to complete program.
 */

@Autonomous(name = "RED:StraightJewel", group = "production")
public class RedStraightJewel extends OpMode {
    private AutonomousBase auto = new AutonomousBase();
    private Robot bot;
    private final Module[][] steps = new Module[][]{
            {new JewelHitter().setTeam(Team.RED_TEAM)},
            {new DriveTime().setTime(1500).setSpeeds(-0.4, 0, 0)}, //Move back
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