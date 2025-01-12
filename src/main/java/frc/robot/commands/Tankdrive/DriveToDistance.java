// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Tankdrive;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankDrivebase;

public class DriveToDistance extends Command {
  /** Creates a new DriveToDistance. */

  private final double m_initialDist;
  private final double m_endingDist;

  private final TankDrivebase m_drivebase;

  private final double m_speed;

  public DriveToDistance(TankDrivebase tankDrive, double meters, double speed) {
    m_drivebase = tankDrive;
    m_initialDist = tankDrive.getEncoder();
    m_endingDist = m_initialDist + meters;
    m_speed = speed;
    
    addRequirements(m_drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivebase.curve(m_speed, 0, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivebase.curve(0, 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_drivebase.getEncoder() >= m_endingDist){
      return true;
    }
    return false;
  }
}
