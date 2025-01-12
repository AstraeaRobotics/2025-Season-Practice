// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TankDriveSub extends SubsystemBase {
  /** Creates a new TankDriveSub. */
  CANSparkMax leftMotor1;
  CANSparkMax rightMotor1;

  CANSparkMax leftMotor2;
  CANSparkMax rightMotor2;

  CANSparkMax leftMotor3;
  CANSparkMax rightMotor3;

  CANSparkMax leftMotor4;
  CANSparkMax rightMotor4;

  RelativeEncoder leftEncoder;
  RelativeEncoder rightEncoder;

  public TankDriveSub() {
    leftMotor1 = new CANSparkMax(1, MotorType.kBrushless);
    leftMotor2 = new CANSparkMax(1, MotorType.kBrushless);
    leftMotor3 = new CANSparkMax(1, MotorType.kBrushless);
    leftMotor4 = new CANSparkMax(1, MotorType.kBrushless);

    rightMotor1 = new CANSparkMax(0, MotorType.kBrushless);
    rightMotor2 = new CANSparkMax(0, MotorType.kBrushless);
    rightMotor3 = new CANSparkMax(0, MotorType.kBrushless);
    rightMotor4 = new CANSparkMax(0, MotorType.kBrushless);

    leftEncoder = leftMotor1.getEncoder();
    rightEncoder = rightMotor1.getEncoder();
 

    configureMotors();
  }

  public RelativeEncoder getEncoder (){
    return leftEncoder;
  }

  public void configureMotors(){
leftMotor2.follow(leftMotor1);
leftMotor3.follow(leftMotor1);
leftMotor4.follow(leftMotor1);

rightMotor2.follow(leftMotor1);
rightMotor3.follow(leftMotor1);
rightMotor4.follow(leftMotor1);

    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);


    leftEncoder.setPositionConversionFactor(Constants.TankDriveConstants.kEncoderConversionFactor);
    rightEncoder.setPositionConversionFactor(Constants.TankDriveConstants.kEncoderConversionFactor);


    leftMotor1.setInverted(false);
    rightMotor1.setInverted(true);
  }

  public double getleftPosition() {
    return leftEncoder.getPosition();
  }
  public double getrightPosition() {
    return rightEncoder.getPosition();
  }
  public double getleftVelocity(){
    return leftEncoder.getVelocity();
  }
  public double getrightVelocity(){
    return rightEncoder.getVelocity();
  }
    public void tankDrive(double forward, double rotation){
      double leftSpeed1 = forward + rotation;
      double rightSpeed1 = forward - rotation;

      leftMotor1.set(leftSpeed1);
      rightMotor1.set(rightSpeed1);
    }




  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
