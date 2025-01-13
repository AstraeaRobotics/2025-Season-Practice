// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAbsoluteEncoder;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.GrabberConstants;
import frc.robot.Constants.GrabberConstants.GrabberStates;

public class GrabberSubsystem extends SubsystemBase {
  CANSparkMax pivotMotor;
  CANSparkMax intakeMotor;
  AbsoluteEncoder pivotEncoder;
  RelativeEncoder intakeEncoder;
  GrabberStates m_state;
  double setpoint;
  PIDController m_grabberPidController;

  /** Creates a new GrabberSubsystem. */
  public GrabberSubsystem() {
    pivotMotor = new CANSparkMax(8, MotorType.kBrushless);
    pivotEncoder = pivotMotor.getAbsoluteEncoder();
    intakeMotor = new CANSparkMax(9, MotorType.kBrushless);
    intakeEncoder = intakeMotor.getEncoder();
    m_state = GrabberStates.kTop;
    setpoint = m_state.getGrabberSetpoint();
    m_grabberPidController = new PIDController(GrabberConstants.kP, GrabberConstants.kI, GrabberConstants.kD);
    
    configureMotors();
  }

  public void setPivotMotor(double speed){
    pivotMotor.set(speed);
  }

  public void setIntakeMotor(double speed){
    intakeMotor.set(speed);
  }

 public void setState(GrabberStates newState){
    m_state = newState;
    setpoint = m_state.getGrabberSetpoint();
  }

  public GrabberStates getGrabberState() {
    return m_state;
  }

  public double getMotorPID() {
    return m_grabberPidController.calculate(getPivotEncoder(), setpoint);
  }

  public void setMotorPID() {
    pivotMotor.set(MathUtil.clamp(getMotorPID(),-0.8,0.8));
  }

  
  public double getPivotEncoder() {
    return pivotEncoder.getPosition();
  }

  public double getIntakeEncoder(){
    return intakeEncoder.getPosition();
  }

  private void configureMotors() {
    intakeEncoder.setPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Grabber Pivot Encoder", getPivotEncoder());
    SmartDashboard.putNumber("Intake Encoder", getIntakeEncoder());
    SmartDashboard.putNumber("PID Output", getMotorPID()); 
    setMotorPID();
     
  }
}
