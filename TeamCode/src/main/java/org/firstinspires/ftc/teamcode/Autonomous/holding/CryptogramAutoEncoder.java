
package org.firstinspires.ftc.teamcode.Autonomous.holding;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.Disabled;
        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import org.firstinspires.ftc.teamcode.Autonomous.Modules.Cryptograms;
        import org.firstinspires.ftc.teamcode.Autonomous.Modules.DriveTime;
        import org.firstinspires.ftc.teamcode.Autonomous.Modules.EncoderDrive;
        import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
        import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
        import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
        import org.firstinspires.ftc.teamcode.Robot.Robot;
        import org.firstinspires.ftc.teamcode.Utilitys.Constants;

/**
 * Created by byron.nice on 2/5/2018.
 * Straight autonomous that simply drives straight and parks
 */

@Autonomous(name = "CryptogramEncoder", group = "testing")
public class CryptogramAutoEncoder extends OpMode {
    private AutonomousBase auto = new AutonomousBase();
    private Robot bot;
    private final Module[][] steps = new Module[][]{
            {new Cryptograms()},
            {new EncoderDrive().setDistances(24,24).setSpeed(0.4)},
            {new Wait().setWaitTime(1000)},
            {
                    new DriveTime().setSpeeds(0,0,0.6).setTime(600),
                    new DriveTime().setSpeeds(0,0,0.6).setTime(800),
                    new DriveTime().setSpeeds(0,0,0.6).setTime(1000)
            },//the three possible positions for the cryptobox
            {new EncoderDrive().setDistances(9,9).setSpeed(0.3)},
            {new Wait()},
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
