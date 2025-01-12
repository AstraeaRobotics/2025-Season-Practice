// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.TankDrivebase;

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
  
  public static class WinchConstants{

    //dummy values (all in this class)
    
    public static final int kWinchPort = 3;
    public static final double kP = 0.01;

    public enum WinchStates {
      kGround(0.0),
      kTarget(1.0);

      private double encoderVal;

      private WinchStates(double encoderVal){
        this.encoderVal = encoderVal;
      }

      public double getEncoderVal(){
        return encoderVal;
      }

    }

    public static double kEncoderToDegrees = 0.0;
  }

  public static class GrabberConstants{

    //dummy values (all in this class)

    public static final int kPivotPort = 1;
    public static final int kIntakePort = 2;

    public static final double kPivotP = 0.01;
    
    public enum GrabberStates {

      kGround(0.0, 0),
      kIntakeCone(1.0, 1),
      kHoldCone(1.5, 0),
      kLowerCone(1.0, 0),
      kDropCone(0.5, -1);

      private double encoderVal;
      private double intakeSpeed;

      private GrabberStates(double encoderVal, double intakeSpeed){
        this.encoderVal = encoderVal;
        this.intakeSpeed = intakeSpeed;
      }

      public double getEncoderVal(){
        return encoderVal;
      }

      public double getIntakeSpeed(){
        return intakeSpeed;
      }
    }

    public static double kEncoderToRadians;
  }

  public static class TankDrivebaseConstants{
    public static final int kMotorLFPort = 5;
    public static final int kMotorRFPort = 6;
    public static final int kMotorLBPort = 7;
    public static final int kMotorRBPort = 8; 
  }

  public static final double RotToMeters = 0;
}