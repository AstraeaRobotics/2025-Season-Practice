// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TankDrivebase extends SubsystemBase {
  /** Creates a new TankDrivebase. */
  private final CANSparkMax m_motorLF;
  private final CANSparkMax m_motorRF;
  private final CANSparkMax m_motorLB;
  private final CANSparkMax m_motorRB;
  private final RelativeEncoder m_encoderL;

  public TankDrivebase() {
    m_motorLF = new CANSparkMax(Constants.TankDrivebaseConstants.kMotorLFPort, CANSparkLowLevel.MotorType.kBrushless);
    m_motorRF = new CANSparkMax(Constants.TankDrivebaseConstants.kMotorRFPort, CANSparkLowLevel.MotorType.kBrushless);
    m_motorLB = new CANSparkMax(Constants.TankDrivebaseConstants.kMotorLBPort, CANSparkLowLevel.MotorType.kBrushless);
    m_motorRB = new CANSparkMax(Constants.TankDrivebaseConstants.kMotorRBPort, CANSparkLowLevel.MotorType.kBrushless);

    m_encoderL = m_motorLF.getEncoder();
    m_encoderL.setPosition(0);
    m_encoderL.setPositionConversionFactor(Constants.RotToMeters);

 }

  public void curve(double speed, double turn, boolean turnInPlace){
    WheelSpeeds speeds = DifferentialDrive.curvatureDriveIK(speed, turn, turnInPlace);
    setLMotors(speeds.left);
    setRMotors(speeds.right);
  }

  public void setLMotors(double speed){
    m_motorLF.set(speed);
    m_motorLB.set(speed);
  }

  public void setRMotors(double speed){
    m_motorRF.set(speed);
    m_motorRB.set(speed);
  }
  public double getPos(){
    return m_encoderL.getPosition();
 }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("TankDrive Encoder", getPos());
    // This method will be called once per scheduler run
  }
}
