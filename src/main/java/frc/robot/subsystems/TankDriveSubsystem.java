// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class TankDriveSubsystem extends SubsystemBase {
  CANSparkMax leftMotor1;
  CANSparkMax leftMotor2;
  CANSparkMax rightMotor1;
  CANSparkMax rightMotor2;
  RelativeEncoder leftEncoder1;
  RelativeEncoder leftEncoder2;
  RelativeEncoder rightEncoder1;
  RelativeEncoder rightEncoder2;
  /** Creates a new TankDriveSubsystem. */
  public TankDriveSubsystem() {
    leftMotor1 = new CANSparkMax(0, MotorType.kBrushless);
    leftMotor2 = new CANSparkMax(1, MotorType.kBrushless);
    rightMotor1 = new CANSparkMax(2, MotorType.kBrushless);
    rightMotor2 = new CANSparkMax(3, MotorType.kBrushless);
    leftEncoder1 = leftMotor1.getEncoder();
    leftEncoder1.setPosition(0);
    leftEncoder2 = leftMotor2.getEncoder();
    leftEncoder2.setPosition(0);
    rightEncoder1 = rightMotor1.getEncoder();
    rightEncoder1.setPosition(0);
    rightEncoder2 = rightMotor2.getEncoder();
    rightEncoder2.setPosition(0);
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
    return leftEncoder1.getPosition();
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

