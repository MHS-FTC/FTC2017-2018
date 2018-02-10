
package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Cryptograms;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.DriveTime;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.EncoderDrive;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.CallFunction;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;
import org.firstinspires.ftc.teamcode.Utilitys.Constants;

/**
 * Created by byron.nice on 2/5/2018.
 * Straight autonomous that simply drives straight and parks
 */

@Autonomous(name = "CryptogramRed", group = "testing")
public class CryptogramRed extends OpMode {
    private AutonomousBase auto = new AutonomousBase();
    private Robot bot;
    private final Module[][] steps = new Module[][]{
            {new Cryptograms()}, //Decrypts Cryptogram
            {new CallFunction().setFunction(()-> bot.forklift.close())}, //Close claws
            {new Wait().setWaitTime(300)},//Wait
            {new CallFunction().setFunction(()-> bot.forklift.raise(0.4))}, //Raise Forklift
            {new Wait().setWaitTime(500)},//Wait
            {new CallFunction().setFunction(()-> bot.forklift.raise(0.0))},//Stop Forklift
            {new EncoderDrive().setDistances(24,24).setSpeed(0.4)},//Moves forward
            {new Wait().setWaitTime(1000)},//Waits
            {
                    new DriveTime().setSpeeds(0,0,0.6).setTime(550),
                    new DriveTime().setSpeeds(0,0,0.6).setTime(950),
                    new DriveTime().setSpeeds(0,0,0.6).setTime(1400),
            },//the three possible positions for the cryptobox
            {new EncoderDrive().setDistances(9,9).setSpeed(0.3)},//Move Forward
            {new CallFunction().setFunction(() -> bot.forklift.open())}, //Open claws
            {new Wait().setWaitTime(300)},//wait to open before continuing back
            {new DriveTime().setSpeeds(-0.2,0,0).setTime(600)},//Moves back
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
