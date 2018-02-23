
package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Cryptograms;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.DriveTime;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.EncoderDrive;
import org.firstinspires.ftc.teamcode.Autonomous.Modules.Wait;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.AutonomousBase;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.CallFunction;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.Robot.Robot;

/**
 * Created by byron.nice on 2/19/2018.
 * Does Red1 Cryptogram
 */

@Autonomous(name = "RED1: Cryptogram", group = "Red")
public class CryptogramRed1 extends OpMode {
    private AutonomousBase auto = new AutonomousBase();
    private Robot bot;
    private final Module[][] steps = new Module[][]{
            {new Cryptograms()}, //Decrypts Cryptogram
            {new CallFunction().setFunction(()-> bot.forklift.close())}, //Close claws
            {new Wait().setWaitTime(300)},//Wait
            {new CallFunction().setFunction(()-> bot.forklift.raise(0.4))}, //Raise Forklift
            {new Wait().setWaitTime(500)},//Wait
            {new CallFunction().setFunction(()-> bot.forklift.raise(0.0))},//Stop Forklift
            {new Wait().setWaitTime(500)},//Waits
            {
                    new EncoderDrive().setDistances(-45,-45).setSpeed(0.5),//LEFT 26
                    new EncoderDrive().setDistances(-37,-37).setSpeed(0.5),//Center
                    new EncoderDrive().setDistances(-29,-29).setSpeed(0.5),//Right
            },//the three possible positions for the cryptobox
            {new EncoderDrive().setDistances(-17,17).setSpeed(0.3)},
            {new Wait().setWaitTime(300)},
            {new EncoderDrive().setDistances(10,10).setSpeed(0.3)},//Move Forward
            {new CallFunction().setFunction(() -> bot.forklift.open())}, //Open claws
            {new Wait().setWaitTime(300)},//wait to open before continuing back
            {new EncoderDrive().setDistances(-5,-5).setSpeed(0.3)},
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