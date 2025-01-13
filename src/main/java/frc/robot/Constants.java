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

  public static class WinchConstants {

    // Winch motor and encoder constants
    public static final int kWinchPort = 0;
    public static final double kWinchP = 0.001;

    // Define states for winch
    public enum WinchStates {

      kGround(0.0, 0.01),
      kRaised(1.5, 0),
      kLowered(1.0, 0);

      private final double winchVal;
      private final double grabberVal;

      private WinchStates(double winchVal, double grabberVal) {
        this.winchVal = winchVal;
        this.grabberVal = grabberVal;
      }

      public double getWinchVal() {
        return winchVal;
      }

      public double getGrabberVal() {
        return grabberVal;
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