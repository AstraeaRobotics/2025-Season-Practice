// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TankDriveBaseConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class TankDriveSubsystem extends SubsystemBase {

  CANSparkMax leftMotor1;
  CANSparkMax leftMotor2;
  CANSparkMax rightMotor1;
  CANSparkMax rightMotor2;

  RelativeEncoder leftEncoder;
  RelativeEncoder rightEncoder;

  /** Creates a new TankDriveSubsystem. */
  public TankDriveSubsystem() {

    leftMotor1 = new CANSparkMax(1, MotorType.kBrushless);
    leftMotor2 = new CANSparkMax(2, MotorType.kBrushless);
    rightMotor1 = new CANSparkMax(3, MotorType.kBrushless);
    rightMotor2 = new CANSparkMax(4, MotorType.kBrushless);

    leftEncoder = leftMotor1.getEncoder();
    rightEncoder = rightMotor1.getEncoder();
    
    configureMotors();

  }

  public void setLeftMotors(double speed){
    leftMotor1.set(speed);
    leftMotor2.set(speed);
  }

  public void setRightMotors(double speed){
    rightMotor1.set(speed);
    rightMotor2.set(speed);
  }

  public void setMotors(double speed){
    rightMotor1.set(speed);
    rightMotor2.set(speed);
    leftMotor1.set(speed);
    leftMotor2.set(speed);
  }

  public double getDistance(){
    return leftEncoder.getPosition();
  }

  private void configureMotors() {
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
    
    leftEncoder.setPositionConversionFactor((TankDriveBaseConstants.kWheelDiameterMeters* Math.PI)/TankDriveBaseConstants.kGearRatio);
    rightEncoder.setPositionConversionFactor((TankDriveBaseConstants.kWheelDiameterMeters* Math.PI)/TankDriveBaseConstants.kGearRatio);

    leftMotor1.set(0);
    leftMotor2.set(0);
    rightMotor1.set(0);
    rightMotor2.set(0);

    
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

