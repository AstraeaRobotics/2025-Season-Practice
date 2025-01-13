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

public class GrabberSubsystem extends SubsystemBase {
  
  private final CANSparkMax m_pivotMotor;
  private final AbsoluteEncoder m_absPivotEncoder;
  private final PIDController m_pivotPid;

  private final CANSparkMax m_intakeMotor;

  private GrabberWinchStates m_state;
  private double m_desiredAngle;
  
  public GrabberSubsystem() {
    m_intakeMotor = new CANSparkMax(Constants.GrabberWinchConstants.kIntakePort, CANSparkLowLevel.MotorType.kBrushless);
    
    m_pivotMotor = new CANSparkMax(Constants.GrabberWinchConstants.kPivotPort, CANSparkLowLevel.MotorType.kBrushless);

    m_absPivotEncoder = m_pivotMotor.getAbsoluteEncoder(Type.kDutyCycle);
    m_absPivotEncoder.setPositionConversionFactor(Constants.GrabberWinchConstants.kEncoderToRadians);

    m_pivotPid = new PIDController(Constants.GrabberWinchConstants.kPivotP, 0, 0);

    m_state = GrabberWinchStates.kRaised;
    m_desiredAngle = m_state.getGrabberVal();
  }

  public GrabberWinchStates getCurrState() { return m_state; }
  public double getPivotPos() { return m_absPivotEncoder.getPosition(); }
  public double getDesiredAngle() { return m_desiredAngle; }

  public void setPivot(double speed){m_pivotMotor.set(speed);}
  public void setIntake(double speed){m_intakeMotor.set(speed);}

  public double getPivotPID(){
    double pidOutput = m_pivotPid.calculate(getPivotPos(), m_desiredAngle);
    SmartDashboard.putNumber("Grabber Pivot PID", pidOutput);
    return pidOutput;
  }

  public void setState(GrabberWinchStates state){
    m_state = state;
    m_desiredAngle = m_state.getGrabberVal();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Grabber Encoder", getPivotPos());
    m_pivotMotor.set(getPivotPID());
  }
}
