// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.WinchConstants;
import frc.robot.Constants.WinchConstants.WinchStates;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {

  // CANSparkMax m_leftMotor;
  CANSparkMax m_winchMotor;
  RelativeEncoder m_encoder;
  WinchStates m_state;
  double setpoint;
  PIDController m_elevatorPidController;
  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem() {
    m_winchMotor = new CANSparkMax(5, MotorType.kBrushless);
    m_encoder = m_winchMotor.getEncoder();
    m_state = WinchStates.kBottom;
    m_elevatorPidController = new PIDController(WinchConstants.kP, WinchConstants.kI, WinchConstants.kD);

    configureMotors();
  }

  public void setState(WinchStates newState){
    m_state = newState;
    setpoint = m_state.getWinchSetpoint();
    m_elevatorPidController.setSetpoint(setpoint);  
  }
  
  public double getEncoder() {
    return m_encoder.getPosition();
  }
  
  public WinchStates getWinchState() {
    return m_state;
  }

  public double getMotorPID() {
    return m_elevatorPidController.calculate(getEncoder(), setpoint);
  }

  public void setMotor(double speed) {
    m_winchMotor.set(getMotorPID());
  }

  private void configureMotors() {
    m_encoder.setPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Elevator Encoder", m_encoder.getPosition());
    SmartDashboard.putNumber("PID Output", getMotorPID());

    setMotor(getMotorPID());
  }
}