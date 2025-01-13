// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class WinchConstants {
    public static final double kEncoderConversionFactor = 2 * Math.PI;
    public static final double kP = 0.002;
    public static final double kI = 0.0;
    public static final double kD = 0.0;

    public enum WinchStates{
      kBottom(-250),
      kMiddle(-139),
      kTop(0);

      private double winchSetpoint;

      private WinchStates(double winchSetpoint){
        this.winchSetpoint = winchSetpoint;
      }

      public double getWinchSetpoint() {
        return winchSetpoint;
      }
    }
  }

  public static class GrabberConstants {
    public static final double kEncoderConversionFactor = 2 * Math.PI;
    public static final double kP = 0.002;
    public static final double kI = 0.0;
    public static final double kD = 0.0;

    public enum GrabberStates {
      kLow(0.23),
      kMid(10),
      kHigh(0.75);

      private double grabberSetPoint;

      private GrabberStates(double grabberSetPoint){
        this.grabberSetPoint=grabberSetPoint;
      }

      public double getGrabberSetPoint() {
        return grabberSetPoint;
      }
    }
  }

  public static class TankDriveBaseConstants {
    public static final double kEncoderConversionFactor = 2 * Math.PI;
    public static final double kWheelDiameterInches = 6;
    public static final double kWheelDiameterMeters = Units.inchesToMeters(kWheelDiameterInches);
    public static final double kGearRatio = 8.45;
  }

}
