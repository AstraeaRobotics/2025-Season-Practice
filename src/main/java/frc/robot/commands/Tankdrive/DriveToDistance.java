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
  private final boolean m_backwards;

/**
 * 
 * @param tankDrive
 * @param meters
 * @param speed must be negative if the robot is to drive backwards, positive if it is to drive forwards
 * @param backwards true if the robot is to drive the distance backwards
 */


  public DriveToDistance(TankDrivebase tankDrive, double meters, double speed, boolean backwards) {


    m_drivebase = tankDrive;
    m_initialDist = tankDrive.getPos();
    m_backwards  = backwards;
    m_speed = speed;

    if(m_backwards){
      m_endingDist = m_initialDist - meters;
    } else {
      m_endingDist = m_initialDist + meters;
    }
    
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
    if(m_drivebase.getPos() >= m_endingDist && !m_backwards){
      return true;
    }
    if(m_drivebase.getPos() <= m_endingDist && m_backwards){
      return true;
    }
    return false;
  }
}
