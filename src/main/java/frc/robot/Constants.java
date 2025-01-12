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
  public class PivotConstants {
    public enum PivotStates {
      kground1(0),
      kMid1(5),
      kHigh1(9.5);
    private double pivotSetPoint;
    private PivotStates(double pivotSetPoint){
      this.pivotSetPoint=pivotSetPoint;
    } public double getPivotSetPoint(){
        return pivotSetPoint;
  
      }
    
    
    }
    
    public enum PivotSpeeds{
      kSlow(0.001),
      kMedium(0.01),
      kFast(0.1);
      private double pivotSpeed;
      private PivotSpeeds(double pivotSpeed){
        this.pivotSpeed=pivotSpeed;
      }
      public double getPivotSpeed(){
        return pivotSpeed;
      }
    }
  }}
