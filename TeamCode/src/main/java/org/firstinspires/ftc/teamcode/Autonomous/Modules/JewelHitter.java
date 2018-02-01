package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.FTC_API.Options;
import org.firstinspires.ftc.teamcode.Robot.SubSystems.JewelPusher;
import org.firstinspires.ftc.teamcode.Utilitys.Direction;
import org.firstinspires.ftc.teamcode.Utilitys.Team;

/**
 * Created by ethan.hampton on 11/17/2017.
 * Designed to wait for a certain amount of time
 */

public class JewelHitter extends Module {
    private JewelPusher jewel;
    private boolean isDone = false;
    private double startTime;
    private Team team = Team.RED_TEAM;//by default is red team, ALWAYS override
    private boolean hasHit = false;

    @Override
    public void start() {
        jewel = (JewelPusher) robot.getSubSystem(JewelPusher.ID);
        jewel.dropArm();//drop the arm

        startTime = robot.getTimeMilliseconds();
    }

    /*
    Wait 2 for arm to drop
    Detect and hit based on color sensor
    Wait 2 for hit
    Reset Hit
    Wait 0.5
    Raise arm
    Wait 1
     */
    @Override
    public void tick() {
        double delta = (robot.getTimeMilliseconds() - startTime) / 1000;//The time (in seconds) this module has been running for
        if (delta > 5) {
            isDone = true;//we are done
        } else if (delta > 4) {
            //raise arm and reset hit
            jewel.liftArm();
            jewel.hit(Direction.MIDDLE);
        } else if (delta > 2) {
            if (!hasHit) {
                //detect and hit based on color sensor
                jewel.turnOnLED();
                Direction hit = jewel.whereToHit(Direction.LEFT, team);
                jewel.hit(hit);//hit the right jewel
                jewel.turnOffLED();
                hasHit = true;//the servo should have hit, only run once
            }
        }
    }

    public JewelHitter setTeam(Team team) {
        this.team = team;
        return this;
    }

    @Override
    public Options options() {
        return new Options("JewelHitter");
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String[] requiredSubSystems() {
        return new String[0];
    }
}
