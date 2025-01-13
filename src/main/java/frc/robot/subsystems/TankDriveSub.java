// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TankDriveSub extends SubsystemBase {
  /** Creates a new TankDriveSub. */
  CANSparkMax leftMotor;
  CANSparkMax leftMotor2;
  CANSparkMax rightMotor;
  CANSparkMax rightMotor2;

  RelativeEncoder leftEncoder;
  RelativeEncoder rightEncoder;

  public TankDriveSub() {
    leftMotor = new CANSparkMax(1, MotorType.kBrushless);
    leftMotor2 = new CANSparkMax(2, MotorType.kBrushless);
    
    rightMotor = new CANSparkMax(4, MotorType.kBrushless);
    rightMotor2 = new CANSparkMax(6, MotorType.kBrushless);
    

    leftEncoder = leftMotor.getEncoder();
    rightEncoder = rightMotor.getEncoder();
 

    configureMotors();
  }

  public RelativeEncoder getEncoder (){
    return leftEncoder;
  }

  public void configureMotors(){
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);

    leftEncoder.setPositionConversionFactor(Constants.TankDriveConstants.kdriveConversionFactor);
    rightEncoder.setPositionConversionFactor(Constants.TankDriveConstants.kdriveConversionFactor);
    
    leftMotor.setInverted(false);
    rightMotor.setInverted(true);
  }

  public void setLeftMotor(double speed){
    leftMotor.set(speed);
    leftMotor2.set(speed);
  }
  public void setRightMotor(double speed){
    rightMotor.set(speed);
    rightMotor2.set(speed);
  }
  public double getLeftPosition() {
    return leftEncoder.getPosition();
  }
  public double getRightPosition() {
    return rightEncoder.getPosition();
  }
  public double getLeftVelocity(){
    return leftEncoder.getVelocity();
  }
  public double getRightVelocity(){
    return rightEncoder.getVelocity();
  }
  public double getDistance(){
    return leftMotor.getEncoder().getVelocity()*Math.PI*2;
  }

  public void CurveDrive(double speed, double turn, boolean canturninplace){
    WheelSpeeds speeds = DifferentialDrive.curvatureDriveIK(speed, turn, canturninplace);
    setLeftMotor(speeds.left);
    setRightMotor(speeds.right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
