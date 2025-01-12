// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TankDriveBaseConstants;

public class TankDriveBase extends SubsystemBase {
  CANSparkMax leftMotor1;
  CANSparkMax leftMotor2;
  CANSparkMax rightMotor1;  
  CANSparkMax rightMotor2;

  RelativeEncoder leftEncoder1;
  RelativeEncoder rightEncoder1;
  RelativeEncoder leftEncoder2;
  RelativeEncoder rightEncoder2;

  /** Creates a new TankDriveBase. */
  public TankDriveBase() {
    leftMotor1 = new CANSparkMax(0, MotorType.kBrushless);
    leftMotor2 = new CANSparkMax(0, MotorType.kBrushless);
    rightMotor1 = new CANSparkMax(0, MotorType.kBrushless);
    rightMotor2 = new CANSparkMax(0, MotorType.kBrushless);

    leftEncoder1 = leftMotor1.getEncoder();
    rightEncoder1 = rightMotor1.getEncoder();


    configureMotors();
  }

  private void configureMotors() {
    leftEncoder1.setPosition(0);
    rightEncoder1.setPosition(0);
  

    leftEncoder1.setPositionConversionFactor((TankDriveBaseConstants.kWheelDiameterMeters* Math.PI)/TankDriveBaseConstants.kGearRatio);
    rightEncoder1.setPositionConversionFactor((TankDriveBaseConstants.kWheelDiameterMeters* Math.PI)/TankDriveBaseConstants.kGearRatio);
 

    leftMotor1.setInverted(false);
    leftMotor2.setInverted(false);
    rightMotor1.setInverted(true);
    rightMotor2.setInverted(true);
  }

  public void setMotors(double speed) {
    leftMotor1.set(speed);
    leftMotor2.set(speed);
    rightMotor1.set(speed);
    rightMotor2.set(speed);
  }

  public void moveMotors(double leftSpeed, double rightSpeed) {
    leftMotor1.set(leftSpeed);
    leftMotor2.set(leftSpeed);
    rightMotor1.set(rightSpeed);
    rightMotor2.set(rightSpeed);
  } 

  public double getLeftEncoder1Position() {
    return leftEncoder1.getPosition();
  }

  public double getRightEncoder1Position() {
    return rightEncoder1.getPosition();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}