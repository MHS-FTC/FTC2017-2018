package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.FTC_API.Options;

/**
 * Created by ethan.hampton on 11/17/2017.
 * Designed to wait for a certain amount of time
 */

public class Wait extends Module {
    private double startTime;
    private long waitTime;

    public Wait setWaitTime(long waitTime) {
        this.waitTime = waitTime;
        return this;
    }

    @Override
    public void start() {
        startTime = robot.getTimeMilliseconds();
    }

    @Override
    public void tick() {

    }

    @Override
    public Options options() {
        return new Options("Wait");
    }

    @Override
    public boolean isDone() {
        return (startTime + waitTime) < robot.getTimeMilliseconds();
    }

    @Override
    public String[] requiredSubSystems() {
        return new String[0];
    }
}
