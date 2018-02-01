package org.firstinspires.ftc.teamcode.Robot.SubSystems;

import android.graphics.Color;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.FTC_API.Options;
import org.firstinspires.ftc.teamcode.FTC_API.Robot.SubSystems.SubSystem;
import org.firstinspires.ftc.teamcode.Utilitys.Constants;
import org.firstinspires.ftc.teamcode.Utilitys.Direction;
import org.firstinspires.ftc.teamcode.Utilitys.Team;

/**
 * Created by ethan.hampton on 10/18/2017.
 * <p>
 * Subsystem for jewel hitter that will include two servos and color sensor
 */

public class JewelPusher extends SubSystem {
    public static final String ID = "JewelPusher";
    private Options options = new Options(ID);

    private Servo rotate;
    private Servo hitter;
    private ColorSensor color;
    private LED led;

    // colorValues is an array that will hold the hue, saturation, and value information.
    private float colorValues[] = {0F, 0F, 0F};

    @Override
    public boolean init(HardwareMap hardwareDevices) {
        rotate = hardwareDevices.servo.get(options.get("rotate"));
        hitter = hardwareDevices.servo.get(options.get("hitter"));
        color = hardwareDevices.colorSensor.get(options.get("color"));
        led = hardwareDevices.led.get(options.get("led"));
        turnOffLED();
        hit(Direction.MIDDLE);
        liftArm();
        return true;
    }

    public JewelPusher setHardwareNames(String rotate, String hitter, String color, String led) {
        options.add("rotate", rotate);
        options.add("hitter", hitter);
        options.add("color", color);
        options.add("led", led);
        return this;
    }

    public void dropArm() {
        hitter.setPosition(0.85);
    }

    public void liftArm() {
        hitter.setPosition(0.01);
    }

    public void turnOnLED() {
        led.enableLight(true);
    }

    public void turnOffLED() {
        led.enableLight(false);
    }

    public void hit(Direction direction) {
        switch (direction) {
            case LEFT:
                rotate.setPosition(Constants.JEWEL_LEFT);
                return;
            case RIGHT:
                rotate.setPosition(Constants.JEWEL_RIGHT);
                return;
            case MIDDLE:
                rotate.setPosition(Constants.JEWEL_MIDDLE);
                return;
            case UNKNOWN:
                return;
            default:
        }
    }

    // TODO: 1/31/2018 Test hue dead zone
    public Direction whereToHit(Direction currentlyReading, Team team) {
        Color.RGBToHSV((color.red() * 255) / 800, (color.green() * 255) / 800, (color.blue() * 255) / 800, colorValues);

        /* is true if the color sensor is less than 60 hue or greater than 300 then it is probably red,
         implies that the ball it is facing at is red */
        boolean isRed = (colorValues[0] < 30 || colorValues[0] > 330);//about a 30 number
        boolean isInvalid = (colorValues[0] > 30 && colorValues[0] < 180)
                || (colorValues[0] > 290 && colorValues[0] < 330);//between 30 and 180 is definitely invalid, as is between 290 and 330

        if (!isInvalid) {//insure not in invalid range, in which case we are not sure
            if (team.equals(Team.RED_TEAM)) {
                //if we are looking at a red ball and we are on the red team, hit the other one
                if (isRed) {
                    return Direction.getOpposite(currentlyReading);
                } else {
                    //if we are on red and we see blue, hit it
                    return currentlyReading;
                }
            } else if (team.equals(Team.BLUE_TEAM)) {
                //if we are looking at a blue ball and we are on the blue team, hit the other one
                if (!isRed) {
                    return Direction.getOpposite(currentlyReading);
                } else {
                    //if we are on blue and we see red, hit it
                    return currentlyReading;
                }
            }
            return Direction.UNKNOWN;
        } else {
            return Direction.MIDDLE;//return middle if we are not sure it is a color
        }
    }

    public void reset() {
        hit(Direction.MIDDLE);
        liftArm();
    }

    public String colorSensorReadable() {
        return "Red:" + color.red() + " Green:" + color.green() + " Blue:" + color.blue() + " Hue:" + colorValues[0];
    }

    @Override
    public Options options() {
        return options;
    }
}
