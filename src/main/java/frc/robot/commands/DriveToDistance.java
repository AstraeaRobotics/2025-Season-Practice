// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.units.Distance;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankDriveSub;

public class DriveToDistance extends Command {
  /** Creates a new DriveForward. */
  TankDriveSub m_driveSub;
  private double m_initialDistance;
  private double m_distance;
  private double m_currentDistance;
  
  public DriveToDistance(TankDriveSub drivesub, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveSub = drivesub;
    m_distance = distance;
    addRequirements(drivesub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_initialDistance = m_driveSub.getLeftPosition();
    m_currentDistance = 0;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_currentDistance += m_driveSub.getDistance();
    
    m_driveSub.setLeftMotor(0.1);
    m_driveSub.setRightMotor(0.1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSub.setLeftMotor(0);
    m_driveSub.setRightMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_currentDistance == Math.abs(m_distance-m_initialDistance) - 2.5;
  }
}//could be simpler, just do a initial distance and wait until desireddistance-initial = 0
