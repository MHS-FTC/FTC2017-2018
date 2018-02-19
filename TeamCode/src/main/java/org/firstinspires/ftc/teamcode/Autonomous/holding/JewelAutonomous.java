package org.firstinspires.ftc.teamcode.Autonomous.holding;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.JewelHitter;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Utilitys.Team;

/**
 * Created by ethan.hampton on 12/4/2017.
 * Straight autonomous that simply drives straight and parks
 */

@Autonomous(name = "TEST: Jewels", group = "TEST")
@Disabled
public class JewelAutonomous extends OpMode {
    private AutonomousBase auto = new AutonomousBase();
    private Robot bot;
    private final Module[][] steps = new Module[][]{
            {new JewelHitter().setTeam(Team.RED_TEAM)},
            //{new DriveTime().setSpeeds(0.85, 0, 0).setTime(500)},
            {new Wait().setWaitTime(30000)},
    };

    @Override
    public void init() {
        bot = new Robot();
        auto.setTelemetry(telemetry);
        auto.init(hardwareMap, bot, steps);
    }

    @Override
    public void loop() {
        auto.loop();

        telemetry.addData("Test", bot.drive.isFunctioning());//Add telemetry
    }
}