// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TankDriveBaseConstants;

public class TankDriveBase extends SubsystemBase {
  CANSparkMax leftFrontMotor;
  CANSparkMax leftBackMotor;
  CANSparkMax rightFrontMotor;  
  CANSparkMax rightBackMotor;

  RelativeEncoder leftEncoder;
  RelativeEncoder rightEncoder;

  /** Creates a new TankDriveBase. */
  public TankDriveBase() {
    leftFrontMotor = new CANSparkMax(1, MotorType.kBrushless);
    leftBackMotor = new CANSparkMax(2, MotorType.kBrushless);
    rightFrontMotor = new CANSparkMax(3, MotorType.kBrushless);
    rightBackMotor = new CANSparkMax(4, MotorType.kBrushless);

    leftEncoder = leftFrontMotor.getEncoder();
    rightEncoder = rightFrontMotor.getEncoder();


    configureMotors();
  }

  private void configureMotors() {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  

    leftEncoder.setPositionConversionFactor((TankDriveBaseConstants.kWheelDiameterMeters* Math.PI)/TankDriveBaseConstants.kGearRatio);
    rightEncoder.setPositionConversionFactor((TankDriveBaseConstants.kWheelDiameterMeters* Math.PI)/TankDriveBaseConstants.kGearRatio);
 

    leftFrontMotor.setInverted(false);
    leftBackMotor.setInverted(false);
    rightFrontMotor.setInverted(true);
    rightBackMotor.setInverted(true);
  }

  public void setMotors(double speed) {
    leftFrontMotor.set(speed);
    leftBackMotor.set(speed);
    rightFrontMotor.set(speed);
    rightBackMotor.set(speed);
  }

  public void moveMotors(double leftSpeed, double rightSpeed) {
    leftFrontMotor.set(leftSpeed);
    leftBackMotor.set(leftSpeed);
    rightFrontMotor.set(rightSpeed);
    rightBackMotor.set(rightSpeed);
  } 

  public double getLeftEncoderPosition() {
    return leftEncoder.getPosition();
  }

  public double getRightEncoderPosition() {
    return rightEncoder.getPosition();
  }

  public void curveDrive (double xSpeed, double turn, boolean turnInPlace){
    var speeds = DifferentialDrive.curvatureDriveIK(xSpeed, turn, turnInPlace);
    moveMotors(speeds.left, speeds.right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
