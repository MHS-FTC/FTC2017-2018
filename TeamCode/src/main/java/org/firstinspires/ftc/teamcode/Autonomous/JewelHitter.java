package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.FTC_API.Options;
import org.firstinspires.ftc.teamcode.Robot.SubSystems.JewelPusher;
import org.firstinspires.ftc.teamcode.Utilitys.Direction;
import org.firstinspires.ftc.teamcode.Utilitys.Team;

/**
 * Created by ethan.hampton on 10/19/2017.
 * <p>
 * Autonomous program with the capacity to do hit the right jewels. Needs some work
 */
// TODO: 10/19/2017 Implement class
public class JewelHitter extends Module {
    private Options options = new Options("JewelHitter");
    JewelPusher j = (JewelPusher) robot.getSubSystem(JewelPusher.ID);

    /**
     * @param team what team we are on
     */
    public void hitCorrectBall(Team team) {

        Team leftBall = Team.UNKNOWN;
        // TODO: 10/18/2017 Find correct color to hit and hit it
        if (!(team == Team.BLUE_TEAM || team == Team.RED_TEAM) || !(leftBall == Team.BLUE_TEAM || leftBall == Team.RED_TEAM)) {
            return;//Don't do anything if we don't have proper input values
        }
        if (leftBall.equals(team)) {
            j.hit(Direction.LEFT);
        } else {
            j.hit(Direction.RIGHT);
        }
        //hit(Direction.MIDDLE);
        //liftArm();

    }

    @Override
    public void start() {
        j.dropArm();

    }


    @Override
    public void tick() {
        hitCorrectBall(Team.valueOf(options().get("team")));
    }

    @Override
    public boolean isDone() {
        return false;// TODO: 10/25/2017 How to stop when the servo has moved the correct amount. Will require real world testing.. Ugh
    }

    @Override
    public Options options() {
        return options;
    }


    @Override
    public String[] requiredSubSystems() {
        return new String[0];
    }

    public JewelHitter team(Team team) {
        options().add("team", team.name());
        return this;
    }
}
