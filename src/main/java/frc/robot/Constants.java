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

  public static class GrabberWinchConstants{

    //dummy values (all in this class)

    public static final int kPivotPort = 1;
    public static final int kIntakePort = 2;

    public static final double kPivotP = 0.002;
    public static final double kWinchP = 0.002;

    public static final int kWinchPort = 5;
    
    
    public enum GrabberWinchStates {

      kGround(0.0, 0.01),
      kRaised(1.5, 0),
      kLowered(1.0, 0);

      private double grabberVal;
      private double winchVal;

      private GrabberWinchStates(double grabberVal, double winchVal){
        this.grabberVal = grabberVal;
        this.winchVal = winchVal;
      }

      public double getGrabberVal(){
        return grabberVal;
      }

      public double getWinchVal(){
        return winchVal;
      }
    }

    public static double kEncoderToRadians;
  }

  public static class TankDrivebaseConstants{
    public static final int kMotorLFPort = 7;
    public static final int kMotorRFPort = 4;
    public static final int kMotorLBPort = 8;
    public static final int kMotorRBPort = 6; 
  }

  public static final double RotToMeters = 2 * Math.PI;
}