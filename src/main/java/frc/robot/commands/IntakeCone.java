// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.GrabberSubsystem;

public class IntakeCone extends Command {
  GrabberSubsystem m_grabberSubsystem;
  double speed;

  /** Creates a new IntakeCone. */
  public IntakeCone(GrabberSubsystem m_grabberSubsystem, double speed) {
    this.m_grabberSubsystem = m_grabberSubsystem;
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
   addRequirements(m_grabberSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_grabberSubsystem.setIntakeMotor(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_grabberSubsystem.setIntakeMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
