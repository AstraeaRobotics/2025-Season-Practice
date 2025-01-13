// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.GrabberConstants;
import frc.robot.Constants.GrabberConstants.GrabberStates;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class GrabberSubsystem extends SubsystemBase {
  CANSparkMax pivotMotor;
  CANSparkMax intakeMotor;
  RelativeEncoder pivotEncoder;
  RelativeEncoder intakeEncoder;
  GrabberStates state;
  double setPoint;
  PIDController m_grabberPidController;

  /** Creates a new GrabberSubsystem. */
  public GrabberSubsystem() {
    pivotMotor= new CANSparkMax(1, MotorType.kBrushless);
    intakeMotor= new CANSparkMax(1, MotorType.kBrushless);

    pivotEncoder = pivotMotor.getEncoder();
    intakeEncoder= intakeMotor.getEncoder();

    m_grabberPidController = new PIDController(GrabberConstants.kP, GrabberConstants.kI, GrabberConstants.kD);

    configureMotors();
  }

  
  private void configureMotors() {
    intakeEncoder.setPosition(0);
    pivotEncoder.setPosition(0);
  }

  public void moveIntakeMotor(double speed){
    intakeMotor.set(speed);
  }

  public void movePivotMotor(double speed){
    pivotMotor.set(speed);
  }
  
  public double getPivotEncoder() {
    return pivotEncoder.getPosition();
  }

  public double getIntakeEncoder() {
    return intakeEncoder.getPosition();
  }

  public void setGrabberState(GrabberStates newState){
    state = newState;
    setPoint = newState.getGrabberSetPoint();
  }

  public GrabberStates getState(){
    return state;
  }

  public double getMotorPID() {
    return m_grabberPidController.calculate(getPivotEncoder(), setPoint);
  }

  public void setMotorPID(double speed) {
    pivotMotor.set(speed);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Pivot Encoder", pivotEncoder.getPosition());
    SmartDashboard.putNumber("PID Output", getMotorPID());
    setMotorPID(getMotorPID());
    // This method will be called once per scheduler run
  } 
}

