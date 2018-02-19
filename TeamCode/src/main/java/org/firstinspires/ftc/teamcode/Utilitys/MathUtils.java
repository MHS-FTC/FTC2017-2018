package org.firstinspires.ftc.teamcode.Utilitys;

import com.qualcomm.robotcore.util.Range;

/**
 * Created by ethan.hampton on 2/19/2018.
 * <p>
 * This will drastically simplify some of our math needs
 */

public class MathUtils {
    public static double scaleThoughZero(double num, double inputMinimum, double inputMaximum, double outputMinimum, double outputMaximum) {
        if (num >= 0) {//if input is greater than zero then go from 0 to maximum
            return Range.scale(num, 0, inputMaximum, 0, outputMaximum);
        } else {//if it is less than zero then go from minimum to 0
            return Range.scale(num, inputMinimum, 0, outputMinimum, 0);
        }
    }


    public static class DeltaTracker {
        double current;
        double minimum;
        double maximum;
        double maxDelta;

        /**
         * @param minimum  Minimum value of the tracked value
         * @param maximum  Maximum value of the tracked value
         * @param start    The start value
         * @param maxDelta maximum change allowed
         */
        public DeltaTracker(double minimum, double maximum, double start, double maxDelta) {
            current = start;
            this.maximum = maximum;
            this.minimum = minimum;
            this.maxDelta = maxDelta;
        }

        /***
         * This changes the current value with an input. This function assumes that the value is zero based,
         * meaning when it is 0, the current value should change by zero
         * @param input the input
         * @param inputMinimum minimum value of input
         * @param inputMaximum maximum value of input
         * @return the current position
         */
        public double delta(double input, double inputMinimum, double inputMaximum) {
            current += scaleThoughZero(input, inputMinimum, inputMaximum, -maxDelta, maxDelta);//add or subtract value
            current = Range.clip(current, minimum, maximum);//clip value so it does not get too big or small
            return current;
        }


        public double getCurrent() {
            return current;
        }

        public void setCurrent(double current) {
            this.current = current;
        }

        public double getMaxDelta() {
            return maxDelta;
        }

        public void setMaxDelta(double maxDelta) {
            this.maxDelta = maxDelta;
        }
    }
}
