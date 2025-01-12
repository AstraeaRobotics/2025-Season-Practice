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
  private final AbsoluteEncoder m_absEncoder;
  private final PIDController m_pivotPid;

  private final CANSparkMax m_intakeMotor;

  private GrabberStates m_state;
  private double m_desiredAngle;
  
  public GrabberSubsystem() {
    m_intakeMotor = new CANSparkMax(Constants.GrabberConstants.kIntakePort, CANSparkLowLevel.MotorType.kBrushless);
    
    m_pivotMotor = new CANSparkMax(Constants.GrabberConstants.kPivotPort, CANSparkLowLevel.MotorType.kBrushless);

    m_absEncoder = m_pivotMotor.getAbsoluteEncoder(Type.kDutyCycle);
    m_absEncoder.setPositionConversionFactor(Constants.GrabberConstants.kEncoderToDegrees);

    m_pivotPid = new PIDController(Constants.GrabberConstants.kPivotP, 0, 0);

    m_state = GrabberStates.kGround;
    m_desiredAngle = m_state.getEncoderVal();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
