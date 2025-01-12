// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkAbsoluteEncoder.Type;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.GrabberConstants.GrabberStates;

public class GrabberSubsystem extends SubsystemBase {
  
  private final CANSparkMax m_pivotMotor;
  private final AbsoluteEncoder m_absPivotEncoder;
  private final PIDController m_pivotPid;

  private final CANSparkMax m_intakeMotor;

  private GrabberStates m_state;
  private double m_desiredAngle;
  private double m_desiredIntakeSpeed;
  
  public GrabberSubsystem() {
    m_intakeMotor = new CANSparkMax(Constants.GrabberConstants.kIntakePort, CANSparkLowLevel.MotorType.kBrushless);
    
    m_pivotMotor = new CANSparkMax(Constants.GrabberConstants.kPivotPort, CANSparkLowLevel.MotorType.kBrushless);

    m_absPivotEncoder = m_pivotMotor.getAbsoluteEncoder(Type.kDutyCycle);
    m_absPivotEncoder.setPositionConversionFactor(Constants.GrabberConstants.kEncoderToDegrees);

    m_pivotPid = new PIDController(Constants.GrabberConstants.kPivotP, 0, 0);

    m_state = GrabberStates.kGround;
    m_desiredAngle = m_state.getEncoderVal();
    m_desiredIntakeSpeed = m_state.getIntakeSpeed();
  }

  public GrabberStates getCurrState() { return m_state; }
  public double getPivotPos() { return m_absPivotEncoder.getPosition(); }
  public double getDesiredAngle() { return m_desiredAngle; }

  public void setPivot(double speed){m_pivotMotor.set(speed);}
  public void setIntake(double speed){m_intakeMotor.set(speed);}

  public double getPivotPID(){
    return m_pivotPid.calculate(getPivotPos(), m_desiredAngle);
  }

  public void setState(GrabberStates state){
    m_state = state;
    m_desiredAngle = m_state.getEncoderVal();
    m_desiredIntakeSpeed = m_state.getIntakeSpeed();
  }

  @Override
  public void periodic() {
    m_pivotMotor.set(getPivotPID());
    m_intakeMotor.set(m_desiredIntakeSpeed);
  }
}
