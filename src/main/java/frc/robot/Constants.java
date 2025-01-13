// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
  public static class GrabberConstants{
    public static final double KI = 0;
    public static final double KP = 0;
    public static final double KD = 0;

    public enum PivotStates{


      kGround(0.23),
      kFull(0.057);
      private double pivotSetPoint;
      private PivotStates(double pivotSetPoint){
      this.pivotSetPoint = pivotSetPoint;
      }
      public double getPivotSetPoint() {
        return pivotSetPoint;
      }
    }

  }
  public static class WinchConstants {
    public static final double KI = 0;
    public static final double KP = 0;
    public static final double KD = 0;
    // Winch motor and encoder constants
    public static final int kWinchPort = 0;
    public static final double kWinchP = 0.001;

    // Define states for winch
    public enum WinchStates {

      kGround(0.0),
      kHalf(1.0),
      kFull(1.5);

      private final double winchSetPoint;
      
      private WinchStates(double winchSetPoint) {
        this.winchSetPoint = winchSetPoint;
      }

      public double getWinchSetPoint() {
        return winchSetPoint;
      }
    }

    // Placeholder for encoder to radians conversion
    public static double kEncoderToRadians = 0.0; // Adjust as needed
  }

  public static class TankDriveConstants {
    public static final double kdriveConversionFactor = 2 * Math.PI * 2;
  }

  // Additional constants can be added here if necessary
}