package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.*;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Utilitys.Constants;
import org.firstinspires.ftc.teamcode.Utilitys.Team;

/**
 * Created by ethan.hampton on 2/1/2018.
 * <p>
 * Does everything blue
 */
@Autonomous(name = "BLUE: Everything", group = "production")
public class EverythingBlue extends OpMode {
    private AutonomousBase auto = new AutonomousBase();
    private Robot robot;

    private Module[][] modules = {
            {new JewelHitter().setTeam(Team.BLUE_TEAM)},
            {new Cryptograms()},
            //{new DriveTime().setSpeeds(.5, 0, 0).setTime(1000)},
            {new EncoderDrive().setSpeed(0.5).setDistances(25, 25)},
            {new Wait().setWaitTime(1000)},
            {new DriveTime().setSpeeds(0, 0, 0.6).setTime(600), new DriveTime().setSpeeds(0, 0, 0.6).setTime(800), new DriveTime().setSpeeds(0, 0, 0.6).setTime(1000)},//the three possible positions for the cryptobox
            {new Wait().setWaitTime(500)},
            {new DriveTime().setSpeeds(0.5, 0, 0).setTime(200)},
            {new Wait().setWaitTime(500)},
            {new EncoderDrive().setSpeed(Constants.DEFAULT_SPEED).setDistances(-2, -2)},
            {new Wait()}
    };

    @Override
    public void init() {
        robot = new Robot();
        auto.setTelemetry(telemetry);
        auto.init(hardwareMap, robot, modules);
    }

    @Override
    public void loop() {
        telemetry.addData("Time", robot.getTimeMilliseconds());
    }
}
