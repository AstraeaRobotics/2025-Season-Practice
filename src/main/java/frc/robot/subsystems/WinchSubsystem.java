// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkAbsoluteEncoder.Type;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.GrabberWinchConstants.GrabberWinchStates;

public class WinchSubsystem extends SubsystemBase {
  
  private final CANSparkMax m_winchMotor;
  private final AbsoluteEncoder m_absEncoder;
  private final PIDController m_pid;

  private GrabberWinchStates m_state;
  private double m_desiredSetpoint;

  public WinchSubsystem() {
    m_winchMotor = new CANSparkMax(Constants.GrabberWinchConstants.kWinchPort, CANSparkLowLevel.MotorType.kBrushless);

    m_absEncoder = m_winchMotor.getAbsoluteEncoder(Type.kDutyCycle);
    m_absEncoder.setPositionConversionFactor(Constants.GrabberWinchConstants.kEncoderToRadians);

    m_pid = new PIDController(Constants.GrabberWinchConstants.kWinchP, 0, 0);

    m_state = GrabberWinchStates.kGround;
    m_desiredSetpoint = m_state.getWinchVal();
  }

  public GrabberWinchStates getCurrState(){
    return m_state;
  }

  public double getDesiredSetpoint(){
    return m_desiredSetpoint;
  }

  public double getPos(){
    return  m_absEncoder.getPosition();
  }

  public void setState(GrabberWinchStates state){
    m_state = state;
    m_desiredSetpoint = m_state.getWinchVal();
  }

  public double getPIDVal(){
    double pidOutput = m_pid.calculate(getPos(), m_desiredSetpoint);
    SmartDashboard.putNumber("Winch Pivot PID", pidOutput);
    return pidOutput;
  }

  public void setWinch(double speed){
    m_winchMotor.set(speed);
  }
  

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Winch Encoder", getPos());
    setWinch(getPIDVal());
  }
}
