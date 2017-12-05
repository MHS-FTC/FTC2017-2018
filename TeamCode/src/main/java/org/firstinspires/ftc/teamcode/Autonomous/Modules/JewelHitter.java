package org.firstinspires.ftc.teamcode.Autonomous.Modules;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.FTC_API.Autonomous.Modules.Module;
import org.firstinspires.ftc.teamcode.FTC_API.Options;
import org.firstinspires.ftc.teamcode.Robot.SubSystems.JewelPusher;
import org.firstinspires.ftc.teamcode.Utilitys.Constants;
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
    private JewelPusher j = (JewelPusher) robot.getSubSystem(JewelPusher.ID);

    private Mode currentMode = Mode.UNKNOWN;

    OpenGLMatrix lastLocation = null;

    /**
     * vuforia is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;
    VuforiaTrackable relicTemplate;

    /**
     * @param team what team we are on
     */
    private void hitCorrectBall(Team team) {

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

    private void startDetection() {
        //int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();//Simply add camera view id to display camera vision on screen


        parameters.vuforiaLicenseKey = Constants.VUFORIA_LICENCE_KEY;

        /*
         * We also indicate which camera on the RC that we wish to use.
         * Here we chose the back (HiRes) camera (for greater range), but
         * for a competition robot, the front camera might be more convenient.
         */
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        /*
         * Load the data set containing the VuMarks for Relic Recovery. There's only one trackable
         * in this data set: all three of the VuMarks in the game were created from this one template,
         * but differ in their instance id information.
         */
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();
    }

    public Direction detectPosition() {

        /*
         * See if any of the instances of relicTemplate are currently visible.
         * RelicRecoveryVuMark is an enum which can have the following values:
         * UNKNOWN, LEFT, CENTER, and RIGHT. When a VuMark is visible, something other than
         * UNKNOWN will be returned by RelicRecoveryVuMark.
         */
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                /* Found an instance of the template. In the actual game, you will probably
                 * loop until this condition occurs, then move on to act accordingly depending
                 * on which VuMark was visible. */
            // robot.telemetry.addData("VuMark", "%s visible", vuMark);


                /* For fun, we also exhibit the navigational pose. In the Relic Recovery game,
                 * it is perhaps unlikely that you will actually need to act on this pose information, but
                 * we illustrate it nevertheless, for completeness. */
            OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();
            //telemetry.addData("Pose", format(pose));

                /* We further illustrate how to decompose the pose into useful rotational and
                 * translational components */
            if (pose != null) {
                VectorF trans = pose.getTranslation();
                Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                // Extract the X, Y, and Z components of the offset of the target relative to the robot
                double tX = trans.get(0);
                double tY = trans.get(1);
                double tZ = trans.get(2);

                // Extract the rotational components of the target relative to the robot
                double rX = rot.firstAngle;
                double rY = rot.secondAngle;
                double rZ = rot.thirdAngle;
            }

            switch (vuMark) {
                case LEFT:
                    return Direction.LEFT;
                case CENTER:
                    return Direction.MIDDLE;
                case RIGHT:
                    return Direction.RIGHT;
            }
        } else {
            //telemetry.addData("VuMark", "not visible");
            return Direction.UNKNOWN;
        }

        // telemetry.update();

        return Direction.UNKNOWN;
    }

    @Override
    public void start() {
        if (currentMode == Mode.HIT_MODE) {
            j.dropArm();
        } else if (currentMode == Mode.DETECT_MODE) {
            startDetection();
        }
    }


    @Override
    public void tick() {
        hitCorrectBall(Team.valueOf(options().get("team")));
    }

    public void setMode(Mode mode) {
        this.currentMode = mode;
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

    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }

    enum Mode {
        DETECT_MODE, HIT_MODE, UNKNOWN
    }
}
