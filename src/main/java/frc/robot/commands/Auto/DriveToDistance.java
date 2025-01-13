// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankDriveSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class DriveToDistance extends Command {
   TankDriveSubsystem m_TankDrive;
  double m_speed;
  double m_currentDistance;
  double m_desiredDistance;

  /** Creates a new DriveToDistance. */
  public DriveToDistance(TankDriveSubsystem tankDriveSubsystem, double speed, double desiredDistance) {
    m_TankDrive = tankDriveSubsystem;
    m_speed = speed;
    m_desiredDistance = desiredDistance;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(tankDriveSubsystem);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_currentDistance = m_TankDrive.getDistance();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_TankDrive.setLeftMotors(m_speed);
    m_TankDrive.setRightMotors(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_TankDrive.setLeftMotors(0);
    m_TankDrive.setRightMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (m_TankDrive.getDistance() - m_currentDistance >= m_desiredDistance);
  }
}

