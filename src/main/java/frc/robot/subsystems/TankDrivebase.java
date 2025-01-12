// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TankDrivebase extends SubsystemBase {
  /** Creates a new TankDrivebase. */
  private final CANSparkMax sparkMax1;
  private final CANSparkMax sparkMax2;
  private final CANSparkMax sparkMax3;
  private final CANSparkMax sparkMax4;
  private final RelativeEncoder m_Encoder;

  public TankDrivebase(int motorId1,int motorId2, int motorId3, int motorId4) {
    sparkMax1 = new CANSparkMax(motorId1,CANSparkLowLevel.MotorType.kBrushless);
    sparkMax2 = new CANSparkMax(motorId2,CANSparkLowLevel.MotorType.kBrushless);
    sparkMax3 = new CANSparkMax(motorId3,CANSparkLowLevel.MotorType.kBrushless);
    sparkMax4 = new CANSparkMax(motorId4,CANSparkLowLevel.MotorType.kBrushless);
    m_Encoder = sparkMaxOne.getEncoder();
    
 }
  public void setSpeed(double speed){
    sparkMax1.set(speed);
    sparkMax2.set(speed);
    sparkMax3.set(speed);
    sparkMax4.set(speed);
  }
  public double getEncoder(){
    return m_Encoder.getPosition();
 }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
